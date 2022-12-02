package subway.ui;

public class InputView {
    private static final String HASHTAG = "##";
    private static final String MAIN_MENU_OPENING = "메인 화면";
    private static final String STATION_MENU_OPENING = "역 관리 화면";

    private static final String MAIN_FIRST = "1. 역 관리";
    private static final String MAIN_SECOND = "2. 노선 관리";
    private static final String MAIN_THIRD = "3. 구간 관리";
    private static final String MAIN_FOURTH = "4. 지하철 노선도 출력";
    private static final String MAIN_FINAL = "Q. 종료";

    private static final String STATION_FIRST = "1. 역 등록";
    private static final String STATION_SECOND = "2. 역 삭제";
    private static final String STATION_THIRD = "3. 역 조회";

    private static final String BACK = "B. 돌아가기";

    private static final String USERS_CHOICE_OPENING = "원하는 기능을 선택하세요.";
    private static final String STATION_CHOICE_OPENING = "등록할 역 이름을 입력하세요.";

    public void printMainChoicesMenu() {
        System.out.println(HASHTAG + " " + MAIN_MENU_OPENING);
        System.out
                .println(MAIN_FIRST + "\n" + MAIN_SECOND + "\n" + MAIN_THIRD + "\n" + MAIN_FOURTH + "\n" + MAIN_FINAL);
    }

    public void printUsersChoiceOpening() {
        System.out.println(HASHTAG + " " + USERS_CHOICE_OPENING);
    }

    public void printStationChoiceOpening() {
        System.out.println(HASHTAG + " " + STATION_CHOICE_OPENING);
    }

    public void printStationsChoicesMenu(){
        System.out.println(HASHTAG + " " + STATION_MENU_OPENING);
        System.out
                .println(STATION_FIRST + "\n" + STATION_SECOND + "\n" + STATION_THIRD + "\n" + BACK);
    }


}
