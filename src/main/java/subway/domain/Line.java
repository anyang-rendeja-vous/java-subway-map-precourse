package subway.domain;

import java.util.LinkedList;

public class Line {
    private String name;
    private LinkedList<Station> stations = new LinkedList<>();

    public Line(String name, Station upbound, Station downbound) {
        this.name = name;
        stations.addFirst(upbound);
        stations.addLast(downbound);
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현
    public void addSection(Station station) { // 구간 추가 = 노선에 역 추가
        stations.add(1, station);
    }

    // 지정한 위치에 추가
    public void addSectionAsSpecified(Station station, int order) {
        stations.add(order, station);
    }

    public void deleteSection(Station station) {
        stations.remove(station);
    }

    public LinkedList<Station> getStations() {
        return stations;
    }
}
