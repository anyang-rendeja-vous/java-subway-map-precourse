package subway.view;

import java.util.Scanner;

public class InputView {

    private static final String ENTER_NUMBER = "## 원하는 기능을 선택하세요.";
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    private String input() {
        return scanner.next();
    }

    public String inputNumber() {
        System.out.println(ENTER_NUMBER);
        String choice = input();
        return choice;
    }
}
