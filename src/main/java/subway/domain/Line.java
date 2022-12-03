package subway.domain;

import java.util.LinkedList;

public class Line {
    private String name;
    private LinkedList<Station> stations = new LinkedList<>();

    public Line(String name, Station inboundLast, Station outboundLast) {
        this.name = name;
        stations.add(inboundLast);
        stations.add(outboundLast);
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현
}
