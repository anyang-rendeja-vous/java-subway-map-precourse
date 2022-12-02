package subway.controller;

import java.util.List;
import subway.InputReader;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.ui.InputView;
import subway.ui.OutputView;

public class StationController implements Controller {

    private final InputReader inputReader;
    private final InputView inputView;
    private final OutputView outputView;

    public StationController() {
        inputReader = new InputReader();
        inputView = new InputView();
        outputView = new OutputView();
    }

    @Override
    public void execute() {
        inputView.printStationsChoicesMenu();
        String choice = inputReader.getUserInput();
        executeChoice(choice);
    }

    // to fix..
    private void executeChoice(String choice){
        if (choice.equals("1")){
            createStation();
            return;
        }
        if (choice.equals("2")){
            deleteStation();
            return;
        }
        if (choice.equals("3")){
            getStationsList();
            return;
        }
        if (choice.equals("B")){
            return;
        }
        throw new IllegalArgumentException("error!");
    }

    private void createStation(){
        inputView.printStationChoiceOpening();
        try{
            String stationName = inputReader.getUserInput();
            StationRepository.addStation(new Station(stationName));
            outputView.printStationCreationResult();
        }
        catch (Exception exception){
            outputView.printErrorMessage(exception.getMessage());
            createStation();
        }
    }

    private void deleteStation(){
        inputView.printStationToDeleteOpening();
        String stationName = inputReader.getUserInput();
        if (StationRepository.deleteStation(stationName)) // TODO: 에러 처리
            outputView.printStationDeletionResult();
    }

    private void getStationsList(){
        inputView.printStationsListOpening();
        List<Station> stations = StationRepository.stations();
        outputView.printStationsList(stations);
    }

}