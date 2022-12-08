package subway.util;

import java.util.regex.Pattern;

public class InputValidator {

    private static final String INVALID_INPUT = "선택할 수 없는 기능입니다.";

    public void isMenuValid(String number) {
        if (!Pattern.matches("^[1-4Q]$", number)) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }

    public void isStationMenuValid(String number) {
        if (!Pattern.matches("^[1-3B]$", number)) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }

    public void isSectionMenuValid(String number) {
        if (!Pattern.matches("^[1-2B]$", number)) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }
}
