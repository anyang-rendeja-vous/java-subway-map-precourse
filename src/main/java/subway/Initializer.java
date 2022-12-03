package subway;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class Initializer {
    private final List<String> initialStations
            = Arrays.asList("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역");
    private final Map<String, List<String>> initialLines
            = Map.of("2호선", List.of("교대역", "강남역", "역삼역"),
            "3호선", List.of("교대역", "남부터미널역", "양재역", "매봉역"),
            "신분당선", List.of("강남역", "양재역", "양재시민의숲역")
    );

    public void subwayInit() {
        initializeStations(initialStations);
        initializeLines(initialLines);
    }

    private void initializeStations(List<String> initialStations) {
        for (String stationName : initialStations) {
            StationRepository.addStation(new Station(stationName));
        }
    }

    private void initializeLines(Map<String, List<String>> initialLines) {
        for (String lineName : initialLines.keySet()){
            initializeSingleLine(lineName, initialLines.get(lineName));
        }
    }

    private void initializeSingleLine(String lineName, List<String> stations) {
        Station[] stationList = stations.stream()
                .map(Station::new)
                .toArray(Station[]::new);
        Line line = new Line(lineName, stationList);
        LineRepository.addLine(line);
    }
}
