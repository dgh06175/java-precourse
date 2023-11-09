package christmas;

import christmas.controller.EventController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        new EventController(
                new InputView(),
                new OutputView()
        ).run();
    }
}

