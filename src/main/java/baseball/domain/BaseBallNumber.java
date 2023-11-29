package baseball.domain;

import static baseball.domain.GameConstant.MAX_NUMBER;
import static baseball.domain.GameConstant.MIN_NUMBER;
import static baseball.domain.GameConstant.NUMBER_COUNT;
import static baseball.exception.ExceptionMessage.DUPLICATE_NUMBER_EXCEPTION;
import static baseball.exception.ExceptionMessage.NUMBER_COUNT_EXCEPTION;
import static baseball.exception.ExceptionMessage.NUMBER_SIZE_EXCEPTION;

import baseball.exception.InvalidInputException;
import java.util.ArrayList;
import java.util.List;

public class BaseBallNumber {
    private final List<Integer> numbers;

    public BaseBallNumber(List<Integer> numbers){
        validateNumbers(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validateNumbers(List<Integer>numbers) {
        if (isInValidNumber(numbers)) {
            throw new InvalidInputException(NUMBER_COUNT_EXCEPTION.message);
        }
        if (isAnyBetween1and9(numbers)) {
            throw new InvalidInputException(NUMBER_SIZE_EXCEPTION.message);
        }
        if (isDuplicateNumbers(numbers)) {
            throw new InvalidInputException(DUPLICATE_NUMBER_EXCEPTION.message);
        }
    }

    private boolean isInValidNumber(List<Integer> numbers) {
        return numbers == null || numbers.size() != NUMBER_COUNT;
    }

    private boolean isAnyBetween1and9(List<Integer> numbers) {
        return numbers.stream().anyMatch(number -> number < MIN_NUMBER || number > MAX_NUMBER);
    }

    private boolean isDuplicateNumbers(List<Integer> numbers) {
        return numbers.stream().distinct().count() != NUMBER_COUNT;
    }

    public int calcStrikeWith(BaseBallNumber otherNumber) {
        int strike = 0;
        for(int i = 0; i < NUMBER_COUNT; i++) {
            if (numbers.get(i).equals(otherNumber.numbers.get(i))) {
                strike++;
            }
        }
        return strike;
    }

    public int calcBallWith(BaseBallNumber otherNumber) {
        int ball = 0;
        for(int i = 0; i < NUMBER_COUNT; i++) {
            if (numbers.contains(otherNumber.numbers.get(i))) {
                ball++;
            }
        }
        return ball - calcStrikeWith(otherNumber);
    }
}
