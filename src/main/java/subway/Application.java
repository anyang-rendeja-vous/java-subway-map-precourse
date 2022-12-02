package subway;

import static subway.ChoiceMapper.executeByUserChoice;

import java.util.Scanner;
import subway.controller.Controller;
import subway.ui.InputView;

public class Application {
    public static boolean isContinue = true;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        while (isContinue){
            inputView.printMainChoicesMenu();
            inputView.printUsersChoiceOpening();
            Controller controller = executeByUserChoice(scanner.next());
            controller.execute();
        }

    }
}
