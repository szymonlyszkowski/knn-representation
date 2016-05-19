import knn.FeatureVector;
import model.Movie;
import model.Rate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aga on 2016-05-18.
 */
public class Main {

    List<Movie> movieList;
    List<Rate> trainingList;
    List<Rate> taskList;

    public static void main(String []args){
        new Main().runApp();

    }


    public void runApp(){
        CSVLoader csvLoader = new CSVLoader();
        movieList = csvLoader.getMovieModel();
        trainingList = csvLoader.getRateTrainModel();
        taskList = csvLoader.getRateModel();
        Double popularity = Double.valueOf(movieList.get(0).getPopularity());
        Double budget = Double.valueOf(movieList.get(0).getBudget());
        Double voteAverage = Double.valueOf(movieList.get(0).getVoteAverage());
        ArrayList<Double> features = new ArrayList<Double>();
        features.add(popularity);
        features.add(budget);
        features.add(voteAverage);
        FeatureVector featureVector = new FeatureVector(features);


        System.out.println(movieList.size() + " ; " + trainingList.size() +  " ; " + taskList.size());

    }
}
