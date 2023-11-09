package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.InputException;
import java.util.regex.Pattern;

public class InputUtil {
    private static final String INTEGER_REGEX = "\\d+";

    public static String readInt() {
        String input = readString();
        if (!Pattern.matches(INTEGER_REGEX, input)) {
            throw new InputException();
        }
        return input;
    }

    public static String readString() {
        String input = Console.readLine();
        if (input.isEmpty() || input.isBlank()) {
            throw new InputException();
        }
        return input;
    }
}
