package menu.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import menu.exception.InvalidInputException;

public class MenuData {
    private static Map<Category, List<String>> menuByCategory = new HashMap<>();

    private MenuData(){}

    static {
        initializeMenuData();
    }

    private static void initializeMenuData() {
        menuByCategory.put(Category.JAPANESE, Arrays.asList(
                "규동", "우동", "미소시루", "스시", "가츠동", "오니기리", "하이라이스", "라멘", "오코노미야끼"));
        menuByCategory.put(Category.KOREAN, Arrays.asList(
                "김밥", "김치찌개", "쌈밥", "된장찌개", "비빔밥", "칼국수", "불고기", "떡볶이", "제육볶음"));
        menuByCategory.put(Category.CHINESE, Arrays.asList(
                "깐풍기", "볶음면", "동파육", "짜장면", "짬뽕", "마파두부", "탕수육", "토마토 달걀볶음", "고추잡채"));
        menuByCategory.put(Category.ASIAN, Arrays.asList(
                "팟타이", "카오 팟", "나시고렝", "파인애플 볶음밥", "쌀국수", "똠얌꿍", "반미", "월남쌈", "분짜"));
        menuByCategory.put(Category.WESTERN, Arrays.asList(
                "라자냐", "그라탱", "뇨끼", "끼슈", "프렌치 토스트", "바게트", "스파게티", "피자", "파니니"));
    }

    public static List<String> getMenusByCategory(Category category) {
        return menuByCategory.getOrDefault(category, List.of());
    }

    public static void isValidMenu(String menu) {
        if (!menuExists(menu)) {
            throw new InvalidInputException("잘못된 메뉴 입력입니다.");
        }
    }

    private static boolean menuExists(String menu) {
        return menuByCategory.values().stream()
                .flatMap(List::stream)
                .anyMatch(menu::equals);
    }

    public static boolean isMenuInCategory(String menu, Category category) {
        List<String> menusInCategory = getMenusByCategory(category);
        return menusInCategory.contains(menu);
    }
}
