package menu;

import menu.controller.MenuRecommendController;
import menu.util.RandomGenerator;

public class Application {
    public static void main(String[] args) {
        new MenuRecommendController(
                new RandomGenerator()
        ).run();
    }
}
