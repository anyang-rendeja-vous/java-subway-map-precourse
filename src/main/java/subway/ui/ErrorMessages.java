package subway.ui;

public enum ErrorMessages {
    DUPLICATED_STATION_NAME("이미 등록된 역 이름입니다."),
    DUPLICATED_LINE_NAME("이미 등록된 노선 이름입니다."),

    INVALID_LINE_NAME_LENGTH("노선 이름은 두글자 이상이여야 합니다."),
    INVALID_LINE_NAME_STRUCT("형식에 맞게 입력해주세요 (~선)"),
    INVALID_MAIN_CHOICE("선택할 수 없는 기능입니다."),
    INVALID_STATION_NAME_LENGTH("역 이름은 두글자 이상이여야 합니다."),
    INVALID_STATION_NAME_STRUCT("형식에 맞게 입력해주세요 (~역)"),

    NON_EXISTING_LINE("존재하지 않는 노선입니다."),
    NON_EXISTING_STATION("존재하지 않는 역 입니다."),

    FORBIDDEN_STATION_TO_INSERT("해당 노선에 존재하는 역입니다."),
    FORBIDDEN_STATION_TO_DELETE("해당 노선에 존재하지 않는 역입니다."),
    UNABLE_TO_DELETE("더 이상 삭제할 수 없는 역입니다."),
    INVALID_ORDER_INPUT("해당 노선 범위 밖 입니다.");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
