package entities;

public class Subject {

    private String name;

    private Teacher lector;

    private int practicesAmount;

    private Teacher[] practiceTeachers;

    private Student[] students;

    public Subject(String name, Teacher lector, int practicesAmount, Teacher[] practiceTeachers, Student[] students) {
        this.name = name;
        this.lector = lector;
        this.practicesAmount = practicesAmount;
        this.practiceTeachers = practiceTeachers;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getLector() {
        return lector;
    }

    public void setLector(Teacher lector) {
        this.lector = lector;
    }

    public int getPracticesAmount() {
        return practicesAmount;
    }

    public void setPracticesAmount(int practicesAmount) {
        this.practicesAmount = practicesAmount;
    }

    public Teacher[] getPracticeTeachers() {
        return practiceTeachers;
    }

    public void setPracticeTeachers(Teacher[] practiceTeachers) {
        this.practiceTeachers = practiceTeachers;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

}
