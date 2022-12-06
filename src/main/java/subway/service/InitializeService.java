package subway.service;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class InitializeService {

    public void initializeData() {
        setStations();
        setLines();
        setSections();
    }

    private void setStations() {
        createStation("교대역");
        createStation("강남역");
        createStation("역삼역");
        createStation("남부터미널역");
        createStation("양재역");
        createStation("양재시민의숲역");
        createStation("매봉역");
    }

    public void createStation(String stationName) {
        StationRepository.addStation(new Station(stationName));
    }

    private void setLines() {
        createLine("2호선", "교대역", "역삼역");
        createLine("3호선", "교대역", "매봉역");
        createLine("신분당선", "강남역", "양재시민의숲역");
    }

    public void createLine(String lineName, String upbound, String downbound) {
        LineRepository.addLine(
                new Line(lineName, StationRepository.findStation(upbound), StationRepository.findStation(downbound)));
    }

    private void setSections() {
        addSections("2호선", "강남역");
        addSections("3호선", "남부터미널역");
        addSections("3호선", "양재역");
        addSections("신분당선", "양재역");
    }

    private void addSections(String lineName, String StationName) {
        LineRepository.findLine(lineName).addSection(StationRepository.findStation(StationName));
    }
}
