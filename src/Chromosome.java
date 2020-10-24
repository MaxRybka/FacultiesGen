import entities.Lesson;
import entities.Student;
import entities.Subject;

import java.util.ArrayList;
import java.util.HashMap;

public class Chromosome {

    private double fitness;

    private Lesson[] genome;

    public Chromosome(double fitness, Lesson[] genome) {
        this.fitness = fitness;
        this.genome = genome;
    }

    public Chromosome() {

    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public Lesson[] getGenome() {
        return genome;
    }

    public void setGenome(Lesson[] genome) {
        this.genome = genome;
    }

    /*
     * Fills a chromosome with random genes.
     * */
    public void fillChromosomeWithRandomGenes(){
        genome = new Lesson[Schedule.GENES_COUNT];
        int counter = 0;

        for (int i=0; i < Schedule.SUBJECTS.length;i++){
            genome[counter++] = fillLesson(Schedule.SUBJECTS[i], false);

            for (int j = 0; j < Schedule.SUBJECTS[i].getPracticesAmount(); j++) {
                genome[counter++] =fillLesson(Schedule.SUBJECTS[i], true);
            }
        }

    }

    /*
     * Calculates fitness for THIS chromosome and returns it.
     * 0 - everything fine
     * +1 for every student
     * +2 for every teacher
     * +3 for every classroom
     * +3 for classroom with less seats then lesson students
     * +10 if lecture room has less then 15 seats
     * */
    public void  calculateFitness(){
        for (int i = 0; i < genome.length; i++) {
            Lesson c_lesson = genome[i];

            if(!c_lesson.isPractice() && c_lesson.getClassroom().getSeats() < 15)
                fitness+=10;

            if(c_lesson.getClassroom().getSeats() < c_lesson.getStudents().length)
                fitness+=3;

            for (int j = 0; j < genome.length; j++) {
                if(i==j)
                    continue;

                Lesson n_lesson = genome[j];
                if(!c_lesson.getTime().equals(n_lesson.getTime()) || !c_lesson.getWeekday().equals(n_lesson.getWeekday()))
                    continue;

                if(c_lesson.getClassroom().equals(n_lesson.getClassroom()))
                    fitness+=3;

                if(c_lesson.getTeacher().equals(n_lesson.getTeacher()))
                    fitness+=2;

                fitness+= findMatchCount(c_lesson.getStudents(),n_lesson.getStudents());
            }
        }
    }

    private Lesson fillLesson(Subject subject,boolean isPractice){
        Lesson lesson = new Lesson();
        lesson.setSubject(subject);
        lesson.setPractice(isPractice);

        lesson.setTime(Schedule.TIMES[Schedule.getRandomInt(0,Schedule.TIMES.length-1)]);
        lesson.setWeekday(Schedule.WEEKDAYS[Schedule.getRandomInt(0,Schedule.WEEKDAYS.length-1)]);

        lesson.setTeacher(isPractice ? subject.getPracticeTeachers()[Schedule.getRandomInt(0,subject.getPracticeTeachers().length-1)] : subject.getLector());

        lesson.setClassroom(Schedule.CLASSROOMS[Schedule.getRandomInt(0,Schedule.CLASSROOMS.length-1)]);
        if(!isPractice)
            lesson.setStudents(subject.getStudents());
        else{
            int studentsAm = Schedule.getRandomInt(1,subject.getStudents().length-1);

            Student[] foundedStudents = new Student[studentsAm];
            for(int i =0; i < studentsAm; i++){
                foundedStudents[i] = subject.getStudents()[Schedule.getRandomInt(0,subject.getStudents().length-1)];
            }

            lesson.setStudents(foundedStudents);
        }

        return lesson;
    }

    /*
       This crossover gives birth to 2 children
    */
    public Chromosome[] doubleCrossover(  Chromosome chromosome  ){

        int crossoverLine = Schedule.getRandomInt(0, Schedule.GENES_COUNT - 2);
        Chromosome[] result = new Chromosome[2];
        result[0]=new Chromosome();
        result[1]=new Chromosome();

        Lesson[] resultGenome0 = new Lesson[Schedule.GENES_COUNT];
        Lesson[] resultGenome1 = new Lesson[Schedule.GENES_COUNT];

        System.arraycopy(this.getGenome(), 0, resultGenome0, 0, crossoverLine);
        System.arraycopy(chromosome.getGenome(), crossoverLine, resultGenome0, crossoverLine, Schedule.GENES_COUNT- crossoverLine);

        System.arraycopy(chromosome.getGenome(), 0, resultGenome1, 0, crossoverLine);
        System.arraycopy(this.getGenome(), crossoverLine, resultGenome1, crossoverLine, Schedule.GENES_COUNT - crossoverLine);

        result[0].setGenome(  resultGenome0 );
        result[1].setGenome(  resultGenome1 );

        return result;

    }

    /*
    This crossover gives birth to 1 child.
    To perform that, it calls doubleCrossover() and then
    randomly selects one of the 2 children.
	*/
    public Chromosome singleCrossover(  Chromosome chromosome  ){
        Chromosome[] children = doubleCrossover(  chromosome  );

        int childNumber = Schedule.getRandomInt(0, 1);

        return children[childNumber];
    }

    public void mutateWithGivenLikelihood(){

        for (int i=0;i<Schedule.GENES_COUNT;++i){
            double randomPercent = Schedule.getRandomFloat(0,100);
            if ( randomPercent < Schedule.MUTATION_LIKELIHOOD ){
                genome[i] = fillLesson(genome[i].getSubject(), genome[i].isPractice());
            }
        }
    }

    public static int findMatchCount(final Student [] a,final Student [] b){
        int matchCount = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if(a[i].equals(a[j]))
                    matchCount++;
            }
            matchCount--;

            for (int j = 0; j < b.length; j++) {
                if(a[i].equals(b[j]))
                    matchCount++;
            }
        }
        return matchCount;
    }

    @Override
    public String toString() {

        HashMap<String, ArrayList<Lesson>> lessons = new HashMap<String, ArrayList<Lesson>>(){{
            put("Mon",new ArrayList<Lesson>());
            put("Tue",new ArrayList<Lesson>());
            put("Wed",new ArrayList<Lesson>());
            put("Thu",new ArrayList<Lesson>());
            put("Fri",new ArrayList<Lesson>());
        }};

        for (Lesson lesson : genome) {
            switch (lesson.getWeekday()) {
                case "Mon":
                    lessons.get("Mon").add(lesson);
                    break;
                case "Tue":
                    lessons.get("Tue").add(lesson);
                    break;
                case "Wed":
                    lessons.get("Wed").add(lesson);
                    break;
                case "Thu":
                    lessons.get("Thu").add(lesson);
                    break;
                case "Fri":
                    lessons.get("Fri").add(lesson);
                    break;
            }
        }
        StringBuilder strbd = new StringBuilder();



        for (String key : lessons.keySet()) {
            strbd.append("\n\n\t"+key+"\n\n");

            for (Lesson lesson:
                 lessons.get(key)) {

                strbd.append(lesson.toString() + "\n\n");
            }
        }

        return strbd.toString();
    }

}
