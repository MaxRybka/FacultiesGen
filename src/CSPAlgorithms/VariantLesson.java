package CSPAlgorithms;

import entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VariantLesson {

    private Subject subject;

    private Teacher teacher;

    private boolean isPractice;

    private ArrayList<SchedulingUnit> schedulingUnits;

    private ArrayList<VariantLesson> neighbors;

    private ArrayList<Student> students ;

    public VariantLesson(Subject subject, Teacher teacher, boolean isPractice) {
        this.subject = subject;
        this.teacher = teacher;
        this.isPractice = isPractice;
        this.schedulingUnits = new ArrayList<>();
        for (Classroom CLASSROOM : Constants.CLASSROOMS) {
            for (String TIME : Constants.TIMES) {
                for (String WEEKDAY : Constants.WEEKDAYS) {
                    if (isPractice)
                        schedulingUnits.add(new SchedulingUnit(CLASSROOM,TIME,WEEKDAY));
                    else if (CLASSROOM.getSeats()>=15)
                        schedulingUnits.add(new SchedulingUnit(CLASSROOM,TIME,WEEKDAY));
                }
            }
        }

        this.students= new ArrayList<>(Arrays.asList(subject.getStudents()));
    }


    public boolean containsStudents(Student[] students){
        for (Student student : students) {
            if (this.students.contains(student)) return true;
        }
        return false;
    }

    public boolean containsStudents(List<Student> students) {
        for (Student student : students) {
            if (this.students.contains(student)) return true;
        }
        return false;
    }


    public void removeClassromUnits(){
        ArrayList<SchedulingUnit> schedulingUnits1= (ArrayList<SchedulingUnit>)schedulingUnits.clone();
        for (SchedulingUnit cUnit:schedulingUnits1) {
            if (cUnit.getClassroom().getSeats()<students.size())
                schedulingUnits.remove(cUnit);

        }
    }

    public void removeUnits(SchedulingUnit schedulingUnit){
        ArrayList<SchedulingUnit> schedulingUnits1= (ArrayList<SchedulingUnit>)schedulingUnits.clone();
        for (SchedulingUnit cUnit:schedulingUnits1) {
            if (cUnit.getTime().equals(schedulingUnit.getTime()) && cUnit.getWeekday().equals(schedulingUnit.getWeekday()))
                schedulingUnits.remove(cUnit);
        }
    }
    public int countAffectedUnits(VariantLesson variantLesson){
        int counter = 0;
        for (SchedulingUnit su1:this.schedulingUnits) {
            for (SchedulingUnit su2:variantLesson.getSchedulingUnits()) {
                if (this.getTeacher().equals(variantLesson.getTeacher()) || this.containsStudents(variantLesson.getStudents())) {
                    if (su1.getTime().equals(su2.getTime()) && su1.getWeekday().equals(su2.getWeekday()))
                        counter++;
                }
                else if (su1.equals(su2))counter++;
            }
        }
        return counter;
    }


    public ArrayList<SchedulingUnit> getSchedulingUnits() {
        return schedulingUnits;
    }

    public void setSchedulingUnits(ArrayList<SchedulingUnit> schedulingUnits) {
        this.schedulingUnits = schedulingUnits;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public boolean isPractice() {
        return isPractice;
    }

    public void setPractice(boolean practice) {
        isPractice = practice;
    }


    public ArrayList<VariantLesson> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList<VariantLesson> neighbors) {
        this.neighbors = neighbors;
    }
}
