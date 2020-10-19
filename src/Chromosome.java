import entities.Lesson;
import entities.Student;
import entities.Subject;

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
        System.out.println("Filling chromosome with random genes....");

        genome = new Lesson[Schedule.GENES_COUNT];
        int counter = 0;

        for (int i=0; i < Schedule.SUBJECTS.length;i++){
            genome[counter++] = fillLesson(Schedule.SUBJECTS[i], false);

            for (int j = 0; j < Schedule.SUBJECTS[i].getPracticesAmount(); j++) {
                genome[counter++] =fillLesson(Schedule.SUBJECTS[i], true);
            }
        }

        System.out.println("I'm done filling chromosome with random genes.. Resulting genome: ");

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

}
