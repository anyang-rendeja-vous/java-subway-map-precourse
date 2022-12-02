package subway.controller;

import subway.InputReader;
import subway.domain.LineRepository;
import subway.ui.InputView;
import subway.ui.OutputView;

public class LineController implements Controller {

    private final InputReader inputReader;
    private final InputView inputView;
    private final OutputView outputView;


    public LineController() {
        inputReader = new InputReader();
        inputView = new InputView();
        outputView = new OutputView();
    }

    @Override
    public void execute() {
        inputView.printLineChoicesMenu();
        String choice = inputReader.getUserInput();
        executeChoice(choice);
    }

    // to fix..
    private void executeChoice(String choice){
        if (choice.equals("1")){
            createLine();
            return;
        }
        if (choice.equals("2")){
            deleteLine();
            return;
        }
        if (choice.equals("3")){
            getLinesList();
            return;
        }
        if (choice.equals("B")){
            return;
        }
        throw new IllegalArgumentException("error!");
    }

    private void createLine() {
    }

    private void deleteLine() {
    }

    private void getLinesList() {
    }

}
