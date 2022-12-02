package subway.controller;

import static subway.ui.ErrorMessages.INVALID_MAIN_CHOICE;

import java.util.HashMap;
import java.util.Map;
import subway.InputReader;
import subway.ui.InputView;
import subway.ui.OutputView;

public class LineController implements Controller {
    private static final String CREATE = "1";
    private static final String DELETE = "2";
    private static final String GET_LIST = "3";
    private static final String BACK = "B";

    private final InputReader inputReader;
    private final InputView inputView;
    private final OutputView outputView;
    private final Map<String, Runnable> commandMap = new HashMap<>();


    public LineController() {
        inputReader = new InputReader();
        inputView = new InputView();
        outputView = new OutputView();
        mapInit();
    }

    private void mapInit() {
        commandMap.put(CREATE, this::createLine);
        commandMap.put(DELETE, this::deleteLine);
        commandMap.put(GET_LIST, this::getLinesList);
        commandMap.put(BACK, this::goBack);
    }

    @Override
    public void execute() {
        inputView.printLineChoicesMenu();
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

    private void createLine() {
    }

    private void deleteLine() {
    }

    private void getLinesList() {
    }

    private void goBack() {
        return;
    }

    private void throwException() {
        throw new IllegalArgumentException(INVALID_MAIN_CHOICE.getMessage());
    }

}
