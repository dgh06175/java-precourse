package racingcar;

import racingcar.controller.RaceController;
import racingcar.util.NumberGenerator;
import racingcar.util.RandomNumberGenerator;

public class Application {
    public static void main(String[] args) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        new RaceController(numberGenerator).run();
    }
}
