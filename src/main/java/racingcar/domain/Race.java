package racingcar.domain;

import static racingcar.exception.ErrorMessage.RACE_ERROR;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.exception.InvalidInputException;

public class Race {
    private final List<Car> cars;
    private final int maxAttempt;
    private int attempt;

    public Race(List<Car> cars, int maxAttempt) {
        this.cars = cars;
        this.maxAttempt = maxAttempt;
        this.attempt = 0;
    }

    public AttemptResult doAttempt() {
        if (isRaceEnd()) {
            throw new InvalidInputException(RACE_ERROR.message);
        }

        for (Car car: cars) {
            car.tryMove();
        }
        attempt += 1;
        return getAttemptResult();
    }

    public GameResult getGameResult() {
        return new GameResult(getWinners());
    }

    private List<String> getWinners() {
        Car maxPositionCar = Collections.max(cars);
        return cars.stream()
                .filter(car -> car.compareTo(maxPositionCar) == 0)
                .map(Car::getName)
                .toList();
    }

    public boolean isRaceEnd() {
        return attempt >= maxAttempt;
    }

    private AttemptResult getAttemptResult() {
        AttemptResult attemptResult = new AttemptResult();
        for(Car car: cars) {
            attemptResult.put(car.getName(), car.getPosition());
        }

        return attemptResult;
    }
}
