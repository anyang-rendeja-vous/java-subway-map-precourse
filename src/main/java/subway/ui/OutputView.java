package subway.ui;

import java.util.List;
import subway.domain.Line;
import subway.domain.Station;

public class OutputView {
    private static final String INFO = "[INFO]";
    private static final String ERROR = "[ERROR]";

    private static final String CREATED = "지하철 %s이 등록되었습니다.";
    private static final String DELETED = "지하철 %s이 삭제되었습니다.";

    public void printErrorMessage(String message){
        System.out.println();
        System.out.println(ERROR + " " + message);
    }

    public void printCreationResult(String subject){
        System.out.println();
        System.out.printf(INFO + " " + CREATED, subject);
        System.out.println();
    }

    public void printDeletionResult(String subject){
        System.out.println();
        System.out.printf(INFO + " " + DELETED, subject);
        System.out.println();
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
