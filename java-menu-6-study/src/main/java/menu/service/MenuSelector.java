package menu.service;

import static menu.model.MenuData.isMenuInCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import menu.exception.MenuRecommendationException;
import menu.model.Category;
import menu.model.MenuData;
import menu.util.Generatable;

public class MenuSelector {
    private final Generatable generator;

    public MenuSelector(Generatable generator) {
        this.generator = generator;
    }

    /**
     * 같은 메뉴가 없고
     * 못먹는 음식이 없는 Menus 반환
     * @param coaches 코치들
     * @param categories 날짜별 카테고리
     * @return 선택된 메뉴 맵 반환
     */
    public Map<Coach, Menus> getSelectedMenus(Coaches coaches, List<Category> categories) {
        Map<Coach, Menus> selectedMenus = new HashMap<>();
        for(Coach coach: coaches.getCoachList()) {
            selectedMenus.put(coach, selectMenuByCoach(coach, categories));
        }

        return selectedMenus;
    }

    private Menus selectMenuByCoach(Coach coach, List<Category> categories) {
        List<String> menus = new ArrayList<>();
        for (Category category : categories) {
            String selectedMenu = selectMenuForCategory(coach, menus, category);
            if (!isMenuInCategory(selectedMenu, category)) {
                throw new MenuRecommendationException("카테고리 오류");
            }
            menus.add(selectedMenu);
        }
        return new Menus(menus);
    }

    private String selectMenuForCategory(Coach coach, List<String> selectedMenus, Category category) {
        List<String> menus = MenuData.getMenusByCategory(category);
        System.out.println(category.getKrName());
        String menuString;
        do {
            //menuString = Randoms.shuffle(menus).get(0);
            menuString = generator.getRandomElementOf(menus);
            System.out.println(menus);
            System.out.println(menuString);
        } while (isInValidMenuForCoach(coach, selectedMenus, menuString));
        return menuString;
    }

    private boolean isInValidMenuForCoach(Coach coach, List<String> menus, String menu) {
        return menus.contains(menu) || coach.isBannedMenu(menu);
    }
}
