package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.DateException;
import christmas.exception.OrderException;
import java.util.regex.Pattern;

public class InputView {
    private static final String INTEGER_REGEX = "\\d+";
    OutputView outputView = new OutputView();

    public String readDate() {
        outputView.printReadDateMessage();
        String input = readString();

        if (isInvalidString(input) || isInvalidNumber(input)) {
            throw new DateException();
        }
        return input;
    }

    public String readOrder() {
        outputView.printReadOrderMessage();
        String input = readString();

        if (isInvalidString(input)) {
            throw new OrderException();
        }
        return input;
    }

    private String readString() {
        return Console.readLine().trim();
    }

    private boolean isInvalidString(String input) {
        return input.isEmpty() || input.isBlank();
    }

    private boolean isInvalidNumber(String input) {
        return !Pattern.matches(INTEGER_REGEX, input);
    }
}
