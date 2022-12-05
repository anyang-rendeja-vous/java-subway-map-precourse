package subway.domain;

import static subway.ui.ErrorMessages.INVALID_LINE_NAME_LENGTH;
import static subway.ui.ErrorMessages.INVALID_LINE_NAME_STRUCT;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Line extends Domain{
    private static final String LINE_SUFFIX = "선";
    private static final int NAME_LENGTH = 2;

    private final LinkedList<Station> stations = new LinkedList<>();

    public Line(String name, Station...stations) {
        this.name = name;
        Arrays.stream(stations)
                .forEach(this::addStation);
    }

    // 추가 기능 구현
    public static void validateName(String lineInput){
        if (lineInput.length() < NAME_LENGTH){
            throw new IllegalArgumentException(INVALID_LINE_NAME_LENGTH.getMessage());
        }
        if (!lineInput.endsWith(LINE_SUFFIX)){
            throw new IllegalArgumentException(INVALID_LINE_NAME_STRUCT.getMessage());
        }
    }

    public void addStation(Station station){
        stations.add(station);
    }

    public List<Domain> getStations() {
        return Collections.unmodifiableList(stations);
    }

    public boolean stationExists(Station station){
        return stations
                .stream()
                .anyMatch(singleStation -> singleStation.nameMatches(station));
    }

    public boolean isInRange(Integer order){
        return 1 <= order && order <= stations.size();
    }

    public void insertStation(Station station, Integer order){
        stations.add(order - 1, station);
    }
}
