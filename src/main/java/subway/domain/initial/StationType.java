package subway.domain.initial;

public enum StationType {

    GYODAE("교대역"),
    GANGNAM("강남역"),
    YEOKSAM("역삼역"),
    SOUTH_TERMINAL("남부터미널역"),
    YANGJAE("양재역"),
    YANGJAE_CITIZENS_FOREST("양재시민의숲역"),
    MAEBONG("매봉역");

    private final String name;

    StationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
