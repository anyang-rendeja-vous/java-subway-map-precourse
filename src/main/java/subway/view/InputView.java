package subway.view;

import java.util.Scanner;

public class InputView {

    private static final String ENTER_NUMBER = "## 원하는 기능을 선택하세요.";
    private static final String ENTER_STATION_NAME_TO_ADD = "## 등록할 역 이름을 입력하세요.";
    private static final String ENTER_STATION_NAME_TO_DELETE = "## 삭제할 역 이름을 입력하세요.";

    private static final String ENTER_LINE_NAME_TO_ADD = "## 등록할 노선 이름을 입력하세요.";
    private static final String ENTER_UPBOUND_TERMINUS_STATION = "## 등록할 노선의 상행 종점역 이름을 입력하세요.";
    private static final String ENTER_DOWNBOUND_TERMINUS_STATION = "## 등록할 노선의 하행 종점역 이름을 입력하세요.";
    private static final String ENTER_LINE_NAME_TO_DELETE = "## 삭제할 노선 이름을 입력하세요.";

    private static final String ENTER_LINE_NAME = "## 노선을 입력하세요.";
    private static final String ENTER_STATION_NAME = "## 역이름을 입력하세요.";
    private static final String ENTER_ORDER = "## 순서를 입력하세요.";

    private static final String ENTER_LINE_NAME_TO_DELETE_IN_SECTION = "## 삭제할 구간의 노선을 입력하세요.";
    private static final String ENTER_STATION_NAME_TO_DELETE_IN_SECTION = "## 삭제할 구간의 역을 입력하세요.";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    private String input() {
        return scanner.next();
    }

    public String inputNumber() {
        System.out.println(ENTER_NUMBER);
        String choice = input();
        // 숫자는 1~4 까지, 알파벳은 Q 만!
        return choice;
    }

    public String inputStationManagement() {
        System.out.println(ENTER_NUMBER);
        String choice = input();
        // 숫자는 1~3 까지, 알파벳은 B 만!
        return choice;
    }

    public String inputSectionManagement() {
        System.out.println(ENTER_NUMBER);
        String choice = input();
        // 숫자는 1~2 까지, 알파벳은 B 만!
        return choice;
    }

    public String inputStationNameToAdd() {
        System.out.println();
        System.out.println(ENTER_STATION_NAME_TO_ADD);
        String name = input();
        return name;
    }

    public String inputStationNameToDelete() {
        System.out.println();
        System.out.println(ENTER_STATION_NAME_TO_DELETE);
        String name = input();
        return name;
    }

    public String inputLineNameToAdd() {
        System.out.println();
        System.out.println(ENTER_LINE_NAME_TO_ADD);
        String name = input();
        return name;
    }

    public String inputUpboundTerminusStation() {
        System.out.println();
        System.out.println(ENTER_UPBOUND_TERMINUS_STATION);
        String name = input();
        return name;
    }

    public String inputDownboundTerminusStation() {
        System.out.println();
        System.out.println(ENTER_DOWNBOUND_TERMINUS_STATION);
        String name = input();
        return name;
    }

    public String inputLineNameToDelete() {
        System.out.println();
        System.out.println(ENTER_LINE_NAME_TO_DELETE);
        String name = input();
        return name;
    }

    public String inputLineName() {
        System.out.println();
        System.out.println(ENTER_LINE_NAME);
        String name = input();
        return name;
    }

    public String inputStationName() {
        System.out.println();
        System.out.println(ENTER_STATION_NAME);
        String name = input();
        return name;
    }

    public String inputOrder() {
        System.out.println();
        System.out.println(ENTER_ORDER);
        String name = input();
        return name;
    }

    public String inputLineNameToDeleteInSection() {
        System.out.println();
        System.out.println(ENTER_LINE_NAME_TO_DELETE_IN_SECTION);
        String name = input();
        return name;
    }

    public String inputStationNameToDeleteInSection() {
        System.out.println();
        System.out.println(ENTER_STATION_NAME_TO_DELETE_IN_SECTION);
        String name = input();
        return name;
    }
}
