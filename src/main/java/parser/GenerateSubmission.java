package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by lyszkows on 21/05/16.
 */
public class GenerateSubmission {

    public void generate() throws IOException {
        Stream<String> streamLines = Files.lines(Paths.get(ClassLoader.getSystemClassLoader().getResource("task.csv").getPath()));
        Stream<DataLine> dataLineStream = streamLines.map(line -> new DataLine(line.split(";")));
        Stream<DataLine> dataLineStreamWithRandomEvals = dataLineStream.map(dataLine -> new DataLine(dataLine,
                dataLine.generateRandomEvaluation()));
        List<String> stringStream = dataLineStreamWithRandomEvals.map(dataLine -> dataLine.toString()).collect(toList());
        Files.write(Paths.get("submission.csv"), stringStream);
    }

    public void generate(List<Double> solution) throws IOException {
        Stream<String> streamLines = Files.lines(Paths.get(ClassLoader.getSystemClassLoader().getResource("task.csv").getPath()));
        Stream<DataLine> dataLineStream = streamLines.map(line -> new DataLine(line.split(";")));
        List<DataLine> dataLineLines = dataLineStream.collect(toList());
        List<DataLine> dataLineLinesWithPredictions = new ArrayList();
        for(int i=0; i<dataLineLines.size()-1; ++i){
            dataLineLinesWithPredictions.add(i,new DataLine(dataLineLines.get(i), solution.get(i).intValue()));
        }
        List<String> stringStream = dataLineLinesWithPredictions.stream().map(dataLine -> dataLine.toString()).collect(toList());
        Files.write(Paths.get("submission.csv"), stringStream);
    }
}
