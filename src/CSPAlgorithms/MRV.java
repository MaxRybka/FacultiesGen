package CSPAlgorithms;

import GenAlgorithm.Schedule;
import entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class MRV {

    protected ArrayList<VariantLesson> variantLessons= new ArrayList<>();

    public ArrayList<Lesson> getResLessons() {
        return resLessons;
    }

    private  ArrayList<Lesson> resLessons = new ArrayList<>();
    private int lessons_count;


    public MRV() {
        for (Subject subject : Constants.SUBJECTS)
            lessons_count+= subject.getPracticesAmount()+1;
        createInitialLessons();
    }

    public void findLessons(){
        preprocessing();
        getResultLesson(variantLessons.get(0));
        for (int i = 1; i < lessons_count; i++) {
            getResultLesson(findBestLesson());
        }
    }

    protected void preprocessing(){

    }


    private int getRandomInt( int min, int max ){
        Random randomGenerator;
        randomGenerator = new Random();
        return  randomGenerator.nextInt( max+1 ) + min ;
    }


    protected SchedulingUnit findSchedulingUnit(VariantLesson varLes){
       return varLes.getSchedulingUnits().get(getRandomInt(0,varLes.getSchedulingUnits().size()-1));
    }

    protected SchedulingUnit findValidSchedulingUnit(VariantLesson varLes){
        SchedulingUnit schedulingUnit = findSchedulingUnit(varLes);
        boolean notValid = true;
        while(notValid){
            notValid=false;
            //delete and check if empty intersections from neighbours
            for (VariantLesson vl : varLes.getNeighbors()) {
                if(!vl.removeUnits(schedulingUnit)){
                    notValid=true;
                    varLes.getSchedulingUnits().remove(schedulingUnit);
                    schedulingUnit = findSchedulingUnit(varLes);
                    break;
                };
            }
        }
        return schedulingUnit;
    }

    protected void removeBadUnits(VariantLesson varLes,SchedulingUnit schedulingUnit){

        //delete exact scheduling Unit from all variant lessons
        for (VariantLesson vl : variantLessons)
            vl.getSchedulingUnits().remove(schedulingUnit);
    }

    private void getResultLesson(VariantLesson varLes){
        SchedulingUnit schedulingUnit = findValidSchedulingUnit(varLes);
        Lesson les= new Lesson();
        les.setSubject(varLes.getSubject());
        les.setTeacher(varLes.getTeacher());
        les.setPractice(varLes.isPractice());
        les.setTime(schedulingUnit.getTime());
        les.setWeekday(schedulingUnit.getWeekday());
        les.setClassroom(schedulingUnit.getClassroom());
        les.setStudents(varLes.getStudents().toArray(new Student[varLes.getStudents().size()]));

        resLessons.add(les);
        varLes.removeFromNeighbors();
        variantLessons.remove(varLes);

        removeBadUnits(varLes,schedulingUnit);

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

    protected VariantLesson findBestLesson(){
        VariantLesson res  = variantLessons.get(0);
        for (VariantLesson variantLesson : variantLessons) {
            if (variantLesson.getSchedulingUnits().size() < res.getSchedulingUnits().size()) res = variantLesson;
        }
        return res;
    }



    private void createInitialLessons(){
        int counter = 0;

        //create and fill initial lessons with teacher, subject, isPractice
        for (entities.Subject Subject : Constants.SUBJECTS) {
            VariantLesson varLes = new VariantLesson(counter,Subject,
                    Subject.getLector(),
                    false);
            variantLessons.add(counter++,varLes);
            for (int j = 0; j < Subject.getPracticesAmount(); j++) {
                varLes = new VariantLesson(counter,Subject,
                        Subject.getPracticeTeachers()[Schedule.getRandomInt(0, Subject.getPracticeTeachers().length - 1)],
                        true);
                variantLessons.add(counter++, varLes);
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

        //fill neighbours
        for (int i = 0; i < variantLessons.size(); i++) {
            for (int j = 0; j < variantLessons.size(); j++) {
                if (i==j) continue;
                if (variantLessons.get(i).getTeacher().equals(variantLessons.get(j).getTeacher()) ||
                        variantLessons.get(i).containsStudents(variantLessons.get(j).getStudents())) {
                    variantLessons.get(i).getNeighbors().add(variantLessons.get(j));
                }
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



}
