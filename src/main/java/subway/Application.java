package subway;

import java.util.Scanner;
import subway.controller.Controller;
import subway.ui.InputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        // TODO: 프로그램 구현
        ChoiceMapper choiceMapper = new ChoiceMapper();
        InputView inputView = new InputView();
        inputView.printMainChoicesMenu();
        inputView.printUsersChoiceOpening();
        Controller controller = choiceMapper.executeByUserChoice(scanner.next());
        controller.execute();
    }
}
