package subway.ui;

import java.util.List;
import subway.domain.Station;

public class OutputView {
    private static final String INFO = "[INFO]";
    private static final String ERROR = "[ERROR]";

    private static final String STATION_CREATED = "지하철 역이 등록되었습니다.";
    private static final String STATION_DELETED = "지하철 역이 삭제되었습니다.";

    public void printErrorMessage(String message){
        System.out.println(ERROR + " " + message);
    }

    public void printStationCreationResult(){
        System.out.println(INFO + " " + STATION_CREATED);
    }

    public void printStationDeletionResult(){
        System.out.println(INFO + " " + STATION_DELETED);
    }

    public void printStationsList(List<Station> stations) {
        for (Station station : stations) {
            printSingleStation(station);
        }
    }

    private void printSingleStation(Station station){
        System.out.println(INFO + " " + station.getName());
    }
}
