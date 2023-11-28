package baseball.domain;

import static baseball.domain.DomainConstant.BALL;
import static baseball.domain.DomainConstant.NUMBER_COUNT;
import static baseball.domain.DomainConstant.STRIKE;

import baseball.util.NumberGenerator;
import java.util.HashMap;
import java.util.Map;

public class NumberBaseballGame {
    private final BaseBallNumber computerNumber;

    public NumberBaseballGame(NumberGenerator numberGenerator) {
        computerNumber = new BaseBallNumber(numberGenerator.generate());
    }

    public GameResult getResultWith(BaseBallNumber userNumber) {
        int strike = computerNumber.calcStrikeWith(userNumber);
        int ball = computerNumber.calcBallWith(userNumber);

        return new GameResult(strike, ball);
    }


    public boolean isWin(BaseBallNumber userNumber) {
        return computerNumber.calcStrikeWith(userNumber) == NUMBER_COUNT;
    }
}
