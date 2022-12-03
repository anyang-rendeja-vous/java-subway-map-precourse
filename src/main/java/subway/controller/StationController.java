package subway.controller;

import static subway.InputReader.getUserInput;
import static subway.ui.ErrorMessages.INVALID_MAIN_CHOICE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.ui.InputView;
import subway.ui.OutputView;

public class StationController implements Controller {
    private static final String CREATE = "1";
    private static final String DELETE = "2";
    private static final String GET_LIST = "3";
    private static final String BACK = "B";

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<String, Runnable> commandMap = new HashMap<>();

    public StationController() {
        inputView = new InputView();
        outputView = new OutputView();
        mapInit();
    }

    private void mapInit() {
        commandMap.put(CREATE, this::createStation);
        commandMap.put(DELETE, this::deleteStation);
        commandMap.put(GET_LIST, this::getStationsList);
        commandMap.put(BACK, this::goBack);
    }

    @Override
    public void execute() {
        inputView.printStationsChoicesMenu();
        runCommand();
    }

    private void runCommand() {
        inputView.printUsersChoiceOpening();
        try {
            String choice = getUserInput();
            commandMap.getOrDefault(choice, this::throwException).run();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            runCommand();
        }
    }

    private void createStation() {
        inputView.printStationChoiceOpening();
        try {
            String stationName = getUserInput();
            StationRepository.addStation(new Station(stationName));
            outputView.printStationCreationResult();
        } catch (Exception exception) {
            outputView.printErrorMessage(exception.getMessage());
            createStation();
        }
    }

    private void deleteStation() {
        inputView.printStationToDeleteOpening();
        try {
            String stationName = getUserInput();
            StationRepository.deleteStationByName(stationName);
        } catch (IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            deleteStation();
        }
    }

    private void getStationsList() {
        inputView.printStationsListOpening();
        List<Station> stations = StationRepository.stations();
        outputView.printStationsList(stations);
    }

    private void goBack() {
        return;
    }

    private void throwException() {
        throw new IllegalArgumentException(INVALID_MAIN_CHOICE.getMessage());
    }

}
