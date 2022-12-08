package subway.service;

import java.util.Arrays;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.domain.initial.LineType;
import subway.domain.initial.StationType;

public class InitialService {

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
        createLine(LineType.TWO.getName(), StationType.GYODAE.getName(), StationType.YEOKSAM.getName());
        createLine(LineType.THREE.getName(), StationType.GYODAE.getName(), StationType.MAEBONG.getName());
        createLine(LineType.SHINBUNDANG.getName(), StationType.GANGNAM.getName(),
                StationType.YANGJAE_CITIZENS_FOREST.getName());
    }

    public void createLine(String lineName, String upBound, String downBound) {
        LineRepository.addLine(
                new Line(lineName, StationRepository.findStation(upBound), StationRepository.findStation(downBound)));
    }

    private void setSections() {
        addSections(LineType.TWO.getName(), StationType.GANGNAM.getName());
        addSections(LineType.THREE.getName(), StationType.SOUTH_TERMINAL.getName());
        addSections(LineType.THREE.getName(), StationType.YANGJAE.getName());
        addSections(LineType.SHINBUNDANG.getName(), StationType.YANGJAE.getName());
    }

    private void addSections(String lineName, String StationName) {
        LineRepository.findLine(lineName)
                .addSectionToFirst(StationRepository.findStation(StationName));
    }
}
