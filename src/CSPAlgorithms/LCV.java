package CSPAlgorithms;

import entities.SchedulingUnit;
import entities.VariantLesson;

public class LCV extends MRV{

    @Override
    protected SchedulingUnit findSchedulingUnit(VariantLesson varLes){
        int bestCounter = 0;
        SchedulingUnit bestUnit  = varLes.getSchedulingUnits().get(0);
        for (SchedulingUnit unit:varLes.getSchedulingUnits()) {
            int counter=0;
            for (VariantLesson neighbour:varLes.getNeighbors()) {
                if (neighbour.getSchedulingUnits().contains(unit))counter++;
            }
            if (counter<bestCounter){
                bestCounter=counter;
                bestUnit=unit;
            }
        }
        return bestUnit;

    }
}
