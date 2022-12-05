package subway.domain;

import static subway.ui.ErrorMessages.DUPLICATED_STATION_NAME;
import static subway.ui.ErrorMessages.NON_EXISTING_STATION;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    public static List<Domain> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(Station station) {
        if (isDuplicatedName(station)){
            throw new IllegalStateException(DUPLICATED_STATION_NAME.getMessage());
        }
        stations.add(station);
    }

    public static Station validateStation(Station station){
        if (!isDuplicatedName(station)){ // 존재하지 않으면 에러 !
            throw new IllegalStateException(NON_EXISTING_STATION.getMessage());
        }
        return station;
    }

    private static boolean isDuplicatedName(Station station) {
        return stations.stream()
                .anyMatch(x -> x.nameMatches(station));
    }

    public static void deleteStationByName(String name) {
        if (isDeletable(name)){
            stations.removeIf(station -> Objects.equals(station.getName(), name));
            return;
        }
        throw new IllegalStateException(NON_EXISTING_STATION.getMessage());
    }

    private static boolean isDeletable(String name) {
        return stations.stream()
                .anyMatch(station -> Objects.equals(station.getName(), name));
    }

    public static Station getStationByName(String stationName) {
        return stations.stream()
                .filter(station -> station.getName().equals(stationName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(NON_EXISTING_STATION.getMessage()));
    }
}
