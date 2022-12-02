package subway;

import subway.controller.Controller;

public class ChoiceMapper {

    public static Controller executeByUserChoice(String choice){
        return MainControls
                .getMatchingControls(choice)
                .generatedController();
    }

}
