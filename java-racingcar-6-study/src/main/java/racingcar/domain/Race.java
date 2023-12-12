package racingcar.domain;

import static racingcar.exception.ErrorMessage.RACE_ATTEMPT_ERROR;
import static racingcar.exception.ErrorMessage.RACE_MAX_ATTEMPT_ERROR;

import racingcar.exception.InvalidInputException;

public class Race {
    private final Cars cars;
    private final int maxAttempt;
    private int attempt;

    public Race(Cars cars, int maxAttempt) {
        validateMaxAttempt(maxAttempt);
        this.cars = cars;
        this.maxAttempt = maxAttempt;
        this.attempt = 0;
    }

    public AttemptResult doAttempt() {
        validateAttempt();
        cars.allTryMove();
        attempt += 1;
        return cars.getAttemptResult();
    }

    public GameResult getGameResult() {
        return new GameResult(cars.getWinners());
    }

    public boolean isRaceEnd() {
        return attempt >= maxAttempt;
    }

    private void validateMaxAttempt(int maxAttempt) {
        if (maxAttempt < 1) {
            throw new InvalidInputException(RACE_MAX_ATTEMPT_ERROR.message);
        }
    }

    private void validateAttempt() {
        if (attempt >= maxAttempt) {
            throw new InvalidInputException(RACE_ATTEMPT_ERROR.message);
        }
    }
}
