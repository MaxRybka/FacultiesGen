package CSPAlgorithms;

import GenAlgorithm.Schedule;
import entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MRV {

    private ArrayList<VariantLesson> variantLessons= new ArrayList<>();
    private HashMap<SchedulingUnit,ArrayList<Lesson>> schedulingUnitArrayListHashMap;
    private  ArrayList<Lesson> resLessons = new ArrayList<>();
    private int lessons_count;


    public MRV() {
        for (Subject subject : Constants.SUBJECTS)
            lessons_count+= subject.getPracticesAmount()+1;
        createInitialLessons();
    }

    public void findLessons(){
        getResultLesson(variantLessons.get(0));
        for (int i = 1; i < lessons_count; i++) {
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
//        String t= varLes.getTimes()[getRandomInt(0,varLes.getTimes().length)];
//        String w= varLes.getWeekdays()[getRandomInt(0,varLes.getWeekdays().length)];
//        Classroom c= varLes.getClassrooms()[getRandomInt(0,varLes.getClassrooms().length)];
        les.setPractice(varLes.isPractice());
        les.setTime(schedulingUnit.getTime());
        les.setWeekday(schedulingUnit.getWeekday());
        les.setClassroom(schedulingUnit.getClassroom());
        if(!les.isPractice()) les.setStudents(varLes.getSubject().getStudents());
        else les.setStudents(getRandomStudents(varLes,schedulingUnit.getClassroom().getSeats()));

        resLessons.add(les);
        variantLessons.remove(varLes);

        for (VariantLesson vl : variantLessons) {
            if (vl.getTeacher().equals(les.getTeacher()) || vl.containsStudents(les.getStudents())) {
                vl.removeUnits(schedulingUnit);
            }
        }

    }


    private Student[] getRandomStudents(VariantLesson variantLesson,int seats){
        int studentsAm = getRandomInt(0,variantLesson.getStudents().size()-1);
        if(seats <studentsAm)
            studentsAm=getRandomInt(0,seats-1);

        Student[] foundedStudents = new Student[studentsAm];
        for(int i =0; i < studentsAm; i++){
            int index = getRandomInt(0,variantLesson.getStudents().size()-1);
            foundedStudents[i] = variantLesson.getStudents().get(index);
            deleteStudent(index,variantLesson);
        }

       return foundedStudents;
    }

    private void deleteStudent(int index,VariantLesson variantLesson){
        for (VariantLesson variantLesson1 : variantLessons) {
            if (variantLesson1.getSubject().equals(variantLesson.getSubject()) && variantLesson1.isPractice())
                variantLesson1.getStudents().remove(index);
        }
    }

    private VariantLesson findBestLesson(){
        VariantLesson res  = variantLessons.get(0);
        for (VariantLesson variantLesson : variantLessons) {
            if (variantLesson.getSchedulingUnits().size() < res.getSchedulingUnits().size()) res = variantLesson;
        }
        return res;
    }


    private void createInitialLessons(){
        int counter = 0;

        for (entities.Subject Subject : Constants.SUBJECTS) {
            variantLessons.add(counter++,new VariantLesson(Subject,
                    Subject.getLector(),
                    false));
            for (int j = 0; j < Subject.getPracticesAmount(); j++) {
                variantLessons.add(counter++, new VariantLesson(Subject,
                     Subject.getPracticeTeachers()[Schedule.getRandomInt(0, Subject.getPracticeTeachers().length - 1)],
                        true));
            }
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
        MRV mrv = new MRV();
        mrv.findLessons();

        System.out.println("RESULT:\n" + mrv.toString());
    }

}
