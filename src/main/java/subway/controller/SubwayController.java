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
        String menuChoice;
        while (true) {
            outputView.printInitialMenu();
            menuChoice = repeat(inputView::inputMainNumber);
            if (menuChoice.equals(QUIT)) {
                break;
            }
            selectMenu(menuChoice);
        }
    }

    private void selectMenu(String mainMenuChoice) {
        if (manageStation(mainMenuChoice)) {
            return;
        }
        if (manageLine(mainMenuChoice)) {
            return;
        }
        if (manageSection(mainMenuChoice)) {
            return;
        }
        printSubwayMap(mainMenuChoice);
    }

    private boolean manageStation(String mainMenuChoice) {
        if (MainMenu.MANAGE_STATION.isSamePrefix(mainMenuChoice)) {
            outputView.printStationManagementMenu();
            String menuChoice = repeat(inputView::inputStationManagement);
            if (menuChoice.equals(BACK)) {
                return true;
            }
            if (StationManageMenu.ADD_STATION.isSamePrefix(menuChoice)) {
                processToAddStation();
            }
            if (StationManageMenu.DELETE_STATION.isSamePrefix(menuChoice)) {
                processToDeleteStation();
            }
            if (StationManageMenu.SELECT_STATION.isSamePrefix(menuChoice)) {
                processToPrintStation();
            }
        }
        return false;
    }

    private boolean manageLine(String mainMenuChoice) {
        if (MainMenu.MANAGE_LINE.isSamePrefix(mainMenuChoice)) {
            outputView.printLineManagementMenu();
            String menuChoice = repeat(inputView::inputStationManagement);
            if (menuChoice.equals(BACK)) {
                return true;
            }
            if (LineManageMenu.ADD_LINE.isSamePrefix(menuChoice)) {
                processToAddLine();
            }
            if (LineManageMenu.DELETE_LINE.isSamePrefix(menuChoice)) {
                processToDeleteLine();
            }
            if (LineManageMenu.SELECT_LINE.isSamePrefix(menuChoice)) {
                processToPrintLine();
            }
        }
        return false;
    }

    private boolean manageSection(String mainMenuChoice) {
        if (MainMenu.MANAGE_SECTION.isSamePrefix(mainMenuChoice)) {
            outputView.printSectionManagementMenu();
            String menuChoice = repeat(inputView::inputSectionManagement);
            if (menuChoice.equals(BACK)) {
                return true;
            }
            if (SectionManageMenu.ADD_SECTION.isSamePrefix(menuChoice)) {
                processToAddSection();
            }
            if (SectionManageMenu.DELETE_SECTION.isSamePrefix(menuChoice)) {
                processToDeleteSection();
            }
        }
        return false;
    }

    private void printSubwayMap(String mainMenuChoice) {
        if (MainMenu.PRINT_SUBWAY_MAP.isSamePrefix(mainMenuChoice)) {
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
            validateDeleteStation(deleteStation);
            outputView.successDeleteStation();
        } catch (IllegalArgumentException ex) {
            outputView.printError(ex.getMessage());
        }
    }

    private void validateDeleteStation(String deleteStation) {
        if (LineRepository.isAddedFindStation(deleteStation)) {
            throw new IllegalArgumentException(CANNOT_DELETE_ADDED_STATION);
        }
        if (!StationRepository.deleteStation(deleteStation)) {
            throw new IllegalArgumentException(CANNOT_DELETE_NON_EXISTENT_STATION);
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
            validateDeleteLine();
            outputView.successDeleteLine();
        } catch (IllegalArgumentException ex) {
            outputView.printError(ex.getMessage());
        }
    }

    private void validateDeleteLine() {
        if (!LineRepository.deleteLineByName(inputView.inputLineNameToDelete())) {
            throw new IllegalArgumentException(CANNOT_DELETE_NON_EXISTENT_LINE);
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
            line.addSection(StationRepository.findStation(stationName),
                    Integer.parseInt(order) - 1);
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
        } catch (IllegalArgumentException | IllegalStateException ex) {
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
