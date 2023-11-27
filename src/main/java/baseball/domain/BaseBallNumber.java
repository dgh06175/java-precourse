package baseball.domain;

import baseball.exception.InvalidInputException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseBallNumber {
    private final List<Integer> numbers;

    public BaseBallNumber(List<Integer> numbers){
        validateNumbers(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    public Map<String, Integer> compareTo(BaseBallNumber otherNumbers) {
        Map<String, Integer> result = new HashMap<>();

        int strike = calcStrike(numbers, otherNumbers.numbers);
        int ball = calcBall(numbers, otherNumbers.numbers);

        result.put("Strike", strike);
        result.put("Ball", ball);

        return result;
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

    private int calcStrike(List<Integer> numbers, List<Integer> otherNumbers) {
        int strike = 0;
        for(int i = 0; i < 3; i++) {
            if (numbers.get(i).equals(otherNumbers.get(i))) {
                strike++;
            }
        }
        return strike;
    }

    private int calcBall(List<Integer> numbers, List<Integer> otherNumbers) {
        int ball = 0;
        for(int i = 0; i < 3; i++) {
            if (otherNumbers.contains(numbers.get(i))) {
                ball++;
            }
            if (numbers.get(i).equals(otherNumbers.get(i))) {
                ball--;
            }
        }
        return ball;
    }
}
