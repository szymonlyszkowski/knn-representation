package parser;

import java.util.Random;

/**
 * Created by lyszkows on 08/05/16.
 */
public class DataLine {

    public Integer getId() {
        return id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    private final Integer id;
    private final Integer personId;
    private final Integer movieId;
    private Integer evaluation;

    public DataLine(Integer id, Integer personId, Integer movieId, Integer evaluation){

        this.id = id;
        this.personId = personId;
        this.movieId = movieId;
        this.evaluation = evaluation;
    }

    public DataLine(String [] arguments){
        this.id = Integer.parseInt(arguments[0]);
        this.personId = Integer.parseInt(arguments[1]);
        this.movieId = Integer.parseInt(arguments[2]);
    }

    public Integer generateRandomEvaluation(){
        return new Random().nextInt(6);
    }

    public DataLine(DataLine line, Integer random){
        this.id = line.getId();
        this.personId = line.getPersonId();
        this.movieId = line.getMovieId();
        this.evaluation = random;
    }

    @Override
    public String toString() {
        return id+";"+personId+";"+movieId+";"+ evaluation;
    }
}

