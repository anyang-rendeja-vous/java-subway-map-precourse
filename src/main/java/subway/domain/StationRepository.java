package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StationRepository {

    private static final String NOT_EXISTED_STATION = "존재하지 않는 역입니다.";
    private static final List<Station> stations = new ArrayList<>();

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static Station findStation(String findStation) {
        return stations.stream()
                .filter(station -> station.getName().equals(findStation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXISTED_STATION));
    }

    public static boolean isContain(String findStation) {
        return stations().stream()
                .map(Station::getName)
                .collect(Collectors.toList())
                .contains(findStation);
    }
}
