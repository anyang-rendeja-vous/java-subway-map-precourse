package subway.controller;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.ui.InputView;
import subway.ui.OutputView;

public class SubwayPrinter implements Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public SubwayPrinter() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    @Override
    public void execute() {
        printTotalSubway();
    }

    private void printTotalSubway(){
        inputView.printTotalSubwayOpening();
        LineRepository.lines()
                .stream()
                .filter(Line.class::isInstance)
                .map(Line.class::cast)
            .forEach(
                    this::printSingleLine
            );
    }

    private void printSingleLine(Line line) {
        outputView.printSingleDomain(line);
        outputView.printSeparator();
        outputView.printDomainList(line.getStations());
        outputView.printEmptyLine();
    }

}
