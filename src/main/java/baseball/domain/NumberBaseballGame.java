package baseball.domain;

import baseball.util.NumberGenerator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberBaseballGame {
    private final BaseBallNumber computerNumber;

    public NumberBaseballGame(NumberGenerator numberGenerator) {
        computerNumber = new BaseBallNumber(numberGenerator.generate());
    }

    public Map<String, Integer> getResultWith(List<Integer> userNumber) {
        Map<String, Integer> result = new HashMap<>();
        BaseBallNumber userNumbers = new BaseBallNumber(userNumber);

        int strike = computerNumber.calcStrikeWith(userNumbers);
        int ball = computerNumber.calcBallWith(userNumbers);

        result.put("Strike", strike);
        result.put("Ball", ball);

        return result;
    }

    public boolean isWin(List<Integer> userNumber) {
        return computerNumber.calcStrikeWith(new BaseBallNumber(userNumber)) == 3;
    }
}
