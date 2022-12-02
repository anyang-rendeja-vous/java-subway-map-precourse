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
}
