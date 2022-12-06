package subway;

import static subway.ControllerMapper.executeByUserChoice;
import static subway.InputReader.getUserInput;

import subway.controller.Controller;
import subway.ui.InputView;
import subway.ui.OutputView;

public class Application {
    public static boolean isContinue = true;

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        new Initializer().subwayInit();
        while (isContinue) {
            Controller controller = getMatchingController(inputView, outputView);
            controller.execute();
        }
    }

    private static Controller getMatchingController(InputView inputView, OutputView outputView) {
        inputView.printMainChoicesMenu();
        inputView.printUsersChoiceOpening();
        try {
            return executeByUserChoice(getUserInput());
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return getMatchingController(inputView, outputView);
        }
    }
}
