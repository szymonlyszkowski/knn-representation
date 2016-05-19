import model.Movie;
import model.Rate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aga on 2016-05-18.
 */
public class CSVLoader {

    private List<Movie> movieModel = new ArrayList<Movie>();
    private List<Rate> rateTrainModel = new ArrayList<Rate>();
    private List<Rate> rateModel = new ArrayList<Rate>();


    public CSVLoader(){
        parseFile(getClass().getResource("data.csv").getPath(), 1);
        parseFile(getClass().getResource("train.csv").getPath(), 2);
        parseFile(getClass().getResource("task.csv").getPath(), 0);
    }

    private void parseFile(String fileName, int type) {
        String line = "";
        try {
            File inputContainer = new File(fileName);
            if (!inputContainer.exists()) {
                throw new RuntimeException("ERROR: missing input file");
            }
            FileReader fr = new FileReader(inputContainer);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                String[] inputs = line.split(";");
                if(type == 1){
                    movieModel.add(new Movie(Integer.parseInt(inputs[0]),
                            inputs[1],
                            Float.parseFloat(inputs[2]),
                            inputs[3],
                            Boolean.parseBoolean(inputs[4]),
                            Long.parseLong(inputs[5]),
                            parseString(inputs[6]),
                            parseString(inputs[7]),
                            Long.parseLong(inputs[8]),
                            Integer.parseInt(inputs[9]),
                            Float.parseFloat(inputs[10]),
                            inputs[11],
                            inputs[12],
                            Float.parseFloat(inputs[13])
                    ));
                }else if(type==2){
                    rateTrainModel.add(new Rate(
                            Integer.parseInt(inputs[0]),
                            Integer.parseInt(inputs[1]),
                            Integer.parseInt(inputs[2]),
                            Integer.parseInt(inputs[3])));
                }else {
                    rateModel.add(new Rate(Integer.parseInt(inputs[0]),
                            Integer.parseInt(inputs[1]),
                            Integer.parseInt(inputs[2]),
                            0));
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<String> parseString(String text){
        String[] lister = text.split(":");
        List<String> arrayList = new ArrayList<String>();
        for (String list: lister
             ) {
            arrayList.add(list);
        }
        return arrayList;
    }

    public List<Movie> getMovieModel() {
        return movieModel;
    }

    public List<Rate> getRateTrainModel() {
        return rateTrainModel;
    }

    public List<Rate> getRateModel() {
        return rateModel;
    }
}
