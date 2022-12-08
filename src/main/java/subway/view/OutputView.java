package subway.view;

import java.util.List;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.menu.LineManageMenu;
import subway.domain.menu.MainMenu;
import subway.domain.menu.SectionManageMenu;
import subway.domain.menu.StationManageMenu;

public class OutputView {

    private static final String INFO_PREFIX = "[INFO] ";
    private static final String ERROR_PREFIX = "[ERROR] ";

    private static final String STATION_LIST = "## 역 목록";
    private static final String LINE_LIST = "## 노선 목록";
    private static final String SUBWAY_MAP = "## 지하철 노선도";
    private static final String SEPARATOR = "---";

    private static final String SUCCESSFUL_STATION_REGISTRATION = "지하철 역이 등록되었습니다.";
    private static final String SUCCESSFUL_STATION_DELETE = "지하철 역이 삭제되었습니다.";
    private static final String SUCCESSFUL_LINE_REGISTRATION = "지하철 노선이 등록되었습니다.";
    private static final String SUCCESSFUL_LINE_DELETE = "지하철 노선이 삭제되었습니다.";
    private static final String SUCCESSFUL_SECTION_REGISTRATION = "구간이 등록되었습니다.";
    private static final String SUCCESSFUL_SECTION_DELETE = "구간이 삭제되었습니다.";

    public void printError(String message) {
        System.out.println();
        System.out.println(ERROR_PREFIX + message);
        System.out.println();
    }

    public void printInitialMenu() {
        MainMenu.getAll()
                .forEach(System.out::println);
        System.out.println();
    }

    public void printStationManagementMenu() {
        System.out.println();
        StationManageMenu.getAll()
                .forEach(System.out::println);
        System.out.println();
    }

    public void printLineManagementMenu() {
        System.out.println();
        LineManageMenu.getAll()
                .forEach(System.out::println);
        System.out.println();
    }

    public void printSectionManagementMenu() {
        System.out.println();
        SectionManageMenu.getAll()
                .forEach(System.out::println);
        System.out.println();
    }

    public void successAddStation() {
        System.out.println();
        System.out.println(INFO_PREFIX + SUCCESSFUL_STATION_REGISTRATION);
        System.out.println();
    }

    public void successDeleteStation() {
        System.out.println();
        System.out.println(INFO_PREFIX + SUCCESSFUL_STATION_DELETE);
        System.out.println();
    }

    public void printAllStations(List<Station> stations) {
        System.out.println(STATION_LIST);
        stations.stream()
                .map(station -> INFO_PREFIX + station.getName())
                .forEach(System.out::println);
        System.out.println();
    }

    public void successAddLine() {
        System.out.println();
        System.out.println(INFO_PREFIX + SUCCESSFUL_LINE_REGISTRATION);
        System.out.println();
    }

    public void printAllLines(List<Line> lines) {
        System.out.println(LINE_LIST);
        lines.stream()
                .map(line -> INFO_PREFIX + line.getName())
                .forEach(System.out::println);
        System.out.println();
    }

    public void successDeleteLine() {
        System.out.println();
        System.out.println(INFO_PREFIX + SUCCESSFUL_LINE_DELETE);
        System.out.println();
    }

    public void successAddSection() {
        System.out.println();
        System.out.println(INFO_PREFIX + SUCCESSFUL_SECTION_REGISTRATION);
        System.out.println();
    }

    public void successDeleteSection() {
        System.out.println();
        System.out.println(INFO_PREFIX + SUCCESSFUL_SECTION_DELETE);
        System.out.println();
    }

    public void printMap() {
        System.out.println();
        System.out.println(SUBWAY_MAP);
        LineRepository.lines().forEach(line -> {
            System.out.println(INFO_PREFIX + line.getName());
            System.out.println(INFO_PREFIX + SEPARATOR);
            printStations(line);
            System.out.println();
        });
    }

    private void printStations(Line line) {
        line.getStations()
                .stream()
                .map(station -> INFO_PREFIX + station.getName())
                .forEach(System.out::println);
    }
}
