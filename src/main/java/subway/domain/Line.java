package subway.domain;

import java.util.LinkedList;

public class Line {

    private static final String LINE_ALREADY_EXISTED_ERROR = "이미 등록된 노선 이름입니다.";
    private static final String LINE_INVALID_SIZE_ERROR = "지하철 노선 이름은 2글자 이상이어야 합니다.";
    private String name;
    private LinkedList<Station> stations = new LinkedList<>();

    public Line(String name, Station upbound, Station downbound) {
        validate(name);
        this.name = name;
        stations.addFirst(upbound);
        stations.addLast(downbound);
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
        if (LineRepository.isContain(name)) {
            throw new IllegalArgumentException(LINE_ALREADY_EXISTED_ERROR);
        }
    }

    private void checkSize(String name) {
        if (name.length() < 2) {
            throw new IllegalArgumentException(LINE_INVALID_SIZE_ERROR);
        }
    }

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
