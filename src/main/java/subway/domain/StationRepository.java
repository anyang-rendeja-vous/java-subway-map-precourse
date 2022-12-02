package subway.domain;

import static subway.ui.ErrorMessages.DUPLICATED_STATION_NAME;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(Station station) {
        if (isDuplicatedName(station)){
            throw new IllegalStateException(DUPLICATED_STATION_NAME.getMessage());
        }
        stations.add(station);
    }

    private static boolean isDuplicatedName(Station station) {
        return stations.stream()
                .anyMatch(x -> x.nameMatches(station));
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }
}
