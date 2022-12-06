package subway.controller;

import java.util.Scanner;
import java.util.function.Supplier;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.StationRepository;
import subway.domain.menu.LineManageMenu;
import subway.domain.menu.MainMenu;
import subway.domain.menu.SectionManageMenu;
import subway.domain.menu.StationManageMenu;
import subway.service.InitializeService;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private static final String QUIT = "Q";
    private static final String BACK = "B";
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
            if (firstChoiceStr.equals(QUIT)) {
                break;
            }
            firstChoice = Integer.parseInt(firstChoiceStr);
            String secondChoiceStr;
            int secondChoice;
            // 역 관리
            if (MainMenu.MANAGE_STATION.isSamePrefix(firstChoice)) {
                outputView.printStationManagementMenu();
                secondChoiceStr = repeat(inputView::inputStationManagement);
                if (secondChoiceStr.equals(BACK)) {
                    continue;
                }
                secondChoice = Integer.parseInt(secondChoiceStr);
                // 역 등록
                if (StationManageMenu.ADD_STATION.isSamePrefix(secondChoice)) {
                    processToAddStation();
                }
                // 역 삭제
                if (StationManageMenu.DELETE_STATION.isSamePrefix(secondChoice)) {
                    processToDeleteStation();
                }
                // 역 조회
                if (StationManageMenu.SELECT_STATION.isSamePrefix(secondChoice)) {
                    processToPrintStation();
                }
            }
            // 노선 관리
            if (MainMenu.MANAGE_LINE.isSamePrefix(firstChoice)) {
                outputView.printLineManagementMenu();
                secondChoiceStr = repeat(inputView::inputStationManagement);
                if (secondChoiceStr.equals(BACK)) {
                    continue;
                }
                secondChoice = Integer.parseInt(secondChoiceStr);
                // 노선 등록
                if (LineManageMenu.ADD_LINE.isSamePrefix(secondChoice)) {
                    processToAddLine();
                }
                // 노선 삭제
                if (LineManageMenu.DELETE_LINE.isSamePrefix(secondChoice)) {
                    processToDeleteLine();
                }
                // 노선 조회
                if (LineManageMenu.SELECT_LINE.isSamePrefix(secondChoice)) {
                    processToPrintLine();
                }
            }
            // 구간 관리
            if (MainMenu.MANAGE_SECTION.isSamePrefix(firstChoice)) {
                outputView.printSectionManagementMenu();
                secondChoiceStr = repeat(inputView::inputSectionManagement);
                if (secondChoiceStr.equals(BACK)) {
                    continue;
                }
                secondChoice = Integer.parseInt(secondChoiceStr);
                // 구간 등록
                if (SectionManageMenu.ADD_SECTION.isSamePrefix(secondChoice)) {
                    processToAddSection();
                }
                // 구간 삭제
                if (SectionManageMenu.DELETE_SECTION.isSamePrefix(secondChoice)) {
                    processToDeleteSection();
                }
            }
            // 지하철 노선도 출력
            if (MainMenu.PRINT_SUBWAY_MAP.isSamePrefix(firstChoice)) {
                outputView.printSubwayMap();
            }

        }
    }

    private void processToAddStation() {
        try {
            initializeService.createStation(inputView.inputStationNameToAdd());
            outputView.successAddStation();
        } catch(IllegalArgumentException ex) {
            outputView.printError(ex.getMessage());
        }
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
        String upBound = inputView.inputUpBoundTerminusStation();
        String downBound = inputView.inputDownBoundTerminusStation();
        try {
            initializeService.createLine(lineName, upBound, downBound);
            outputView.successAddLine();
        } catch(IllegalArgumentException ex) {
            outputView.printError(ex.getMessage());
        }
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
        try {
            line.addSectionAsSpecified(StationRepository.findStation(stationName),
                    Integer.parseInt(order) - 1); // 1번부터 시작하니까 실제로는 -1 해야함
            outputView.successAddSection();
        } catch(IllegalArgumentException ex) {
            outputView.printError(ex.getMessage());
        }
    }

    private void processToDeleteSection() {
        String lineName = inputView.inputLineNameToDeleteInSection();
        String stationName = inputView.inputStationNameToDeleteInSection();
        Line line = LineRepository.findLine(lineName);
        try {
            line.deleteSection(StationRepository.findStation(stationName));
            outputView.successDeleteSection();
        } catch(IllegalStateException ex) {
            outputView.printError(ex.getMessage());
        }
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
