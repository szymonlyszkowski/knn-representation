package knn;

import org.apache.commons.math3.ml.distance.EuclideanDistance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyszkows on 21/05/16.
 */
public class VectorSimilarity {

    private List<FeatureVector> featureVectors;

    public VectorSimilarity(List<FeatureVector> featuresGiven){
       featureVectors = featuresGiven;
    }

    public List<Double> calculateDistancesOfNeighbours(FeatureVector vector){
        List<Double> distances = new ArrayList();
        for(FeatureVector vectorGiven: featureVectors){
            distances.add(calculateSimilarity(vectorGiven,vector));
        }
        return distances;
    }

    public List<FeatureVectorMovieTuple>



    private double calculateSimilarity(FeatureVector ft1, FeatureVector ft2){
        return new EuclideanDistance().compute(ft1.getFeatureVectorNormalized(),ft2.getFeatureVectorNormalized());
    }
}
