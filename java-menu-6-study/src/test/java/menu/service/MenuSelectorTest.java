package menu.service;

import menu.model.MenuData;
import menu.util.RandomGenerator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import menu.model.Category;
import menu.service.Menus;
import menu.util.FakeGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MenuSelectorTest {

    @RepeatedTest(100)
    void 선택된_메뉴_검증() {
        // FakeGenerator 설정: 항상 첫 번째 메뉴를 선택하도록 설정
        MenuSelector menuSelector = new MenuSelector(new RandomGenerator());

        // 코치 및 카테고리 목록 설정
        List<String> bannedMenusForCoach1 = Arrays.asList("규동", "우동");
        List<String> bannedMenusForCoach2 = Arrays.asList("김치찌개", "불고기");
        Coach coach1 = new Coach("코치1", new Menus(bannedMenusForCoach1));
        Coach coach2 = new Coach("코치2", new Menus(bannedMenusForCoach2)); // 금지된 메뉴 포함
        Coaches coaches = new Coaches(Arrays.asList(coach1, coach2));
        List<Category> categories = Arrays.asList(Category.JAPANESE, Category.KOREAN);

        // 선택된 메뉴 검증
        Map<Coach, Menus> selectedMenus = menuSelector.getSelectedMenus(coaches, categories);
        Menus menus1 = selectedMenus.get(coach1);
        for (String s: bannedMenusForCoach1) {
            assertThat(menus1.hasMenu(s)).isFalse();
        }

        Menus menus2 = selectedMenus.get(coach2);
        for (String s: bannedMenusForCoach2) {
            assertThat(menus2.hasMenu(s)).isFalse();
        }
    }

    @RepeatedTest(100)
    void 출력된_메뉴들의_카테고리_순서_검증() {
        // FakeGenerator 설정: 카테고리별 첫 번째 메뉴를 선택하도록 설정
        FakeGenerator generator = new FakeGenerator(1, 0);
        MenuSelector menuSelector = new MenuSelector(generator);

        // 코치 및 카테고리 목록 설정
        Coach coach = new Coach("코치", new Menus(Arrays.asList()));
        Coach coach2 = new Coach("코치2", new Menus(Arrays.asList()));

        Coaches coaches = new Coaches(Arrays.asList(coach, coach2));
        List<Category> categories = Arrays.asList(Category.JAPANESE, Category.KOREAN, Category.CHINESE, Category.ASIAN, Category.WESTERN);

        // 선택된 메뉴 검증
        Map<Coach, Menus> selectedMenus = menuSelector.getSelectedMenus(coaches, categories);
        Menus menus = selectedMenus.get(coach);

        List<String> selectedMenuList = menus.getMenuStringList();
        assertThat(selectedMenuList.size()).isEqualTo(categories.size());
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            String selectedMenu = selectedMenuList.get(i);

            // 선택된 메뉴가 해당 카테고리의 메뉴 목록에 속하는지 검증
            assertThat(MenuData.getMenusByCategory(category)).contains(selectedMenu);
        }
    }
}
