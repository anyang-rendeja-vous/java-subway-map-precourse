package subway.domain;

import java.util.Stack;

public class Line {
    private String name;
    private Stack<Station> stations = new Stack<>();

    public Line(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현
    public void addSection(Station station) { // 구간 추가 = 노선에 역 추가
        stations.add(station);
    }
}
