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
import subway.service.InitialService;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private static final String QUIT = "Q";
    private static final String BACK = "B";
    private static final String CANNOT_DELETE_ADDED_STATION = "노선에 등록되어 있는 역은 삭제할 수 없습니다.";
    private static final String CANNOT_DELETE_NON_EXISTENT_STATION = "존재하지 않는 역은 삭제할 수 없습니다.";
    private static final String CANNOT_DELETE_NON_EXISTENT_LINE = "존재하지 않는 노선은 삭제할 수 없습니다.";
    private final InputView inputView;
    private final OutputView outputView;
    private final InitialService initialService;

    public SubwayController(Scanner scanner) {
        this.inputView = new InputView(scanner);
        this.outputView = new OutputView();
        this.initialService = new InitialService();
    }

    public void run() {
        initialService.initializeData();
        String mainMenuChoice;
        int mainMenuNumber;
        while (true) {
            outputView.printInitialMenu();
            mainMenuChoice = repeat(inputView::inputNumber);
            if (mainMenuChoice.equals(QUIT)) {
                break;
            }
            mainMenuNumber = Integer.parseInt(mainMenuChoice);
            if (manageStation(mainMenuNumber)) {
                continue;
            }
            if (manageLine(mainMenuNumber)) {
                continue;
            }
            if (manageSection(mainMenuNumber)) {
                continue;
            }
            printSubwayMap(mainMenuNumber);
        }
    }

    private boolean manageStation(int mainMenuNumber) {
        if (MainMenu.MANAGE_STATION.isSamePrefix(mainMenuNumber)) {
            outputView.printStationManagementMenu();
            String menuChoice = repeat(inputView::inputStationManagement);
            if (menuChoice.equals(BACK)) {
                return true;
            }
            int menuNumber = Integer.parseInt(menuChoice);
            if (StationManageMenu.ADD_STATION.isSamePrefix(menuNumber)) {
                processToAddStation();
            }
            if (StationManageMenu.DELETE_STATION.isSamePrefix(menuNumber)) {
                processToDeleteStation();
            }
            if (StationManageMenu.SELECT_STATION.isSamePrefix(menuNumber)) {
                processToPrintStation();
            }
        }
        return false;
    }

    private boolean manageLine(int mainMenuNumber) {
        if (MainMenu.MANAGE_LINE.isSamePrefix(mainMenuNumber)) {
            outputView.printLineManagementMenu();
            String menuChoice = repeat(inputView::inputStationManagement);
            if (menuChoice.equals(BACK)) {
                return true;
            }
            int menuNumber = Integer.parseInt(menuChoice);
            if (LineManageMenu.ADD_LINE.isSamePrefix(menuNumber)) {
                processToAddLine();
            }
            if (LineManageMenu.DELETE_LINE.isSamePrefix(menuNumber)) {
                processToDeleteLine();
            }
            if (LineManageMenu.SELECT_LINE.isSamePrefix(menuNumber)) {
                processToPrintLine();
            }
        }
        return false;
    }

    private boolean manageSection(int mainMenuNumber) {
        if (MainMenu.MANAGE_SECTION.isSamePrefix(mainMenuNumber)) {
            outputView.printSectionManagementMenu();
            String menuChoice = repeat(inputView::inputSectionManagement);
            if (menuChoice.equals(BACK)) {
                return true;
            }
            int menuNumber = Integer.parseInt(menuChoice);
            if (SectionManageMenu.ADD_SECTION.isSamePrefix(menuNumber)) {
                processToAddSection();
            }
            if (SectionManageMenu.DELETE_SECTION.isSamePrefix(menuNumber)) {
                processToDeleteSection();
            }
        }
        return false;
    }

    private void printSubwayMap(int mainMenuNumber) {
        if (MainMenu.PRINT_SUBWAY_MAP.isSamePrefix(mainMenuNumber)) {
            outputView.printMap();
        }
    }

    private void processToAddStation() {
        try {
            initialService.createStation(inputView.inputStationNameToAdd());
            outputView.successAddStation();
        } catch (IllegalArgumentException ex) {
            outputView.printError(ex.getMessage());
        }
    }

    private void processToDeleteStation() {
        String deleteStation = inputView.inputStationNameToDelete();
        try {
            if (LineRepository.isContain(deleteStation)) {
                throw new IllegalArgumentException(CANNOT_DELETE_ADDED_STATION);
            }
            if (!StationRepository.deleteStation(deleteStation)) {
                throw new IllegalArgumentException(CANNOT_DELETE_NON_EXISTENT_STATION);
            }
            outputView.successDeleteStation();
        } catch (IllegalArgumentException ex) {
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
            initialService.createLine(lineName, upBound, downBound);
            outputView.successAddLine();
        } catch (IllegalArgumentException ex) {
            outputView.printError(ex.getMessage());
        }
    }

    private void processToDeleteLine() {
        try {
            if (!LineRepository.deleteLineByName(inputView.inputLineNameToDelete())) {
                throw new IllegalArgumentException(CANNOT_DELETE_NON_EXISTENT_LINE);
            }
            outputView.successDeleteLine();
        } catch (IllegalArgumentException ex) {
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
        } catch (IllegalArgumentException ex) {
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
        } catch (IllegalStateException ex) {
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
