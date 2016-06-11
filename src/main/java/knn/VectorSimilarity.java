package knn;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VectorSimilarity {

    private List<FeatureVector> featureVectors;

    public VectorSimilarity(List<FeatureVector> featuresGiven){
       featureVectors = featuresGiven;
    }

    public List<FeatureVector>returnFeatureVectorsUsedByKNN(Integer kParameter, FeatureVector vectorToBeCompared){
        List<Double> distances = calculateDistancesOfNeighbours(vectorToBeCompared);
        List<Pair<Double, Integer>> distancesIndices = getClosestFeatureVectors(kParameter, distances);
        List<FeatureVector> vectors = new ArrayList();
        for(Pair<Double,Integer> pair: distancesIndices){
            vectors.add(featureVectors.get(pair.getRight()));
        }
        return vectors;
    }

    private List<Double> calculateDistancesOfNeighbours(FeatureVector vector){
        List<Double> distances = new ArrayList();
        for(FeatureVector vectorGiven: featureVectors){
            distances.add(calculateSimilarity(vectorGiven,vector));
        }
        return distances;
    }

    private List<Pair<Double,Integer>> getClosestFeatureVectors(Integer kParameter, List<Double> distances){
        Collections.sort(distances);
        List<Pair<Double,Integer>>distancesIndices = new ArrayList<Pair<Double,Integer>>();
        for(Double distance: distances){
          distancesIndices.add(new ImmutablePair(distance, distances.indexOf(distance)));
        }

        return distancesIndices.subList(0, kParameter);
    }

    private double calculateSimilarity(FeatureVector ft1, FeatureVector ft2){
        return new EuclideanDistance().compute(ft1.getFeatureVectorNormalized(),ft2.getFeatureVectorNormalized());
    }
}
