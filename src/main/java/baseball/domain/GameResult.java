package baseball.domain;

import static baseball.domain.GameConstant.BALL_KR;
import static baseball.domain.GameConstant.NOTHING;
import static baseball.domain.GameConstant.STRIKE_KR;

public class GameResult {
    private final int strike;
    private final int ball;

    public GameResult(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
    }

    public String formatResult() {
        if (isNothing()) {
            return NOTHING;
        }

        StringBuilder resultBuilder = new StringBuilder();
        if (ball > 0) {
            resultBuilder.append(ball).append(BALL_KR);
        }
        if (ball > 0 && strike > 0) {
            resultBuilder.append(" ");
        }
        if (strike > 0) {
            resultBuilder.append(strike).append(STRIKE_KR);
        }

        return resultBuilder.toString();
    }

    private boolean isNothing() {
        return strike == 0 && ball == 0;
    }
}

