package racingcar.view;

import static racingcar.exception.ErrorMessage.EMPTY_INPUT;
import static racingcar.exception.ErrorMessage.INVALID_INPUT;
import static racingcar.exception.ErrorMessage.NOT_A_NUMBER_INPUT;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Pattern;
import racingcar.exception.InvalidInputException;

public class InputView {
    private static final String CAR_NAMES_INPUT_REGEX = "^[\\w+][,\\w+]*$";

    public String readCarNames() {
        printReadCarNamesMessage();
        String input = readString();

        validateCarNamesInput(input);
        return input;
    }

    public int readMaxAttempt() {
        printReadAttemptMessage();
        return parseInt(readString());
    }

    private void validateCarNamesInput(String input) {
        validateString(input);
        if (!Pattern.matches(CAR_NAMES_INPUT_REGEX, input)) {
            throw new InvalidInputException(INVALID_INPUT.message);
        }
    }

    private String readString() {
        return Console.readLine().trim();
    }

    private void validateString(String input) {
        if (input.isBlank()) {
            throw new InvalidInputException(EMPTY_INPUT.message);
        }
    }

    private int parseInt(String input) {
        validateString(input);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(NOT_A_NUMBER_INPUT.message);
        }
    }

    private void printReadCarNamesMessage() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    private void printReadAttemptMessage() {
        System.out.println("시도할 회수는 몇회인가요?");
    }
}
