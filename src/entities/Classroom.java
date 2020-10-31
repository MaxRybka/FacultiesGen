package entities;

public class Classroom {

    private int seats;
    private int number;

    public Classroom(int seats, int number) {
        this.seats = seats;
        this.number = number;
    }

    public int getSeats() {
        return seats;
    }

    public int getNumber() {
        return number;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Pair or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Classroom)) {
        return false;
    }

    // typecast o to Complex so that we can compare data members
    Classroom classroom = (Classroom) obj;

        return this.number == classroom.number;
}

    @Override
    public String toString() {
        return "num: " + number +"; seats: " + seats;
    }
}
