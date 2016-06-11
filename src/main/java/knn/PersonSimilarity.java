package knn;

import model.Movie;
import model.Rate;
import org.apache.commons.math3.stat.StatUtils;
import parser.GenerateSubmission;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PersonSimilarity {
    CSVLoader csvLoader = new CSVLoader();
    List<Movie> movieList = csvLoader.getMovieModel();
    Map<Integer, Movie> moviesMap = new Main().getMoviesMap(movieList);

    public static void main(String[] args) throws IOException {
        PersonSimilarity personSimilarity = new PersonSimilarity();
        Map<Integer, List<Integer>> peopleSimilarities = personSimilarity.calculateSimilarity();
        personSimilarity.calculateRating(peopleSimilarities);

    }

    public Rate getMovieRateForGivenUser(Movie movie, Integer userIdLookedFor){
        List<Rate> trainingList = csvLoader.getRateTrainModel();
        for(Rate rate: trainingList){
            Integer movieId = rate.getMovieId();
            Integer userId = rate.getAccountId();
            if(movieId == null || userId == null || movie ==null || userIdLookedFor == null){
                return null;

            }
            if(movieId.equals(movie.getId()) && userId.equals(userIdLookedFor)){
                return rate;
            }
        }

        return null;
    }
    public Map<Integer, List<Integer>> calculateSimilarity() {

        List<Movie> movieList = csvLoader.getMovieModel();
        Main main = new Main();
        List<Rate> trainingList = csvLoader.getRateTrainModel();
        List<Rate> taskList = csvLoader.getRateModel();
        Map<Integer, Movie> moviesMap = main.getMoviesMap(movieList);
        Map<Integer, List<Integer>> similarPeopleForGivenKey = new HashMap<>();



        for (Rate rate : taskList) {
            Map<Integer, Movie> moviesRatedByGivenUser = main.getMoviesRatedByGivenUser(rate.getAccountId(), trainingList,
                    moviesMap);

            Collection<Movie> values = moviesRatedByGivenUser.values();
            Map<Rate,Movie>moviesRatedByGivenUserWithRateAsKey  = new HashMap<>();
            for(Movie movie: values){
                Rate rateFound = getMovieRateForGivenUser(movie, rate.getAccountId());
                if(rateFound != null) {
                    moviesRatedByGivenUserWithRateAsKey.put(rateFound, movie);
                }
            }

            List<Integer> similarPeople = getSimilarPeople(moviesRatedByGivenUserWithRateAsKey, rate.getAccountId(), trainingList);
            similarPeopleForGivenKey.put(rate.getAccountId(), similarPeople);

        }
        return similarPeopleForGivenKey;
    }

    public void calculateRating(Map<Integer, List<Integer>> similarPeopleForGivenKey) throws IOException {
        List<Rate> taskList = csvLoader.getRateModel();
        List<Double> solutions = new ArrayList();

        for(Rate rate: taskList){
            List<Integer> similarPeopleForGivenUser = similarPeopleForGivenKey.get(rate.getAccountId());
            Integer movieIdToBeRated = rate.getMovieId();
            System.out.println("Retriving rates to base on...");

                List<Rate> collectedRates = similarPeopleForGivenUser.stream()
                        .map(similarPersonId -> getMovieRateForGivenUser(moviesMap.get(movieIdToBeRated), similarPersonId)).filter(Objects::nonNull).collect(
                                Collectors.toList());
            if(collectedRates.isEmpty()){
                int rand = new Random().nextInt(5);
                collectedRates.add(new Rate(0,0,0,rand));
                System.out.println("Fake rate");
            }
            double finalRate = calculateFinalRate(collectedRates);
            solutions.add(finalRate);
            System.out.println("Calculated Rate: " + finalRate);
        }

        new GenerateSubmission().generate(solutions);

    }

    private double calculateFinalRate(List<Rate> ratesFromWhichCalculateMode){
        double[] temp = new double [ratesFromWhichCalculateMode.size()];
        for (int i=0; i<ratesFromWhichCalculateMode.size(); ++i) {
            temp[i] = ratesFromWhichCalculateMode.get(i).getRate();
        }
        return StatUtils.mode(temp)[0];
    }

    private List<Integer> getSimilarPeople(Map<Rate,Movie>moviesRatedByGivenUserWithRateAsKey, Integer userId, List<Rate> trainingList){
        List<Integer> similarPersonsIds  = new ArrayList<>();

        moviesRatedByGivenUserWithRateAsKey.forEach((rate,movie) -> getSimilarPeopleIds(rate,trainingList, similarPersonsIds));
        return similarPersonsIds;
    }

    public void getSimilarPeopleIds(Rate movieRate, List<Rate> trainingList, List<Integer> similarPersonsIds) {
        for (Rate rate : trainingList) {
            if (rate.equals(movieRate)) {
                similarPersonsIds.add(rate.getAccountId());
            }
        }
    }
}
