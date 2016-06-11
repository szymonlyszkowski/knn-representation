package model;

/**
 * Created by Aga on 2016-05-18.
 */
public class Rate {
    //1;1642;137;4
    private int id;
    private int accountId;
    private int movieId;
    private int rate;

    public Rate(int id, int movieId, int accountId, int rate) {
        this.id = id;
        this.movieId = movieId;
        this.accountId = accountId;
        this.rate = rate;
    }

    public Rate(){}

    public int getId() {
        return id;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getRate() {
        return rate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public boolean equals(Rate obj) {
        return  movieId==obj.movieId && rate == obj.rate;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", movieId=" + movieId +
                ", rate=" + rate +
                '}';
    }
}
