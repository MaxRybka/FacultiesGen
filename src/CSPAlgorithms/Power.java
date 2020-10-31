package CSPAlgorithms;


public class Power extends MRV {

    @Override
    protected VariantLesson findBestLesson(){
        VariantLesson res  = variantLessons.get(0);
        for (VariantLesson variantLesson : variantLessons) {
            if (variantLesson.getNeighbors().size() > res.getNeighbors().size()) res = variantLesson;
        }
        return res;
    }

}
