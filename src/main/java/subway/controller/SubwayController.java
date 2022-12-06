package subway.controller;

import java.util.Scanner;
import java.util.function.Supplier;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.StationRepository;
import subway.service.InitializeService;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final OutputView outputView;
    private final InitializeService initializeService;

    public SubwayController(Scanner scanner) {
        this.inputView = new InputView(scanner);
        this.outputView = new OutputView();
        this.initializeService = new InitializeService();
    }

    public void run() {
        initializeService.initializeData();

        String firstChoiceStr;
        int firstChoice;
        while (true) {
            outputView.printInitialMenu();
            firstChoiceStr = repeat(inputView::inputNumber);
            if (firstChoiceStr.equals("Q")) {
                break;
            }
            firstChoice = Integer.parseInt(firstChoiceStr);
            String secondChoiceStr;
            int secondChoice;
            // 역 관리
            if (firstChoice == 1) {
                outputView.printStationManagementMenu();
                secondChoiceStr = repeat(inputView::inputStationManagement);
                if (secondChoiceStr.equals("B")) {
                    continue;
                }
                secondChoice = Integer.parseInt(secondChoiceStr);
                // 역 등록
                if (secondChoice == 1) {
                    processToAddStation();
                }
                // 역 삭제
                if (secondChoice == 2) {
                    processToDeleteStation();
                }
                // 역 조회
                if (secondChoice == 3) {
                    processToPrintStation();
                }
            }
            // 노선 관리
            if (firstChoice == 2) {
                outputView.printLineManagementMenu();
                secondChoiceStr = repeat(inputView::inputStationManagement);
                if (secondChoiceStr.equals("B")) {
                    continue;
                }
                secondChoice = Integer.parseInt(secondChoiceStr);
                // 노선 등록
                if (secondChoice == 1) {
                    processToAddLine();
                }
                // 노선 삭제
                if (secondChoice == 2) {
                    processToDeleteLine();
                }
                // 노선 조회
                if (secondChoice == 3) {
                    processToPrintLine();
                }
            }
            // 구간 관리
            if (firstChoice == 3) {
                outputView.printSectionManagementMenu();
                secondChoiceStr = repeat(inputView::inputSectionManagement);
                if (secondChoiceStr.equals("B")) {
                    continue;
                }
                secondChoice = Integer.parseInt(secondChoiceStr);
                // 구간 등록
                if (secondChoice == 1) {
                    processToAddSection();
                }
                // 구간 삭제
                if (secondChoice == 2) {
                    processToDeleteSection();
                }
            }
            // 지하철 노선도 출력
            if (firstChoice == 4) {
                outputView.printSubwayMap();
            }

        }
    }

    private void processToAddStation() {
        initializeService.createStation(inputView.inputStationNameToAdd());
        outputView.successAddStation();
    }

    private void processToDeleteStation() {
        String deleteStation = inputView.inputStationNameToDelete();
        try {
            if (LineRepository.isContain(deleteStation)) {
                throw new IllegalArgumentException("노선에 등록되어 있는 역은 삭제할 수 없습니다.");
            }
            if (!StationRepository.deleteStation(deleteStation)) {
                throw new IllegalArgumentException("존재하지 않는 역입니다.");
            }
            outputView.successDeleteStation();
        } catch(IllegalArgumentException ex) {
            outputView.printError(ex.getMessage());
        }
    }

    private void processToPrintStation() {
        outputView.printAllStations(StationRepository.stations());
    }

    private void processToAddLine() {
        String lineName = inputView.inputLineNameToAdd();
        String upbound = inputView.inputUpboundTerminusStation();
        String downbound = inputView.inputDownboundTerminusStation();
        initializeService.createLine(lineName, upbound, downbound);
        outputView.successAddLine();
    }

    private void processToDeleteLine() {
        try {
            if (!LineRepository.deleteLineByName(inputView.inputLineNameToDelete())) {
                throw new IllegalArgumentException("존재하지 않는 노선입니다.");
            }
            outputView.successDeleteLine();
        } catch(IllegalArgumentException ex) {
            outputView.printError(ex.getMessage());
        }
    }

    private void processToPrintLine() {
        outputView.printAllLines(LineRepository.lines());
    }

    private void processToAddSection() {
        String lineName = inputView.inputLineName();
        String stationName = inputView.inputStationName();
        String order = inputView.inputOrder();
        Line line = LineRepository.findLine(lineName);
        line.addSectionAsSpecified(StationRepository.findStation(stationName),
                Integer.parseInt(order) - 1); // 1번부터 시작하니까 실제로는 -1 해야함
        outputView.successAddSection();
    }

    private void processToDeleteSection() {
        String lineName = inputView.inputLineNameToDeleteInSection();
        String stationName = inputView.inputStationNameToDeleteInSection();
        Line line = LineRepository.findLine(lineName);
        line.deleteSection(StationRepository.findStation(stationName));
        outputView.successDeleteSection();
    }


    private <T> T repeat(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException ex) {
            outputView.printError(ex.getMessage());
            return repeat(inputReader);
        }
    }
}
