package subway.domain.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SectionManageMenu {

    MAIN("##", " 구간 관리 화면"),
    ADD_SECTION("1", ". 구간 등록"),
    DELETE_SECTION("2", ". 구간 삭제"),
    BACK("B", ". 돌아가기");

    private final String prefix;
    private final String content;

    SectionManageMenu(String prefix, String content) {
        this.prefix = prefix;
        this.content = content;
    }

    public static List<String> getAll() {
        return Arrays.stream(SectionManageMenu.values())
                .map(SectionManageMenu::toString)
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
