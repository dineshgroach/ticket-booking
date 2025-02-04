package ticket.booking.services;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class TrainService {
    private List<Train> trainList;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String TRAIN_PATH = "C:/Users/dines/Desktop/IRCTC/app/src/main/java/ticket.booking/localDB/trains.json";
    private void loadTrainListFromFile() throws IOException {
        trainList = objectMapper.readValue(new File(TRAIN_PATH), new TypeReference<List<Train>>() {});
    }
    public TrainService() throws IOException{
        loadTrainListFromFile();
    }
    private boolean validTrain(Train train,String source,String destination) {
        List<String> orderStations = train.getStations();
        int sourceIdx = orderStations.indexOf(source.toLowerCase());
        int destinationIdx = orderStations.indexOf(destination.toLowerCase());
        return sourceIdx != -1 && destinationIdx != -1 && sourceIdx < destinationIdx;
    }
    public List<Train> searchTrain(String source,String destination) {
        return trainList.stream().filter(train -> validTrain(train,source,destination)).collect(Collectors.toList());
    }
    public Train getTrain(String trainID) {
        try{
            return trainList.stream().filter(train -> train.getTrainID().equals(trainID)).findFirst().get();
        }catch (NoSuchElementException nsee){
            return new Train();
        }
    }
}
