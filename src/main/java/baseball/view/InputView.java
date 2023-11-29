package baseball.view;

import static baseball.domain.GameConstant.NUMBER_COUNT;
import static baseball.exception.ExceptionMessage.INTEGER_EXCEPTION;
import static baseball.exception.ExceptionMessage.NUMBER_COUNT_EXCEPTION;
import static baseball.exception.ExceptionMessage.NUMBER_INPUT_STRING_EXCEPTION;
import static baseball.exception.ExceptionMessage.RETRY_NUMBER_COUNT_INVALID_EXCEPTION;

import baseball.exception.InvalidInputException;
import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    public List<Integer> inputBaseballNumbers() throws InvalidInputException {
        String input = readInput();
        return convertStringToListOfIntegers(input);
    }

    public int inputRestartNumber() throws InvalidInputException {
        String input = readInput();
        return convertStringToInteger(input);
    }

    private String readInput() {
        return Console.readLine().trim();
    }

    private List<Integer> convertStringToListOfIntegers(String input) throws InvalidInputException {
        validateInputForBaseballNumbers(input);
        try {
            return Arrays.stream(input.split(""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(NUMBER_INPUT_STRING_EXCEPTION.message);
        }
    }

    private int convertStringToInteger(String input) throws InvalidInputException {
        validateInputForRetryNumber(input);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(INTEGER_EXCEPTION.message);
        }
    }

    private void validateInputForBaseballNumbers(String input) throws InvalidInputException {
        if (input.length() != NUMBER_COUNT) {
            throw new InvalidInputException(NUMBER_COUNT_EXCEPTION.message);
        }
    }

    private void validateInputForRetryNumber(String input) throws InvalidInputException {
        if (input.length() != 1) {
            throw new InvalidInputException(RETRY_NUMBER_COUNT_INVALID_EXCEPTION.message);
        }
    }
}
