package subway.domain;

public class Station {

    private static final String ALREADY_EXISTED_STATION = "이미 등록된 역 이름입니다.";
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
        if (StationRepository.isContain(name)) {
            throw new IllegalArgumentException(ALREADY_EXISTED_STATION);
        }
    }
}
