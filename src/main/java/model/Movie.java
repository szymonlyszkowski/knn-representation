package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aga on 2016-05-18.
 */
public class Movie {

    private int id;
    private String title;
    private float popularity;
    private String releaseDate;
    private boolean adult;
    private long budget;
    private List<String> genres;
    private List<String> productionCompanies;
    private long revenue;
    private int runtime;
    private String tagline;
    private float userRating;
    private float voteAverage;
    private String status;
    public int userRate;

    public Movie() {
    }

    public Movie(int id, String title, float popularity, String releaseDate, boolean adult, long budget, List<String> genres, List<String> productionCompanies, long revenue, int runtime, float userRating, String tagline, String status, float voteAverage) {
        this.id = id;
        this.title = title;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
        this.adult = adult;
        this.budget = budget;
        this.genres = genres;
        this.productionCompanies = productionCompanies;
        this.revenue = revenue;
        this.runtime = runtime;
        this.userRating = userRating;
        this.tagline = tagline;
        this.status = status;
        this.voteAverage = voteAverage;
    }

    public Movie(int id, String title, float popularity, String releaseDate, boolean adult, long budget, List<String>
            genres, List<String> productionCompanies, long revenue, int runtime, float userRating, String tagline,
            String status, float voteAverage, int userRate) {
        this.id = id;
        this.title = title;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
        this.adult = adult;
        this.budget = budget;
        this.genres = genres;
        this.productionCompanies = productionCompanies;
        this.revenue = revenue;
        this.runtime = runtime;
        this.userRating = userRating;
        this.tagline = tagline;
        this.status = status;
        this.voteAverage = voteAverage;
        this.userRate = userRate;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPopularity() {
        return this.popularity;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isAdult() {
        return this.adult;
    }

    public long getBudget() {
        return this.budget;
    }

    public List<String> getGenres() {

        return this.genres;
    }

    public List<String> getProductionCompanies() {
        return this.productionCompanies;
    }

    public long getRevenue() {
        return this.revenue;
    }

    public int getRuntime() {
        return this.runtime;
    }

    public String getTagline() {
        return this.tagline;
    }

    public float getVoteAverage() {
        return this.voteAverage;
    }

    public String getStatus() {
        return this.status;
    }

    public float getUserRating() {
        return this.userRating;
    }

    public String toString() {
        return this.id + ";" +
                this.title + ";" +
                this.popularity + ";" +
                this.releaseDate + ";" +
                this.adult + ";" +
                this.budget + ";" +
                genresToString() + ";" +
                companiesToString() + ";" +
                this.revenue + ";" +
                this.runtime + ";" +
                this.userRating + ";" +
                this.tagline + ";" +
                this.status + ";" +
                this.voteAverage;
    }

    private String genresToString(){
        String tmp = "";
        for(int i=0; i< genres.size(); i++){
            tmp+=genres.get(i);
            if(i+1 < genres.size()) tmp+=":";
        }
        return tmp;
    }

    private String companiesToString(){
        String tmp = "";
        for(int i=0; i< productionCompanies.size(); i++){
            tmp+=productionCompanies.get(i);
            if(i+1 < productionCompanies.size()) tmp+=":";
        }
        return tmp;
    }

    public List<Double> getFeatureData(){
        Integer YEAR_INDEX = 0;
        List<Double> data = new ArrayList();
        data.add((double) popularity);
        data.add((double) budget);
        data.add((double)runtime);
//        data.add((double) revenue);
        data.add((double) voteAverage);
        data.add(Double.parseDouble(releaseDate.split("-")[YEAR_INDEX]));
        return data;
    }

}
