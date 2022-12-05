package subway.ui;

public class InputView {
    private static final String HASHTAG = "##";
    private static final String MAIN_MENU_OPENING = "메인 화면";
    private static final String FEATURE_MENU_OPENING = "%s 관리 화면";

    private static final String MAIN_FIRST = "1. 역 관리";
    private static final String MAIN_SECOND = "2. 노선 관리";
    private static final String MAIN_THIRD = "3. 구간 관리";
    private static final String MAIN_FOURTH = "4. 지하철 노선도 출력";
    private static final String MAIN_FINAL = "Q. 종료";

    private static final String FIRST_FEATURE = "1. %s 등록";
    private static final String SECOND_FEATURE = "2. %s 삭제";
    private static final String THIRD_FEATURE = "3. %s 조회";
    private static final String BACK = "B. 돌아가기";

    private static final String USERS_CHOICE_OPENING = "원하는 기능을 선택하세요.";
    private static final String TO_CREATE_OPENING = "등록할 %s 이름을 입력하세요.";
    private static final String TO_DELETE_OPENING = "삭제할 %s 이름을 입력하세요.";
    private static final String LAST_STATION_OPENING = "등록할 노선의 %s 종점역 이름을 입력하세요.";

    private static final String LIST_OPENING = "%s 목록";
    private static final String SUBWAY_LIST = "지하철 노선도";

    public void printMainChoicesMenu() {
        System.out.println();
        System.out.println(HASHTAG + " " + MAIN_MENU_OPENING);
        System.out
                .println(MAIN_FIRST + "\n" + MAIN_SECOND + "\n" + MAIN_THIRD + "\n" + MAIN_FOURTH + "\n" + MAIN_FINAL);
        System.out.println();
    }

    public void printUsersChoiceOpening() {
        System.out.println(HASHTAG + " " + USERS_CHOICE_OPENING);
    }

    public void printCreationChoiceOpening(String subject) {
        System.out.println();
        System.out.printf(HASHTAG + " " + TO_CREATE_OPENING + "\n", subject);
    }

    public void printFeaturesMenu(String subject) {
        System.out.println();
        System.out.printf(HASHTAG + " " + FEATURE_MENU_OPENING + "\n", subject);
        System.out.printf(FIRST_FEATURE + "\n" + SECOND_FEATURE + "\n" + THIRD_FEATURE + "\n" + BACK + "\n", subject, subject, subject);
        System.out.println();
    }

    public void printDeleteChoiceOpening(String subject) {
        System.out.println();
        System.out.printf(HASHTAG + " " + TO_DELETE_OPENING + "\n", subject);
    }

    public void printListOpening(String subject) {
        System.out.println();
        System.out.printf(HASHTAG + " " + LIST_OPENING + "\n", subject);
    }

    public void printLastStationChoiceOpening(String subject) {
        System.out.println();
        System.out.printf(HASHTAG + " " + LAST_STATION_OPENING + "\n", subject);
    }

    public void printTotalSubwayOpening() {
        System.out.println();
        System.out.println(HASHTAG + " " + SUBWAY_LIST);
    }

}
