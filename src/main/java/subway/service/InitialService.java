package subway.service;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class InitialService {

    private static final String STATION_GYODAE = "교대역";
    private static final String STATION_GANGNAM = "강남역";
    private static final String STATION_YEOKSAM = "역삼역";
    private static final String STATION_SOUTH_TERMINAL = "남부터미널역";
    private static final String STATION_YANGJAE = "양재역";
    private static final String STATION_YANGJAE_CITIZENS_FOREST = "양재시민의숲역";
    private static final String STATION_MAEBONG = "매봉역";

    private static final String LINE_TWO = "2호선";
    private static final String LINE_THREE = "3호선";
    private static final String LINE_SHINBUNDANG = "신분당선";

    public void initializeData() {
        setStations();
        setLines();
        setSections();
    }

    private void setStations() {
        createStation(STATION_GYODAE);
        createStation(STATION_GANGNAM);
        createStation(STATION_YEOKSAM);
        createStation(STATION_SOUTH_TERMINAL);
        createStation(STATION_YANGJAE);
        createStation(STATION_YANGJAE_CITIZENS_FOREST);
        createStation(STATION_MAEBONG);
    }

    public void createStation(String stationName) {
        StationRepository.addStation(new Station(stationName));
    }

    private void setLines() {
        createLine(LINE_TWO, STATION_GYODAE, STATION_YEOKSAM);
        createLine(LINE_THREE, STATION_GYODAE, STATION_MAEBONG);
        createLine(LINE_SHINBUNDANG, STATION_GANGNAM, STATION_YANGJAE_CITIZENS_FOREST);
    }

    public void createLine(String lineName, String upBound, String downBound) {
        LineRepository.addLine(
                new Line(lineName, StationRepository.findStation(upBound), StationRepository.findStation(downBound)));
    }

    private void setSections() {
        addSections(LINE_TWO, STATION_GANGNAM);
        addSections(LINE_THREE, STATION_SOUTH_TERMINAL);
        addSections(LINE_THREE, STATION_YANGJAE);
        addSections(LINE_SHINBUNDANG, STATION_YANGJAE);
    }

    private void addSections(String lineName, String StationName) {
        LineRepository.findLine(lineName)
                .addSectionToFirst(StationRepository.findStation(StationName));
    }
}
