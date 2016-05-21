package knn;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.util.MathArrays;

import java.util.List;

/**
 * @author Szymon.Lyszkowski@tomtom.com on 19.05.16.
 */
public class FeatureVector {

    private double[] featureVector;
    private double[] featureVectorNormalized;
    private int userRating;

    public FeatureVector(List<Double> data){
        featureVector = new double[data.size()];
        featureVectorNormalized = new double[data.size()];
        featureVector = ArrayUtils.toPrimitive(getFeatureArray(data));
        featureVectorNormalized = MathArrays.normalizeArray(featureVector, 1);
    }

    public FeatureVector(List<Double> data, int userRating) {
        featureVector = new double[data.size()];
        featureVectorNormalized = new double[data.size()];
        featureVector = ArrayUtils.toPrimitive(getFeatureArray(data));
        featureVectorNormalized = MathArrays.normalizeArray(featureVector, 1);
        this.userRating = userRating;
    }

    private Double[] getFeatureArray(List<Double> featureData){
        Object[] objects = featureData.toArray();
        Double [] featuresArray = new Double[featureData.size()];
        for(Object object: objects){
            featuresArray[ArrayUtils.indexOf(objects,object)] = new Double(object.toString());
        }
        return featuresArray;
    }

    public double[] getFeatureVectorNormalized() {
        return featureVectorNormalized;
    }
}
