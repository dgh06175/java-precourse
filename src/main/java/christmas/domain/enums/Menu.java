package christmas.domain.enums;

import christmas.exception.OrderException;
import java.util.Arrays;
import java.util.stream.Collectors;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", MenuCategory.APPETIZER, 6000),
    TAPAS("타파스", MenuCategory.APPETIZER, 5500),
    CAESAR_SALAD("시저샐러드", MenuCategory.APPETIZER, 8000),

    T_BONE_STEAK("티본스테이크", MenuCategory.MAIN, 55000),
    BARBECUE_RIBS("바비큐립", MenuCategory.MAIN, 54000),
    SEAFOOD_PASTA("해산물파스타", MenuCategory.MAIN, 35000),
    CHRISTMAS_PASTA("크리스마스파스타",MenuCategory.MAIN, 25000),

    CHOCOLATE_CAKE("초코케이크", MenuCategory.DESSERT, 15000),
    ICE_CREAM("아이스크림", MenuCategory.DESSERT, 5000),

    ZERO_COLA("제로콜라", MenuCategory.DRINK, 3000),
    RED_WINE("레드와인", MenuCategory.DRINK, 60000),
    CHAMPAGNE("샴페인", MenuCategory.DRINK, 25000);

    public final String displayName;
    private final MenuCategory menuCategory;
    private final int price;

    Menu(String displayName, MenuCategory menuCategory, int price) {
        this.displayName = displayName;
        this.menuCategory = menuCategory;
        this.price = price;
    }

    /**
     * 문자열과 이름이 동일한 메뉴를 반환한다.
     * @param inName 이름
     * @return 이름에 맞는 메뉴
     */
    public static Menu of(String inName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.isNameEquals(inName))
                .findFirst()
                .orElseThrow(OrderException::new);
    }

    /**
     * 메뉴판 출력을 위해 모든 메뉴의 정보를 문자열로 반환한다.
     * @return 메뉴 정보 문자열
     */
    public static String formattedMenu() {
        return Arrays.stream(MenuCategory.values())
                .map(Menu::formatMenuForCategory)
                .collect(Collectors.joining("\n\n"))
                .trim();
    }

    private static String formatMenuForCategory(MenuCategory category) {
        String menuItems = Arrays.stream(Menu.values())
                .filter(menu -> menu.isCategoryEquals(category))
                .map(menu -> String.format("%s(%,d)", menu.displayName, menu.price))
                .collect(Collectors.joining(", "));

        return String.format("<%s>\n%s", category.getDisplayName(), menuItems);
    }

    public boolean isCategoryEquals(MenuCategory inCategory) {
        return menuCategory.equals(inCategory);
    }

    public int getPrice() {
        return price;
    }

    public int getPrice(int count) {
        return price * count;
    }

    private boolean isNameEquals(String inName) {
        return displayName.equals(inName);
    }
}
