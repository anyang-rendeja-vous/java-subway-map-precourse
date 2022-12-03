package subway.domain;

import static subway.ui.ErrorMessages.INVALID_LINE_NAME_LENGTH;
import static subway.ui.ErrorMessages.INVALID_LINE_NAME_STRUCT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LineRepository {
    private static final List<Line> lines = new ArrayList<>();

    public static List<Line> lines() {
        return Collections.unmodifiableList(lines);
    }

    public static void addLine(Line line) {
        lines.add(line);
    }

    public static boolean deleteLineByName(String name) {
        return lines.removeIf(line -> Objects.equals(line.getName(), name));
    }

    public static String validateLineName(String lineInput){
        validateName(lineInput);
        if (isDuplicatedLine(lineInput)){
            throw new IllegalArgumentException("error!");
        }
        return lineInput;
    }

    private static void validateName(String lineInput){
        if (lineInput.length() < 2){
            throw new IllegalArgumentException(INVALID_LINE_NAME_LENGTH.getMessage());
        }
        if (!lineInput.endsWith("호선")){
            throw new IllegalArgumentException(INVALID_LINE_NAME_STRUCT.getMessage());
        }
    }

    private static boolean isDuplicatedLine(String lineInput) {
        return lines.stream()
                .anyMatch(line -> lineInput.equals(line.getName()));
    }

}
