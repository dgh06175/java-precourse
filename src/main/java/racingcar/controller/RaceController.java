package racingcar.controller;

import static racingcar.exception.ErrorMessage.DUPLICATE_NAME;

import java.util.List;
import racingcar.domain.AttemptResult;
import racingcar.domain.Car;
import racingcar.domain.GameResult;
import racingcar.domain.Race;
import racingcar.exception.InvalidInputException;
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

        GameResult gameResult = doRace(cars, maxAttempt);
        outputView.printGameResult(gameResult);
    }

    private List<Car> requestCars() {
        String inputCarNames = inputView.readCarNames();
        return parseInputCarNames(inputCarNames);
    }

    private List<Car> parseInputCarNames(String inputCarNames) {
        List<String> carNames = List.of(inputCarNames.split(","));
        validateCarNames(carNames);
        return carNames.stream()
                .map(name -> new Car(name, numberGenerator))
                .toList();
    }

    private void validateCarNames(List<String> carNames) {
        if (carNames.stream().distinct().count() != carNames.size()) {
            throw new InvalidInputException(DUPLICATE_NAME.message);
        }
    }

    private int requestMaxAttempt() {
        return inputView.readMaxAttempt();
    }

    private GameResult doRace(List<Car> cars, int maxAttempt) {
        Race race = new Race(cars, maxAttempt);
        outputView.printAttemptResultStartMessage();
        do {
            AttemptResult attemptResult = race.doAttempt();
            outputView.printAttemptResult(attemptResult);
        } while (!race.isRaceEnd());

        return race.getGameResult();
    }
}
