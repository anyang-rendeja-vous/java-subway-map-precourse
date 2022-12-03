package subway.domain;

import java.util.Arrays;
import java.util.LinkedList;

public class Line {
    private String name;
    private LinkedList<Station> stations = new LinkedList<>();

    public Line(String name, Station...stations) {
        this.name = name;
        Arrays.stream(stations)
                .forEach(this::addStation);
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현
    public void addStation(Station station){
        stations.add(station);
    }
}
