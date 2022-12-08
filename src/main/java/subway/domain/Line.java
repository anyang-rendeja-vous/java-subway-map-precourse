package subway.domain;

import java.util.LinkedList;

public class Line {

    private static final String LINE_ALREADY_EXISTED_ERROR = "이미 등록된 노선 이름입니다.";
    private static final String LINE_INVALID_SIZE_ERROR = "지하철 노선 이름은 2글자 이상이어야 합니다.";
    private static final String CANNOT_DELETE_STATION_ERROR = "노선에 포함된 역이 두 개 이하인 경우 역을 제거할 수 없습니다.";
    private static final String INVALID_ORDER_ERROR = "순서는 1 부터 %d 까지 가능합니다.";
    private String name;
    private LinkedList<Station> stations = new LinkedList<>();

    public Line(String name, Station upBound, Station downBound) {
        validate(name);
        this.name = name;
        stations.addFirst(upBound);
        stations.addLast(downBound);
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

    public void addSectionToFirst(Station station) {
        stations.add(1, station);
    }

    public void addSection(Station station, int order) {
        if (order < 1 || order > stations.size()) {
            throw new IllegalArgumentException(String.format(INVALID_ORDER_ERROR, stations.size() + 1));
        }
        stations.add(order, station);
    }

    public void deleteSection(Station station) {
        if (stations.size() <= 2) {
            throw new IllegalStateException(CANNOT_DELETE_STATION_ERROR);
        }
        stations.remove(station);
    }

    public LinkedList<Station> getStations() {
        return stations;
    }
}
