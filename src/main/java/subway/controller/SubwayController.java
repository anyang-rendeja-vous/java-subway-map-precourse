package subway.controller;

import java.util.Scanner;
import java.util.stream.Collectors;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final OutputView outputView;

    public SubwayController(Scanner scanner) {
        this.inputView = new InputView(scanner);
        this.outputView = new OutputView();
    }

    public void run() {
        initialSettings();
        outputView.printMenu();
        int choice = Integer.parseInt(inputView.inputNumber());


//        System.out.println(StationRepository.stations().stream()
//                .map(Station::getName)
//                .collect(Collectors.toList()));
    }

    private void initialSettings() {
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

    private void createStation(String stationName) {
        StationRepository.addStation(new Station(stationName));
    }

    private void setLines() {
        createLine("2호선");
        createLine("3호선");
        createLine("신분당선");
    }

    private void createLine(String lineName) {
        LineRepository.addLine(new Line(lineName));
    }

    private void setSections() {
        addSections("2호선", "교대역");
        addSections("2호선", "강남역");
        addSections("2호선", "역삼역");

        addSections("3호선", "교대역");
        addSections("3호선", "남부터미널역");
        addSections("3호선", "양재역");
        addSections("3호선", "매봉역");

        addSections("신분당선", "강남역");
        addSections("신분당선", "양재역");
        addSections("신분당선", "양재시민의숲역");
    }

    private void addSections(String lineName, String StationName) {
        LineRepository.findLine(lineName).addSection(StationRepository.findStation(StationName));
    }
}
