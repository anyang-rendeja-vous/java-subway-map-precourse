package subway.view;

import java.util.List;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;

public class OutputView {

    private static final String MAIN = "## 메인 화면";
    private static final String STATION_MANAGEMENT = "1. 역 관리";
    private static final String LINE_MANAGEMENT = "2. 노선 관리";
    private static final String SECTION_MANAGEMENT = "3. 구간 관리";
    private static final String PRINT_SUBWAY_MAP = "4. 지하철 노선도 출력";
    private static final String QUIT = "Q. 종료";

    private static final String STATION_MANAGEMENT_MAIN = "## 역 관리 화면";
    private static final String ADD_STATION = "1. 역 등록";
    private static final String DELETE_STATION = "2. 역 삭제";
    private static final String PRINT_ALL_STATIONS = "3. 역 조회";
    private static final String BACK = "B. 돌아가기";

    private static final String LINE_MANAGEMENT_MAIN = "## 노선 관리 화면";
    private static final String ADD_LINE = "1. 노선 등록";
    private static final String DELETE_LINE = "2. 노선 삭제";
    private static final String PRINT_ALL_LINES = "3. 노선 조회";

    private static final String SECTION_MANAGEMENT_MAIN = "## 구간 관리 화면";
    private static final String ADD_SECTION = "1. 구간 등록";
    private static final String DELETE_SECTION = "2. 구간 삭제";

    private static final String PREFIX = "[INFO] ";
    private static final String SUCCESSFUL_STATION_REGISTRATION = "지하철 역이 등록되었습니다.";
    private static final String SUCCESSFUL_STATION_DELETE = "지하철 역이 삭제되었습니다.";
    private static final String SUCCESSFUL_LINE_REGISTRATION = "지하철 노선이 등록되었습니다.";
    private static final String SUCCESSFUL_LINE_DELETE = "지하철 노선이 삭제되었습니다.";
    private static final String SUCCESSFUL_SECTION_REGISTRATION = "구간이 등록되었습니다.";
    private static final String SUCCESSFUL_SECTION_DELETE = "구간이 삭제되었습니다.";

    private static final String SUBWAY_MAP = "## 지하철 노선도";
    private static final String SEPARATOR = "---";

    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printError(String message) {
        System.out.println();
        System.out.println(ERROR_PREFIX + message);
        System.out.println();
    }

    public void printInitialMenu() {
        System.out.println(MAIN);
        System.out.println(STATION_MANAGEMENT);
        System.out.println(LINE_MANAGEMENT);
        System.out.println(SECTION_MANAGEMENT);
        System.out.println(PRINT_SUBWAY_MAP);
        System.out.println(QUIT);
        System.out.println();
    }

    public void printStationManagementMenu() {
        System.out.println();
        System.out.println(STATION_MANAGEMENT_MAIN);
        System.out.println(ADD_STATION);
        System.out.println(DELETE_STATION);
        System.out.println(PRINT_ALL_STATIONS);
        System.out.println(BACK);
        System.out.println();
    }

    public void printLineManagementMenu() {
        System.out.println();
        System.out.println(LINE_MANAGEMENT_MAIN);
        System.out.println(ADD_LINE);
        System.out.println(DELETE_LINE);
        System.out.println(PRINT_ALL_LINES);
        System.out.println(BACK);
        System.out.println();
    }

    public void successAddStation() {
        System.out.println();
        System.out.println(PREFIX + SUCCESSFUL_STATION_REGISTRATION);
        System.out.println();
    }

    public void successDeleteStation() {
        System.out.println();
        System.out.println(PREFIX + SUCCESSFUL_STATION_DELETE);
    }

    public void printAllStations(List<Station> stations) {
        stations.stream()
                .map(station -> PREFIX + station.getName())
                .forEach(System.out::println);
        System.out.println();
    }

    public void successAddLine() {
        System.out.println();
        System.out.println(PREFIX + SUCCESSFUL_LINE_REGISTRATION);
        System.out.println();
    }

    public void printAllLines(List<Line> lines) {
        lines.stream()
                .map(line -> PREFIX + line.getName())
                .forEach(System.out::println);
        System.out.println();
    }

    public void successDeleteLine() {
        System.out.println();
        System.out.println(PREFIX + SUCCESSFUL_LINE_DELETE);
        System.out.println();
    }

    public void printSectionManagementMenu() {
        System.out.println();
        System.out.println(SECTION_MANAGEMENT_MAIN);
        System.out.println(ADD_SECTION);
        System.out.println(DELETE_SECTION);
        System.out.println(BACK);
        System.out.println();
    }

    public void successAddSection() {
        System.out.println();
        System.out.println(PREFIX + SUCCESSFUL_SECTION_REGISTRATION);
        System.out.println();
    }

    public void successDeleteSection() {
        System.out.println();
        System.out.println(PREFIX + SUCCESSFUL_SECTION_DELETE);
        System.out.println();
    }

    public void printSubwayMap() {
        System.out.println();
        System.out.println(SUBWAY_MAP);
        LineRepository.lines().forEach(line -> {
            System.out.println(PREFIX + line.getName());
            System.out.println(PREFIX + SEPARATOR);
            line.getStations()
                    .stream()
                    .map(station -> PREFIX + station.getName())
                    .forEach(System.out::println);
            System.out.println();
        });
    }
}