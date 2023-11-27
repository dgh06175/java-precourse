package baseball;

import baseball.controller.GameController;
import baseball.util.RandomNumberGenerator;

public class Application {
    public static void main(String[] args) {
        new GameController(new RandomNumberGenerator()).run();
    }
}
