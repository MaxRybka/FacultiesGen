package entities;

public class Lesson {

    private Subject subject;

    private Teacher teacher;

    private Classroom classroom;

    private String time;

    private String weekday;

    private Student[] students;

    private boolean isPractice;


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

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public boolean isPractice() {
        return isPractice;
    }

    public void setPractice(boolean practice) {
        isPractice = practice;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    @Override
    public String toString() {
        StringBuilder strbd = new StringBuilder();

        strbd.append("Subject : ").append(subject.toString());
        strbd.append("\nTeacher : ").append(teacher.toString());
        strbd.append("\nClassroom : ").append(classroom.toString());
        strbd.append("\nTime : ").append(time);
        strbd.append("\nIs Practice : ").append(isPractice);
        strbd.append("\nStudents : ");

        for (Student student:
             students) {
            strbd.append(student.toString()).append(",");
        }
        strbd.setCharAt(strbd.length()-1, ' ');

        return strbd.toString();

    }
}


