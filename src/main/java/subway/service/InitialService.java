package subway.service;

import java.util.Arrays;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.domain.initial.StationType;

public class InitialService {

    private static final String LINE_TWO = "2호선";
    private static final String LINE_THREE = "3호선";
    private static final String LINE_SHINBUNDANG = "신분당선";

    public void initializeData() {
        setStations();
        setLines();
        setSections();
    }

    private void setStations() {
        Arrays.stream(StationType.values())
                        .forEach(stationType -> createStation(stationType.getName()));
    }

    public void createStation(String stationName) {
        StationRepository.addStation(new Station(stationName));
    }

    private void setLines() {
        createLine(LINE_TWO, StationType.GYODAE.getName(), StationType.YEOKSAM.getName());
        createLine(LINE_THREE, StationType.GYODAE.getName(), StationType.MAEBONG.getName());
        createLine(LINE_SHINBUNDANG, StationType.GANGNAM.getName(), StationType.YANGJAE_CITIZENS_FOREST.getName());
    }

    public void createLine(String lineName, String upBound, String downBound) {
        LineRepository.addLine(
                new Line(lineName, StationRepository.findStation(upBound), StationRepository.findStation(downBound)));
    }

    private void setSections() {
        addSections(LINE_TWO, StationType.GANGNAM.getName());
        addSections(LINE_THREE, StationType.SOUTH_TERMINAL.getName());
        addSections(LINE_THREE, StationType.YANGJAE.getName());
        addSections(LINE_SHINBUNDANG, StationType.YANGJAE.getName());
    }

    private void addSections(String lineName, String StationName) {
        LineRepository.findLine(lineName)
                .addSectionToFirst(StationRepository.findStation(StationName));
    }
}
