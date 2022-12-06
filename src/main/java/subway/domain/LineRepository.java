package subway.domain;

import static subway.domain.Line.validateName;
import static subway.ui.ErrorMessages.DUPLICATED_LINE_NAME;
import static subway.ui.ErrorMessages.FORBIDDEN_STATION_TO_INSERT;
import static subway.ui.ErrorMessages.INVALID_ORDER_INPUT;
import static subway.ui.ErrorMessages.NON_EXISTING_LINE;
import static subway.ui.ErrorMessages.UNABLE_TO_DELETE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LineRepository {
    private static final List<Line> lines = new ArrayList<>();

    public static List<Domain> lines() {
        return Collections.unmodifiableList(lines);
    }

    public static void addLine(Line line) {
        lines.add(line);
    }

    public static void deleteLineByName(String name) {
        if (isDeletable(name)){
            lines.removeIf(line -> Objects.equals(line.getName(), name));
            return;
        }
        throw new IllegalStateException(NON_EXISTING_LINE.getMessage());
    }

    private static boolean isDeletable(String name) {
        return lines.stream()
                .anyMatch(line -> Objects.equals(line.getName(), name));
    }

    public static void insertStationToLine(Line line, Station station, Integer order){
        line.insertStation(station, order);
    }

    public static String validateLineToAdd(String lineInput){
        validateName(lineInput);
        if (isDuplicatedLine(lineInput)){
            throw new IllegalArgumentException(DUPLICATED_LINE_NAME.getMessage());
        }
        return lineInput;
    }

    private static boolean isDuplicatedLine(String lineInput) {
        return lines.stream()
                .anyMatch(line -> lineInput.equals(line.getName()));
    }

    public static Line getExistingLine(String lineInput) {
        validateName(lineInput);
        return lines.stream()
                .filter(line -> lineInput.equals(line.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NON_EXISTING_LINE.getMessage()));
    }

    public static void validateIfStationExistsInLine(Line line, Station existingStation) {
        if (line.stationExists(existingStation)){
            throw new IllegalStateException(FORBIDDEN_STATION_TO_INSERT.getMessage());
        }
    }

    public static Station validateStationInLine(Line line, Station station) {
        return line.getExistingStation(station);
    }

    public static void validateOrderInRange(Line line, Integer order) {
        if (!line.isInRange(order)){
            throw new IllegalStateException(INVALID_ORDER_INPUT.getMessage());
        }
    }

    public static void deleteStationInLine(Line line, Station station) {
        if (line.isDeletableInSize()){
            line.removeStation(station);
            return;
        }
        throw new IllegalStateException(UNABLE_TO_DELETE.getMessage());
    }
}
