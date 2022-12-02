package subway.controller;

import subway.InputReader;
import subway.domain.StationRepository;

public class StationController implements Controller {

    private final InputReader inputReader;

    public StationController() {
        inputReader = new InputReader();
    }

    @Override
    public void execute() {
        String choice = inputReader.getUserInput();
        executeChoice(choice);
    }

    // to fix..
    private void executeChoice(String choice){
        if (choice.equals("1")){
            createStation();
        }
        if (choice.equals("2")){
            deleteStation();
        }
        if (choice.equals("3")){
            getStationsList();
        }
        if (choice.equals("B")){
            return;
        }
        throw new IllegalArgumentException("error!");
    }

    private void createStation(){
        StationRepository.addStation();
    }

    private void deleteStation(){
        StationRepository.deleteStation();
    }

    private void getStationsList(){
        StationRepository.stations();
    }

}
