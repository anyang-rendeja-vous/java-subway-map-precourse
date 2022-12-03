package subway;

import static subway.ui.ErrorMessages.INVALID_MAIN_CHOICE;

import java.util.Arrays;
import java.util.function.Supplier;
import subway.controller.Controller;
import subway.controller.LineController;
import subway.controller.ProgramFinish;
import subway.controller.SectionController;
import subway.controller.StationController;
import subway.controller.SubwayPrinter;

public enum MainControls {
    STATION_CONTROL("1", StationController::new),
    LINE_CONTROL("2", LineController::new),
    SECTION_CONTROL("3", SectionController::new),
    LINES_PRINTER("4", SubwayPrinter::new),
    QUIT("Q", ProgramFinish::new);

    private final String choice;
    private final Supplier<Controller> controllerMaker;

    MainControls(String choice, Supplier<Controller> controllerMaker) {
        this.choice = choice;
        this.controllerMaker = controllerMaker;
    }

    public Controller generatedController(){
        return controllerMaker.get();
    }

    public static MainControls getMatchingControls(String choice){
        return Arrays.stream(MainControls.values())
                .filter(controls -> controls.choiceMatches(choice))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_MAIN_CHOICE.getMessage()));
    }

    public boolean choiceMatches(String choice){
        return this.choice.equals(choice);
    }
}
