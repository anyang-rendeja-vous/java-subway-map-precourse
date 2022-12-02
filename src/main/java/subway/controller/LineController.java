package subway.controller;

import subway.InputReader;
import subway.domain.LineRepository;

public class LineController implements Controller {

    private final InputReader inputReader;
    private final LineRepository lineRepository;

    public LineController() {
        inputReader = new InputReader();
        lineRepository = new LineRepository();
    }

    @Override
    public void execute() {
        //String userInput = getUserInput();
    }




}
