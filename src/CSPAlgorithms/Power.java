package CSPAlgorithms;

import GenAlgorithm.Schedule;
import entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Power {

    private ArrayList<VariantLesson> variantLessons= new ArrayList<>();

    public ArrayList<Lesson> getResLessons() {
        return resLessons;
    }

    private  ArrayList<Lesson> resLessons = new ArrayList<>();
    private int lessons_count;


    public Power() {
        for (Subject subject : Constants.SUBJECTS)
            lessons_count+= subject.getPracticesAmount()+1;
        createInitialLessons();
    }

    public void findLessons(){
        for (int i = 0; i < lessons_count; i++) {
            getResultLesson(findBestLesson());
        }
    }


    private int getRandomInt( int min, int max ){
        Random randomGenerator;
        randomGenerator = new Random();
        return  randomGenerator.nextInt( max+1 ) + min ;
    }


    private void getResultLesson(VariantLesson varLes){
        Lesson les= new Lesson();
        les.setSubject(varLes.getSubject());
        les.setTeacher(varLes.getTeacher());

        SchedulingUnit schedulingUnit = varLes.getSchedulingUnits().get(getRandomInt(0,varLes.getSchedulingUnits().size()-1));
        les.setPractice(varLes.isPractice());
        les.setTime(schedulingUnit.getTime());
        les.setWeekday(schedulingUnit.getWeekday());
        les.setClassroom(schedulingUnit.getClassroom());
        les.setStudents(varLes.getStudents().toArray(new Student[varLes.getStudents().size()]));

        resLessons.add(les);
        variantLessons.remove(varLes);

        for (VariantLesson vl : variantLessons) {
            if (vl.getTeacher().equals(les.getTeacher()) || vl.containsStudents(les.getStudents())) {
                vl.removeUnits(schedulingUnit);
            }
            vl.getSchedulingUnits().remove(schedulingUnit);
        }

    }


    private Student[] getRandomStudents(int lessonIndex){
        VariantLesson variantLesson = variantLessons.get(lessonIndex);
        int practiceAm = variantLesson.getSubject().getPracticesAmount();
        int studentsAm = variantLesson.getStudents().size()/practiceAm;

        Student[] foundedStudents = new Student[studentsAm];
        for(int i =0; i < studentsAm; i++){
            int index = getRandomInt(0,variantLesson.getStudents().size()-1);
            foundedStudents[i] = variantLesson.getStudents().get(index);
            deleteStudent(index,lessonIndex);
        }
        decreasePractisesAm(practiceAm-1,variantLesson);

        return foundedStudents;
    }

    private void decreasePractisesAm(int am,VariantLesson variantLesson){
        for (VariantLesson variantLesson1 : variantLessons) {
            if (variantLesson1.getSubject().equals(variantLesson.getSubject()) && variantLesson1.isPractice())
                variantLesson1.getSubject().setPracticesAmount(am);
        }
    }

    private void deleteStudent(int studentindex,int lessonIndex){
        for (int i = lessonIndex; i < variantLessons.size(); i++) {
            if (variantLessons.get(i).getSubject().equals(variantLessons.get(lessonIndex).getSubject()) && variantLessons.get(i).isPractice())
                variantLessons.get(i).getStudents().remove(studentindex);
        }
    }


    private VariantLesson findBestLesson(){

        int bestCounter = 0;
        VariantLesson bestLesson  = variantLessons.get(0);
        for (int i = 0; i < variantLessons.size(); i++) {
            VariantLesson first = variantLessons.get(i);
            for (int j = 0; j < variantLessons.size(); j++) {
                VariantLesson second = variantLessons.get(j);
                if (i==j)continue;
                int count =first.countAffectedUnits(second);
                if (bestCounter<count){
                    bestCounter=count;
                    bestLesson = first;
                }

            }
        }
        return bestLesson;
    }


    private void createInitialLessons(){
        int counter = 0;

        //create and fill initial lessons with teacher, subject, isPractice
        for (entities.Subject Subject : Constants.SUBJECTS) {
            VariantLesson varLes = new VariantLesson(Subject,
                    Subject.getLector(),
                    false);
            variantLessons.add(counter++,varLes);
            for (int j = 0; j < Subject.getPracticesAmount(); j++) {
                variantLessons.add(counter++, new VariantLesson(Subject,
                        Subject.getPracticeTeachers()[Schedule.getRandomInt(0, Subject.getPracticeTeachers().length - 1)],
                        true));
            }
        }
        // fill initial lessons with students and remove scheduling units with not enough seats
        for (int i = 0; i < variantLessons.size(); i++) {
            if(!variantLessons.get(i).isPractice()) {
                variantLessons.get(i).setStudents(new ArrayList<>(Arrays.asList(variantLessons.get(i).getSubject().getStudents())));
            }
            else variantLessons.get(i).setStudents(new ArrayList<>(Arrays.asList(getRandomStudents(i))));
            variantLessons.get(i).removeClassromUnits();
        }

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

        for (Lesson lesson : resLessons) {
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

    public static void main(String[] args ) {
        Power power = new Power();
        power.findLessons();

        System.out.println("RESULT:\n" + power.toString());
    }

}
