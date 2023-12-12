package menu.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import menu.model.Category;
import menu.service.Coach;
import menu.service.Coaches;
import menu.service.Menus;

public class OutputView {
    public void printStartMessage() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }

    public void printResult(List<Category> categories, Coaches coaches, Map<Coach, Menus> selectedMenus) {
        printResultStartMessage();
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");
        printCategory(categories);
        for(Coach coach: coaches.getCoachList()) {
            List<String> menusString = selectedMenus.get(coach).getMenuStringList();
            printFormattedList(menusString, coach.getName());
        }
        printResultEndMessage();
    }

    private void printResultStartMessage() {
        System.out.println();
        System.out.println("메뉴 추천 결과입니다.");
    }

    private void printResultEndMessage() {
        System.out.println();
        System.out.println("추천을 완료했습니다.");
    }

    private void printCategory(List<Category> categories) {
        List<String> categoryNames = categories.stream()
                .map(Category::getKrName)
                .collect(Collectors.toList());

        printFormattedList(categoryNames, "카테고리");
    }

    private static void printFormattedList(List<String> list, String label) {
        String combined = String.join(" | ", list);
        System.out.printf("[ %s | %s ]%n", label, combined);
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
