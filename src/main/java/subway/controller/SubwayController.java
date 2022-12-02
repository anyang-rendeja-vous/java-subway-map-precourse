package subway.controller;

import java.util.Scanner;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final OutputView outputView;

    public SubwayController(Scanner scanner) {
        this.inputView = new InputView(scanner);
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.printMenu();
        int choice = Integer.parseInt(inputView.inputNumber());

        System.out.println("choice = " + choice);
    }
}
