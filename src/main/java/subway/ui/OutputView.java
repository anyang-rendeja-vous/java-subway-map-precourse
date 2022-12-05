package subway.ui;

import java.util.List;
import subway.domain.Line;
import subway.domain.Station;

public class OutputView {
    private static final String INFO = "[INFO]";
    private static final String ERROR = "[ERROR]";

    private static final String STATION_CREATED = "지하철 역이 등록되었습니다.";
    private static final String STATION_DELETED = "지하철 역이 삭제되었습니다.";
    private static final String LINE_CREATED = "지하철 노선이 등록되었습니다.";
    private static final String LINE_DELETED = "지하철 노선이 삭제되었습니다.";

    public void printErrorMessage(String message){
        System.out.println();
        System.out.println(ERROR + " " + message);
    }

    public void printStationCreationResult(){
        System.out.println();
        System.out.println(INFO + " " + STATION_CREATED);
    }

    public void printStationDeletionResult(){
        System.out.println();
        System.out.println(INFO + " " + STATION_DELETED);
    }

    public void printStationsList(List<Station> stations) {
        for (Station station : stations) {
            printSingleStation(station);
        }
        System.out.println();
    }

    private void printSingleStation(Station station){
        System.out.println(INFO + " " + station.getName());
    }

    public void printLineCreationResult(){
        System.out.println();
        System.out.println(INFO + " " + LINE_CREATED);
        System.out.println();
    }

    public void printLineDeletionResult(){
        System.out.println();
        System.out.println(INFO + " " + LINE_DELETED);
        System.out.println();
    }

    public void printLineList(List<Line> lines) {
        for (Line line : lines){
            printSingleLine(line);
        }
        System.out.println();
    }

    private void printSingleLine(Line line){
        System.out.println(INFO + " " + line.getName());
    }
}
