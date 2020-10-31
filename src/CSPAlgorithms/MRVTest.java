package CSPAlgorithms;


import entities.Lesson;
import entities.Student;
import org.junit.Test;

import java.util.ArrayList;

public class MRVTest {

    private static final int TEST_AMOUNT = 20000;

    @Test
    public void findLessons() {
        boolean isInvalid = false;

        for (int c = 0; c < TEST_AMOUNT; c++) {

            //MRV mrv = new MRV();
            //LCV mrv = new LCV();
            Power mrv = new Power();

            mrv.findLessons();

            ArrayList<Lesson> res = mrv.getResLessons();
            for (int i = 0; i < res.size(); i++) {
                for (int j = 0; j < res.size(); j++) {
                    if (i == j) continue;

                    if (res.get(i).getWeekday().equals(res.get(j).getWeekday())
                            && res.get(i).getTime().equals(res.get(j).getTime())) {
                        //if teacher is the same, this schedule is invalid
                        if (res.get(i).getTeacher().equals(res.get(j).getTeacher())) {
                            System.out.println("teacher the same ");
                            isInvalid = true;
                            break;
                        }

                        //if there is at least one student who has a lesson at this time, this schedule is invalid
                        for (Student f_student :
                                res.get(i).getStudents()) {
                            for (Student s_student :
                                    res.get(j).getStudents()) {
                                if(f_student.equals(s_student)){
                                    System.out.println("invalid student");
                                    isInvalid = true;
                                    break;
                                }
                            }
                        }

                        //if at this time the class is used for several lessons, this schedule is invalid
                        if(res.get(i).getClassroom().equals(res.get(j).getClassroom())){
                            System.out.println("\n\n i = "+res.get(i).toString());
                            System.out.println("\n\n j = "+res.get(j).toString());
                            System.out.println("invalid classroom");
                            isInvalid = true;
                            break;
                        }
                    }
                }
            }
            //System.out.println("counter " + c);
            assert !isInvalid;
        }
    }
}
