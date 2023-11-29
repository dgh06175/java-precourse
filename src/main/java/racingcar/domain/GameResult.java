package racingcar.domain;

import java.util.List;

public class GameResult {
    private List<String> carNames;

    public GameResult(List<String> carNames) {
        this.carNames = carNames;
    }

    public List<String> getCarNames() {
        return carNames;
    }
}
