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

        String firstChoiceStr;
        int firstChoice;
        while(true) {
            outputView.printInitialMenu();
            firstChoiceStr = inputView.inputNumber();
            if (firstChoiceStr.equals("Q")) {
                break;
            }

            firstChoice = Integer.parseInt(firstChoiceStr);
            String secondChoiceStr;
            int secondChoice;
            // 역 관리
            if (firstChoice == 1) {
                outputView.printStationManagementMenu();
                secondChoiceStr = inputView.inputStationManagement();
                if (secondChoiceStr.equals("B")) {
                    continue;
                }

                secondChoice = Integer.parseInt(secondChoiceStr);

                // 역 등록
                if (secondChoice == 1) {
                    createStation(inputView.inputStationNameToAdd());
                    outputView.successAddStation();
                }

                // 역 삭제
                if (secondChoice == 2) {
                    StationRepository.deleteStation(
                            inputView.inputStationNameToDelete()); // 삭제할 수 없다면 false 반환 -> 예외 처리
                    outputView.successDeleteStation();
                }

                // 역 조회
                if (secondChoice == 3) {
                    outputView.printAllStations(StationRepository.stations());
                }
            }

            // 노선 관리
            if (firstChoice == 2) {
                outputView.printLineManagementMenu();
                secondChoiceStr = inputView.inputStationManagement();
                if (secondChoiceStr.equals("B")) {
                    continue;
                }

                secondChoice = Integer.parseInt(secondChoiceStr);

                // 노선 등록
                if (secondChoice == 1) {
                    String lineName = inputView.inputLineNameToAdd();
                    String upbound = inputView.inputUpboundTerminusStation();
                    String downbound = inputView.inputDownboundTerminusStation();
                    createLine(lineName, upbound, downbound);
                    outputView.successAddLine();
                }

                // 노선 조회
                if (secondChoice == 3) {
                    outputView.printAllLines(LineRepository.lines());
                }
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
        createLine("2호선", "교대역", "역삼역");
        createLine("3호선", "교대역", "매봉역");
        createLine("신분당선", "강남역", "양재시민의숲역");
    }

    private void createLine(String lineName, String upbound, String downbound) {
        LineRepository.addLine(new Line(lineName, StationRepository.findStation(upbound), StationRepository.findStation(downbound)));
    }

    private void setSections() {
//        addSections("2호선", "교대역");
        addSections("2호선", "강남역");
//        addSections("2호선", "역삼역");

//        addSections("3호선", "교대역");
        addSections("3호선", "남부터미널역");
        addSections("3호선", "양재역");
//        addSections("3호선", "매봉역");

//        addSections("신분당선", "강남역");
        addSections("신분당선", "양재역");
//        addSections("신분당선", "양재시민의숲역");
    }

    private void addSections(String lineName, String StationName) {
        LineRepository.findLine(lineName).addSection(StationRepository.findStation(StationName));
    }
}
