package subway.controller;

import static subway.ui.ErrorMessages.INVALID_MAIN_CHOICE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import subway.InputReader;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.ui.InputView;
import subway.ui.OutputView;

public class StationController implements Controller {

    private final InputReader inputReader;
    private final InputView inputView;
    private final OutputView outputView;
    private final Map<String, Runnable> commandMap = new HashMap<>();

    public StationController() {
        inputReader = new InputReader();
        inputView = new InputView();
        outputView = new OutputView();
        mapInit();
    }

    private void mapInit() {
        commandMap.put("1", this::createStation);
        commandMap.put("2", this::deleteStation);
        commandMap.put("3", this::getStationsList);
        commandMap.put("B", this::goBack);
    }

    @Override
    public void execute() {
        inputView.printStationsChoicesMenu();
        runCommand();
    }

    private void runCommand() {
        try {
            String choice = inputReader.getUserInput();
            commandMap.getOrDefault(choice, this::throwException).run();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            runCommand();
        }
    }

    private void createStation() {
        inputView.printStationChoiceOpening();
        try {
            String stationName = inputReader.getUserInput();
            StationRepository.addStation(new Station(stationName));
            outputView.printStationCreationResult();
        } catch (Exception exception) {
            outputView.printErrorMessage(exception.getMessage());
            createStation();
        }
    }

    private void deleteStation() {
        inputView.printStationToDeleteOpening();
        String stationName = inputReader.getUserInput();
        if (StationRepository.deleteStation(stationName)) // TODO: 에러 처리
        {
            outputView.printStationDeletionResult();
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
