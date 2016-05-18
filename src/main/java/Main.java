import model.Movie;
import model.Rate;

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

        System.out.println(movieList.size() + " ; " + trainingList.size() +  " ; " + taskList.size());

    }
}
