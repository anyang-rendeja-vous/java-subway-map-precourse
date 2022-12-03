package subway.domain;

import static subway.ui.ErrorMessages.INVALID_STATION_NAME_LENGTH;
import static subway.ui.ErrorMessages.INVALID_STATION_NAME_STRUCT;

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
            throw new IllegalArgumentException(INVALID_STATION_NAME_LENGTH.getMessage());
        }
        if (!name.endsWith("역")){
            throw new IllegalArgumentException(INVALID_STATION_NAME_STRUCT.getMessage());
        }
    }

    public boolean nameMatches(Station station){
        return this.name.equals(station.getName());
    }
}
