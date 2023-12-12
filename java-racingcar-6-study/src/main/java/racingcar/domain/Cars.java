package racingcar.domain;

import java.util.Collections;
import java.util.List;

public class Cars {
    private final List<Car> carList;

    public Cars(List<Car> carList) {
        this.carList = carList;
    }

    public void allTryMove() {
        for(Car car: carList) {
            car.tryMove();
        }
    }

    public List<String> getWinners() {
        Car maxPositionCar = Collections.max(carList);
        return carList.stream()
                .filter(car -> car.equals(maxPositionCar))
                .map(Car::getName)
                .toList();
    }

    public AttemptResult getAttemptResult() {
        AttemptResult attemptResult = new AttemptResult();
        for(Car car: carList) {
            attemptResult.put(car.getName(), car.getPosition());
        }

        return attemptResult;
    }
}
