package subway.domain.initial;

public enum LineType {

    TWO("2호선"),
    THREE("3호선"),
    SHINBUNDANG("신분당선");

    private final String name;

    LineType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
