package subway.controller;

import subway.Application;

public class ProgramFinish implements Controller{
    @Override
    public void execute() {
        Application.isContinue = false;
    }
}
