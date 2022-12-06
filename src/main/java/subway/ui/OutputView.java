package subway.ui;

import java.util.List;
import subway.domain.Domain;

public class OutputView {
    private static final String INFO = "[INFO]";
    private static final String ERROR = "[ERROR]";

    private static final String CREATED = "지하철 %s이 등록되었습니다.";
    private static final String DELETED = "지하철 %s이 삭제되었습니다.";

    private static final String SECTION_INSERTED = "구간이 등록되었습니다.";
    private static final String SECTION_DELETED = "구간이 삭제되었습니다.";

    private static final String END = "어플리케이션을 종료합니다.";

    public void printErrorMessage(String message){
        System.out.println(System.lineSeparator() + ERROR + " " + message);
    }

    public void printCreationResult(String subject){
        System.out.printf(System.lineSeparator() + INFO + " " + CREATED, subject);
        System.out.println();
    }

    public void printSectionInsertedResult(){
        System.out.println(System.lineSeparator() + INFO + " " + SECTION_INSERTED);
    }

    public void printSectionDeletedResult() {
        System.out.println(System.lineSeparator() + INFO + " " + SECTION_DELETED);
    }

    public void printDeletionResult(String subject){
        System.out.printf(System.lineSeparator() + INFO + " " + DELETED, subject);
        System.out.println();
    }

    public void printDomainList(List<Domain> subjects) {
        for (Domain subject : subjects) {
            printSingleDomain(subject);
        }
    }

    public void printSingleDomain(Domain subject){
        System.out.println(INFO + " " + subject.getName());
    }

    public void printApplicationEnd() {
        System.out.println(System.lineSeparator() + INFO + " " + END);
    }

    public void printEmptyLine(){
        System.out.println();
    }

    public void printSeparator() {
        System.out.println(INFO + " ---");
    }
}
