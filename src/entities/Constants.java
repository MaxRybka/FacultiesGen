package entities;

import java.util.ArrayList;

public class Constants {
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

    public static final ArrayList<Student> STUDENTS = new ArrayList<Student>(){{
        add(new Student("Artem 1"));
        add(new Student("Artem 2"));
        add(new Student("Artem 3"));
        add(new Student("Artem 4"));
        add(new Student("Artem 5"));
        add(new Student("Artem 6"));
        add(new Student("Artem 7"));
        add(new Student("Artem 8"));
        add(new Student("Artem 9"));
        add(new Student("Artem 10"));
        add(new Student("Artem 11"));
        add(new Student("Artem 12"));
        add(new Student("Artem 13"));
        add(new Student("Artem 14"));
        add(new Student("Artem 15"));
        add(new Student("Artem 16"));
        add(new Student("Artem 17"));
        add(new Student("Artem 18"));
        add(new Student("Artem 19"));
        add(new Student("Artem 20"));
        add(new Student("Artem 21"));
        add(new Student("Artem 22"));
        add(new Student("Artem 23"));
        add(new Student("Artem 24"));
        add(new Student("Artem 25"));
        add(new Student("Artem 26"));
        add(new Student("Artem 27"));
        add(new Student("Artem 28"));
        add(new Student("Artem 29"));
        add(new Student("Artem 30"));
    }};

    public static final ArrayList<Teacher> TEACHERS = new ArrayList<Teacher>(){{
        add(new Teacher("Lidochka Tchaikovsky"));
        add(new Teacher("Ruslan Sokolov"));
        add(new Teacher("Vladislava Tkachenko"));
        add(new Teacher("Rozaliya Shevchuk"));
        add(new Teacher("Manya Morozov"));
        add(new Teacher("Stanislava Mikhailov"));
        add(new Teacher("Gala Antonov"));
//            add(new Teacher("Lyov Kovalev"));
//            add(new Teacher("Eva Tkachuk"));
//            add(new Teacher("Oleksandra Lebedev"));
//            add(new Teacher("Alina Kovalev"));
//            add(new Teacher("Inessa Novikov"));
//            add(new Teacher("Denis Utkin"));
//            add(new Teacher("Luba Pavlov"));
//            add(new Teacher("Apollinariya Ignatiev"));
//            add(new Teacher("Oleksandr Pasternak"));
//            add(new Teacher("Serafima Stasiuk"));
//            add(new Teacher("Oleh Havrylyuk"));
//            add(new Teacher("Makari Vasilev"));
//            add(new Teacher("Elena Andreev"));
    }};

    public static final Subject SUBJECTS[] = new Subject[]{
            new Subject("Subject 1", TEACHERS.get(0), 2, TEACHERS.subList(1,2).toArray(new Teacher[]{}) , STUDENTS.subList(0,10).toArray(new Student[]{})),
            new Subject("Subject 2", TEACHERS.get(3), 3, TEACHERS.subList(2,4).toArray(new Teacher[]{}) , STUDENTS.subList(5,15).toArray(new Student[]{})),
            new Subject("Subject 3", TEACHERS.get(4), 4, TEACHERS.subList(3,6).toArray(new Teacher[]{}) , STUDENTS.subList(10,15).toArray(new Student[]{})),
            new Subject("Subject 4", TEACHERS.get(0), 3, TEACHERS.subList(0,2).toArray(new Teacher[]{}) , STUDENTS.subList(12,20).toArray(new Student[]{})),
            new Subject("Subject 5", TEACHERS.get(0), 3, TEACHERS.subList(1,3).toArray(new Teacher[]{}) , STUDENTS.subList(0,20).toArray(new Student[]{})),
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

}
