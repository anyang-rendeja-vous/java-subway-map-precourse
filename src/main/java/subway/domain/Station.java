package subway.domain;

public class Station {
    private String name;

    public Station(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현
    private void validateName(String name){
        if (name.length() < 2){
            throw new IllegalArgumentException("역 이름은 두글자 이상이여야 합니다.");
        }
        if (!name.contains("역")){
            throw new IllegalArgumentException("형식에 맞게 입력해주세요 (~역)");
        }
    }

    public boolean nameMatches(Station station){
        return this.name.equals(station.getName());
    }
}
