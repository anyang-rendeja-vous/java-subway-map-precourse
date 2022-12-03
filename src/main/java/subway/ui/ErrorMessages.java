package subway.ui;

public enum ErrorMessages {
    INVALID_MAIN_CHOICE("선택할 수 없는 기능입니다."),

    DUPLICATED_STATION_NAME("이미 등록된 역 이름입니다."),

    NONEXISTING_STATION("존재하지 않는 역 입니다."),
    INVALID_STATION_NAME_LENGTH("역 이름은 두글자 이상이여야 합니다."),
    INVALID_STATION_NAME_STRUCT("형식에 맞게 입력해주세요 (~역)"),
    INVALID_LINE_NAME_LENGTH("노선 이름은 두글자 이상이여야 합니다."),
    INVALID_LINE_NAME_STRUCT("형식에 맞게 입력해주세요 (~호선)");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
