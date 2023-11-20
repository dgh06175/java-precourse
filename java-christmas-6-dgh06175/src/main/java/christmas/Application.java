package christmas;

import christmas.controller.EventController;
import christmas.util.InputParser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.Month;

public class Application {
    public static void main(String[] args) {
        new EventController(
                2023,
                Month.of(12),
                new InputView(),
                new OutputView(),
                new InputParser()
        ).run();
    }
}

