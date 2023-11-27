package baseball.domain;

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
        if (isValidNumber(numbers)) {
            throw new InvalidInputException("숫자는 정확히 3개여야 합니다.");
        }
        if (isAllBetween1and9(numbers)) {
            throw new InvalidInputException("숫자는 1에서 9 사이의 값 이어야 합니다.");
        }
    }

    private boolean isValidNumber(List<Integer> numbers) {
        return numbers == null || numbers.size() != 3;
    }

    private boolean isAllBetween1and9(List<Integer> numbers) {
        return numbers.stream().anyMatch(number -> number < 1 || number > 9);
    }

    public int calcStrikeWith(BaseBallNumber otherNumber) {
        int strike = 0;
        for(int i = 0; i < 3; i++) {
            if (numbers.get(i).equals(otherNumber.numbers.get(i))) {
                strike++;
            }
        }
        return strike;
    }

    public int calcBallWith(BaseBallNumber otherNumber) {
        int ball = 0;
        for(int i = 0; i < 3; i++) {
            if (numbers.contains(otherNumber.numbers.get(i))) {
                ball++;
            }
            if (numbers.get(i).equals(otherNumber.numbers.get(i))) {
                ball--;
            }
        }
        return ball;
    }
}
