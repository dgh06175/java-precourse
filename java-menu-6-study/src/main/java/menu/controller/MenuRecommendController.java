package menu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import menu.exception.InvalidInputException;
import menu.exception.MenuRecommendationException;
import menu.model.Category;
import menu.service.CategorySelector;
import menu.service.Coach;
import menu.service.Coaches;
import menu.service.MenuSelector;
import menu.service.Menus;
import menu.util.Generatable;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuRecommendController {
    private final Generatable generator;
    private final OutputView outputView;
    private final InputView inputView;

    public MenuRecommendController(Generatable generator) {
        this.generator = generator;
        this.outputView = new OutputView();
        this.inputView = new InputView();
    }

    public void run() {
        outputView.printStartMessage();

        Coaches coaches = getCoaches();

        List<Category> categories = new CategorySelector(generator).getCategories();
        Map<Coach, Menus> selectedMenus = new MenuSelector(generator).getSelectedMenus(coaches, categories);

        outputView.printResult(categories, coaches, selectedMenus);
    }

    private Coaches getCoaches() {
        return executeWithRetry(() -> {
            List<String> coachNames = inputView.readCoachNames();
            List<Coach> coachList = new ArrayList<>();
            for(String coachName: coachNames) {
                List<String> bannedMenus = inputView.readMenu(coachName);
                coachList.add(new Coach(coachName, new Menus(bannedMenus)));
            }
            return new Coaches(coachList);
        });
    }

    private  <T> T executeWithRetry(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (InvalidInputException | MenuRecommendationException e) {
                outputView.printErrorMessage(e);
            }
        }
    }
}
