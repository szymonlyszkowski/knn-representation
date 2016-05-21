package knn;

import org.apache.commons.math3.stat.StatUtils;

import java.util.List;

/**
 * Created by lyszkows on 21/05/16.
 */
public class UserPredictor {
    private List<FeatureVector> neighbours;

    public UserPredictor(List<FeatureVector> neighbours){
         this.neighbours = neighbours;
    }

    public Double predictRate(){
        double[] possibleRates = new double[neighbours.size()];
        for(FeatureVector vector: neighbours){
            possibleRates[neighbours.indexOf(vector)] = vector.getUserRating();
        }
        double[] mode = StatUtils.mode(possibleRates);
        return mode[0];
    }

}
