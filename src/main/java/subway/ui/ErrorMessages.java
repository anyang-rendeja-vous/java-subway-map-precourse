package subway.ui;

public enum ErrorMessages {
    DUPLICATED_STATION_NAME("이미 등록된 역 이름입니다.");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
