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
}
