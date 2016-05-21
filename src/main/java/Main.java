import knn.FeatureVector;
import knn.VectorSimilarity;
import model.Movie;
import model.Rate;

import java.util.*;

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

    private List<FeatureVector> createFeatureVectorSet(List<Movie>movieList){
        List<FeatureVector> vector = new ArrayList<FeatureVector>();
        for(Movie movie: movieList){
           vector.add(new FeatureVector(movie.getFeatureData(), movie.userRate));
        }
       return vector;
    }

    private Map<Integer,Movie> getMoviesRatedByGivenUser(Integer userId, List<Rate> trainingRates, Map<Integer,Movie> moviesMap){
        Map<Integer,Movie> userRatedMovies = new HashMap<Integer, Movie>();
        for (Rate rate: trainingRates)
            if (rate.getAccountId() == userId) {
                Movie userRatedMovie = moviesMap.get(rate.getMovieId());
                if (userRatedMovie != null){
                    userRatedMovie.userRate = rate.getRate();
                    userRatedMovies.put(userRatedMovie.getId(),userRatedMovie);
                }
            }

        return userRatedMovies;
    }

//    private ImmutablePair<Integer, Movie> extractUserMovie(List<Movie> movies, Map<Integer, Movie> userRatedMovies, Rate rate) {
//        for (Movie movie : movies) {
//            if (movie.getId() == rate.getMovieId()) {
//                return new ImmutablePair<Integer,Movie>(movie.getId(), movie);
//            }
//        }
//        throw new RuntimeException("No movies found in collection of all movies!");
//    }

    private Map getMoviesMap(List<Movie> movies){
        HashMap<Integer, Movie> moviesKeyId = new HashMap<Integer, Movie>();
        for(Movie movie: movies){
            moviesKeyId.put(movie.getId(),movie);
        }
        return moviesKeyId;
    }

    public void runApp(){
        CSVLoader csvLoader = new CSVLoader();
        movieList = csvLoader.getMovieModel();
        trainingList = csvLoader.getRateTrainModel();
        taskList = csvLoader.getRateModel();

        Map<Integer, Movie> moviesMap = getMoviesMap(movieList);
        Map<Integer, Movie> moviesRatedByGivenUser = getMoviesRatedByGivenUser(68, trainingList, moviesMap);

        ArrayList<Movie> moviesRatedByUser = new ArrayList<Movie>(moviesRatedByGivenUser.values());

        List<FeatureVector> featureVectorSet = createFeatureVectorSet(moviesRatedByUser);
        VectorSimilarity vectorSimilarity = new VectorSimilarity(featureVectorSet);


        final List<FeatureVector> neighbours = vectorSimilarity
                .returnFeatureVectorsUsedByKNN(5, new FeatureVector(moviesMap.get(3078).getFeatureData()));

        System.out.println(movieList.size() + " ; " + trainingList.size() +  " ; " + taskList.size());

    }
}
