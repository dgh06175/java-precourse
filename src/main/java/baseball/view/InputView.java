package baseball.view;

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

    public int inputRetryNumber() throws InvalidInputException {
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
            throw new InvalidInputException("숫자로만 이루어진 값을 입력해주세요.");
        }
    }

    private int convertStringToInteger(String input) throws InvalidInputException {
        validateInputForRetryNumber(input);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("정수 값을 입력해주세요.");
        }
    }

    private void validateInputForBaseballNumbers(String input) throws InvalidInputException {
        if (input.length() != 3) {
            throw new InvalidInputException("숫자는 정확히 3개를 입력해야 합니다.");
        }
    }

    private void validateInputForRetryNumber(String input) throws InvalidInputException {
        if (input.length() != 1) {
            throw new InvalidInputException("한 자리 숫자를 입력해야 합니다.");
        }
    }
}
