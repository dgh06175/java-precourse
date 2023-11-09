package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.InputException;

public class InputUtil {
    public static int readInt() {
        try {
            return Integer.parseInt(readString());
        } catch (NumberFormatException e) {
            throw new InputException();
        }
    }

    public static String readString() {
        String input = Console.readLine();
        if (input.isEmpty() || input.isBlank()) {
            throw new InputException();
        }
        return input;
    }
}
