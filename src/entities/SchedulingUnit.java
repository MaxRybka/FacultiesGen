package entities;

public class SchedulingUnit {
    private Classroom classroom;

    private String time;

    private String weekday;

    public SchedulingUnit(Classroom classroom, String time, String weekday) {
        this.classroom = classroom;
        this.time = time;
        this.weekday = weekday;
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
    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Pair or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof SchedulingUnit)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        SchedulingUnit schedulingUnit = (SchedulingUnit) obj;

        return this.time.equals(schedulingUnit.time) && this.weekday.equals(schedulingUnit.weekday) && this.classroom.equals(schedulingUnit.classroom);
    }
}
