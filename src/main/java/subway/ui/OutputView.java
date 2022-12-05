package subway.ui;

import java.util.List;
import subway.domain.Domain;

public class OutputView {
    private static final String INFO = "[INFO]";
    private static final String ERROR = "[ERROR]";

    private static final String CREATED = "지하철 %s이 등록되었습니다.";
    private static final String DELETED = "지하철 %s이 삭제되었습니다.";

    public void printErrorMessage(String message){
        System.out.println();
        System.out.println(ERROR + " " + message);
    }

    public void printCreationResult(String subject){
        System.out.println();
        System.out.printf(INFO + " " + CREATED, subject);
        System.out.println();
    }

    public void printDeletionResult(String subject){
        System.out.println();
        System.out.printf(INFO + " " + DELETED, subject);
        System.out.println();
    }

    public void printDomainList(List<Domain> subjects) {
        for (Domain subject : subjects) {
            printSingleDomain(subject);
        }
    }

    private void printSingleDomain(Domain subject){
        System.out.println(INFO + " " + subject.getName());
    }

}
