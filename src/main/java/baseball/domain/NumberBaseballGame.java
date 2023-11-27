package baseball.domain;

import baseball.util.NumberGenerator;
import java.util.HashMap;
import java.util.Map;

public class NumberBaseballGame {
    private final BaseBallNumber computerNumber;

    public NumberBaseballGame(NumberGenerator numberGenerator) {
        computerNumber = new BaseBallNumber(numberGenerator.generate());
    }

    public Map<String, Integer> getResultWith(BaseBallNumber userNumber) {
        Map<String, Integer> result = new HashMap<>();
        int strike = computerNumber.calcStrikeWith(userNumber);
        int ball = computerNumber.calcBallWith(userNumber);

        result.put("Strike", strike);
        result.put("Ball", ball);
        return result;
    }

    public boolean isWin(BaseBallNumber userNumber) {
        return computerNumber.calcStrikeWith(userNumber) == 3;
    }
}
