package subway.domain.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum LineManageMenu {

    MAIN("##", " 노선 관리 화면"),
    ADD_LINE("1", ". 노선 등록"),
    DELETE_LINE("2", ". 노선 삭제"),
    SELECT_LINE("3", ". 노선 조회"),
    BACK("B", ". 돌아가기");

    private final String prefix;
    private final String content;

    LineManageMenu(String prefix, String content) {
        this.prefix = prefix;
        this.content = content;
    }

    public static List<String> getAll() {
        return Arrays.stream(LineManageMenu.values())
                .map(LineManageMenu::toString)
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
