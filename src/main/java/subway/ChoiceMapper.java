package subway;

import java.util.Arrays;
import subway.controller.LineController;
import subway.controller.SectionController;
import subway.controller.StationController;
import subway.controller.SubwayPrinter;

public class ChoiceMapper {
    private LineController lineController;
    private SectionController sectionController;
    private StationController stationController;
    private SubwayPrinter subwayPrinter;

    public MainControls executeByUserChoice(String choice){
        return Arrays.stream(MainControls.values())
                .filter(x -> x.isMatchingController(choice))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 선택할 수 없는 기능입니다."));
    }

}
