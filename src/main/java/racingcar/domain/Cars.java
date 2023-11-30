package racingcar.domain;

import java.util.Collections;
import java.util.List;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public void allTryMove() {
        for(Car car: cars) {
            car.tryMove();
        }
    }

    public List<String> getWinners() {
        Car maxPositionCar = Collections.max(cars);
        return cars.stream()
                .filter(car -> car.compareTo(maxPositionCar) == 0)
                .map(Car::getName)
                .toList();
    }

    public AttemptResult getAttemptResult() {
        AttemptResult attemptResult = new AttemptResult();
        for(Car car: cars) {
            attemptResult.put(car.getName(), car.getPosition());
        }

        return attemptResult;
    }
}
