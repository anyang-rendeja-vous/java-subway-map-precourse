package subway.domain;

public class Station {

    private static final String ALREADY_EXISTED_ERROR = "이미 등록된 역 이름입니다.";
    private static final String INVALID_SIZE_ERROR = "지하철 역 이름은 2글자 이상이어야 합니다.";
    private String name;

    public Station(String name) {
        validate(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현
    public void validate(String name) {
        checkSize(name);
        checkConflict(name);
    }

    private void checkConflict(String name) {
        if (StationRepository.isContain(name)) {
            throw new IllegalArgumentException(ALREADY_EXISTED_ERROR);
        }
    }

    private void checkSize(String name) {
        if (name.length() < 2) {
            throw new IllegalArgumentException(INVALID_SIZE_ERROR);
        }
    }
}
