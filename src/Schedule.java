import entities.Classroom;
import entities.Subject;

import java.util.Random;

public class Schedule {

    //number of "generations".
    public static final int MAX_ITERATIONS = 200;

    public static int GENES_COUNT;

    public static final Subject SUBJECTS[] = new Subject[]{
        //TODO: fill

    };

    public static final String TIMES[] = new String[]{
            "8:30",
            "10:00",
            "11:40",
            "13:30",
            "15:00",
            "16:30",
            "18:00"
    };

    public static final String WEEKDAYS[] = new String[]{
            "Mon",
            "Tue",
            "Wed",
            "Thu",
            "Fri"
    };

    public static final Classroom CLASSROOMS[] = new Classroom[]{
            new Classroom(4,1),
            new Classroom(8,2),
            new Classroom(15,3),
            new Classroom(20,4),
            new Classroom(20,5),
            new Classroom(7,6),
            new Classroom(6,7),
            new Classroom(9,8),
            new Classroom(3,9),
            new Classroom(6,10),
            new Classroom(8,11),
            new Classroom(9,12)
    };

    public Schedule() {
        for (Subject subject : SUBJECTS)
            GENES_COUNT+= subject.getPracticesAmount()+1;


    }

    /*
     * Returns random integer number between min and max ( all included :)  )
     * */
    public static int getRandomInt( int min, int max ){
        Random randomGenerator;
        randomGenerator = new Random();
        return  randomGenerator.nextInt( max+1 ) + min ;
    }




    public static void main( String[] args ) {

    }
}
