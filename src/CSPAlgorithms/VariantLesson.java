package CSPAlgorithms;

import entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VariantLesson {
    private int id;

    private Subject subject;

    private Teacher teacher;

    private boolean isPractice;

    private ArrayList<SchedulingUnit> schedulingUnits;

    private ArrayList<VariantLesson> neighbors;

    private ArrayList<Student> students ;

    public VariantLesson(int id,Subject subject, Teacher teacher, boolean isPractice) {
        this.id  = id;
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
        this.neighbors = new ArrayList<>();
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


    public void removeFromNeighbors(){
        for (VariantLesson neighbor:this.neighbors) {
            neighbor.getNeighbors().remove(this);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Pair or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof VariantLesson)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        VariantLesson variantLesson = (VariantLesson) obj;

        return this.id == variantLesson.id;
    }
}
