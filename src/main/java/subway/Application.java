package subway;

import static subway.ChoiceMapper.executeByUserChoice;
import static subway.InputReader.getUserInput;

import subway.controller.Controller;
import subway.ui.InputView;

public class Application {
    public static boolean isContinue = true;

    public static void main(String[] args) {
        InputView inputView = new InputView();
        while (isContinue){
            inputView.printMainChoicesMenu();
            inputView.printUsersChoiceOpening();
            Controller controller = executeByUserChoice(getUserInput());
            controller.execute();
        }

    }
}
