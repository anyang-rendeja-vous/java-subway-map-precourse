package subway.controller;

import java.util.Scanner;
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
        outputView.printInitialMenu();
        int choice = Integer.parseInt(inputView.inputNumber());

        int secondChoice;
        // 역 관리
        if (choice == 1) {
            outputView.printStationManagementMenu();
            secondChoice = Integer.parseInt(inputView.inputStationManagement());

            // 역 등록
            if (secondChoice == 1) {
                createStation(inputView.inputStationName());
                outputView.successAddStation();
            }

            // 역 조회
            if (secondChoice == 3) {
                outputView.printAllStations(StationRepository.stations());
            }
        }
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
