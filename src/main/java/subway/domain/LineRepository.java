package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LineRepository {

    private static final String NOT_EXISTED_LINE = "존재하지 않는 노선입니다.";
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

    public static Line findLine(String findLine) {
        return lines.stream()
                .filter(line -> line.getName().equals(findLine))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXISTED_LINE));
    }

    public static boolean isContain(String lineName) {
        return lines.stream()
                .anyMatch(line -> line.getName().equals(lineName));
    }

    public static boolean isAddedFindStation(String findStation) {
        boolean isAdded = false;
        for (Line line : lines) {
            isAdded = isAddedInSingleLine(findStation, line);
            if (isAdded) {
                break;
            }
        }
        return isAdded;
    }

    private static boolean isAddedInSingleLine(String findStation, Line line) {
        return line.getStations()
                .stream()
                .anyMatch(station -> station.getName().equals(findStation));
    }
}
