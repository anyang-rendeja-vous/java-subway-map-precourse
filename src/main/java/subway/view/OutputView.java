package subway.view;

public class OutputView {

    private static final String MAIN = "## 메인 화면";
    private static final String STATION_MANAGEMENT = "1. 역 관리";
    private static final String LINE_MANAGEMENT = "2. 노선 관리";
    private static final String SECTION_MANAGEMENT = "3. 구간 관리";
    private static final String PRINT_SUBWAY_MAP = "4. 지하철 노선도 출력";
    private static final String QUIT = "Q. 종료";

    public void printMenu() {
        System.out.println(MAIN);
        System.out.println(STATION_MANAGEMENT);
        System.out.println(LINE_MANAGEMENT);
        System.out.println(SECTION_MANAGEMENT);
        System.out.println(PRINT_SUBWAY_MAP);
        System.out.println(QUIT);
        System.out.println();
    }
}
