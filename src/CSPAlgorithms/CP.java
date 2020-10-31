package CSPAlgorithms;

import entities.SchedulingUnit;
import entities.VariantLesson;

import java.util.ArrayList;

public class CP extends MRV {


    @Override
    protected void preprocessing(){
        for (VariantLesson variantLesson:variantLessons) {
            for (VariantLesson neighbour:variantLesson.getNeighbors()){
                ArrayList<SchedulingUnit> resUnits = (ArrayList<SchedulingUnit>)variantLesson.getSchedulingUnits().clone();
                for (SchedulingUnit myUnit:resUnits) {
                    if(!neighbour.checkUnits(myUnit)){
                        variantLesson.getSchedulingUnits().remove(myUnit);
                    }
                }
                resUnits = (ArrayList<SchedulingUnit>)neighbour.getSchedulingUnits().clone();
                for (SchedulingUnit neighbourUnit:resUnits){
                    if(!variantLesson.checkUnits(neighbourUnit)){
                        neighbour.getSchedulingUnits().remove(neighbourUnit);
                    }
                }
            }
        }
    }
    @Override
    protected SchedulingUnit findValidSchedulingUnit(VariantLesson varLes){
        return findSchedulingUnit(varLes);
    }
    @Override
    protected void removeBadUnits(VariantLesson varLes,SchedulingUnit schedulingUnit){
        //delete intersections from neighbours
        for (VariantLesson vl : varLes.getNeighbors()) {
            if (vl.getTeacher().equals(varLes.getTeacher()) || vl.containsStudents(varLes.getStudents())) {
                vl.removeUnits(schedulingUnit);
            }
        }
        //delete exact scheduling Unit from all variant lessons
        for (VariantLesson vl : variantLessons)
            vl.getSchedulingUnits().remove(schedulingUnit);
    }

}
