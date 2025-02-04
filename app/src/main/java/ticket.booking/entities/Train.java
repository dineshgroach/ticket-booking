package ticket.booking.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Train {
    private String trainID;
    private Integer trainNo;

    private List<List<Integer>> seats;
    private Map<String, String> stationTimes;
    private List<String> stations;
    public Train() {}
    public Train(String trainID,Integer trainNo,List<List<Integer>> seats,Map<String,String> stationTimes,List<String> stations) {
        this.trainID = trainID;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.stations = stations;
    }
    public List<String> getStations() {
        return stations;
    }
    public List<List<Integer>> getSeats(){
        return  seats;
    }
    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }
    public String getTrainID() {
        return  trainID;
    }
    public Map<String,String> getStationTimes() {
        return stationTimes;
    }
    public Integer getTrainNo() {
        return  trainNo;
    }

    public void setTrainID(String trainID){
        this.trainID = trainID;
    }
    public void setTrainNo(Integer trainNo) {
        this.trainNo = trainNo;
    }
    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public String getTrainInfo() {
        return String.format("Train ID: %s Train No: %s",trainID,trainNo);
    }
    public String getStationTiming(String station){
        if(stationTimes.containsKey(station)) {
            return stationTimes.get(station);
        }
        return "InValid";
    }
}
