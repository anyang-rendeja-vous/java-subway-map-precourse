package subway.domain.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MainMenu {

    MAIN("##", " 메인 화면"),
    MANAGE_STATION("1", ". 역 관리"),
    MANAGE_LINE("2", ". 노선 관리"),
    MANAGE_SECTION("3", ". 구간 관리"),
    PRINT_SUBWAY_MAP("4", ". 지하철 노선도 출력"),
    QUIT("Q", ". 종료");

    private final String prefix;
    private final String content;

    MainMenu(String prefix, String content) {
        this.prefix = prefix;
        this.content = content;
    }

    public static List<String> getAll() {
        return Arrays.stream(MainMenu.values())
                .map(MainMenu::toString)
                .collect(Collectors.toList());
    }

    public boolean isSamePrefix(String prefix) {
        return this.prefix.equals(prefix);
    }

    @Override
    public String toString() {
        return prefix + content;
    }
}
