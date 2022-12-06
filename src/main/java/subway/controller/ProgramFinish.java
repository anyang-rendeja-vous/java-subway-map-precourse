package subway.controller;

import subway.Application;
import subway.ui.OutputView;

public class ProgramFinish implements Controller{
    private final OutputView outputView = new OutputView();

    @Override
    public void execute() {
        Application.isContinue = false;
        outputView.printApplicationEnd();
    }
}
