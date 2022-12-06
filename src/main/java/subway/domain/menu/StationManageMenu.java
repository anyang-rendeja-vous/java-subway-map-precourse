package subway.domain.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum StationManageMenu {

    MAIN("##", " 역 관리 화면"),
    ADD_STATION("1", ". 역 등록"),
    DELETE_STATION("2", ". 역 삭제"),
    SELECT_STATION("3", ". 역 조회"),
    BACK("B", ". 돌아가기");

    private final String prefix;
    private final String content;

    StationManageMenu(String prefix, String content) {
        this.prefix = prefix;
        this.content = content;
    }

    public static List<String> getAll() {
        return Arrays.stream(StationManageMenu.values())
                .map(StationManageMenu::toString)
                .collect(Collectors.toList());
    }

    public boolean isSamePrefix(int prefix) {
        return Integer.parseInt(this.prefix) == prefix;
    }

    @Override
    public String toString() {
        return prefix + content;
    }
}
