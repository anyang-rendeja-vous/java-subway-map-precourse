package subway;

import java.util.Arrays;
import subway.controller.Controller;
import subway.controller.LineController;
import subway.controller.SectionController;
import subway.controller.StationController;
import subway.controller.SubwayPrinter;

public class ChoiceMapper {
    private LineController lineController;
    private SectionController sectionController;
    private StationController stationController;
    private SubwayPrinter subwayPrinter;

    public Controller executeByUserChoice(String choice){
        return MainControls
                .getMatchingControls(choice)
                .generatedController();
    }

}
