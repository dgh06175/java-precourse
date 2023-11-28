package baseball.domain;

import static baseball.domain.GameConstant.NUMBER_COUNT;

import baseball.util.NumberGenerator;

public class BaseballGame {
    private final BaseBallNumber computerNumber;

    public BaseballGame(NumberGenerator numberGenerator) {
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
