package subway.ui;

public class OutputView {
    private static final String INFO = "[INFO]";
    private static final String ERROR = "[ERROR]";

    private static final String STATION_CREATED = "지하철 역이 등록되었습니다.";

    public void printErrorMessage(String message){
        System.out.println(ERROR + " " + message);
    }

    public void printStationCreationResult(){
        System.out.println(INFO + " " + STATION_CREATED);
    }
}
