package baseball.domain;

public class GameResult {
    private static final String NOTHING = "낫싱";
    private static final String STRIKE = "스트라이크";
    private static final String BALL = "볼";

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
            resultBuilder.append(ball).append(BALL);
        }
        if (ball > 0 && strike > 0) {
            resultBuilder.append(" ");
        }
        if (strike > 0) {
            resultBuilder.append(strike).append(STRIKE);
        }

        return resultBuilder.toString();
    }

    private boolean isNothing() {
        return strike == 0 && ball == 0;
    }
}

