package racingcar.controller;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.AttemptResult;
import racingcar.domain.Car;
import racingcar.domain.Race;
import racingcar.util.NumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RaceController {
    private final NumberGenerator numberGenerator;
    private final InputView inputView;
    private final OutputView outputView;

    public RaceController(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        List<Car> cars = requestCars();

        int maxAttempt = requestMaxAttempt();

        Race race = new Race(cars, maxAttempt);

        do {
            AttemptResult attemptResult = race.doAttempt();
            outputView.printAttemptResult(attemptResult);
        } while (!race.isRaceEnd());

        outputView.printGameResult(race.getGameResult());
    }

    private List<Car> requestCars() {
        String inputCarNames = inputView.readCarNames();
        return parseInputCarNames(inputCarNames);
    }

    private List<Car> parseInputCarNames(String inputCarNames) {
        List<String> carNames = List.of(inputCarNames.split(","));
        return carNames.stream()
                .map(name -> new Car(name, numberGenerator))
                .toList();
    }

    private int requestMaxAttempt() {
        return inputView.readMaxAttempt();
    }
}
