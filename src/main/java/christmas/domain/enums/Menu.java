package christmas.domain.enums;

import christmas.exception.OrderException;
import java.util.Arrays;

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

    public static Menu of(String inName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.isNameEquals(inName))
                .findFirst()
                .orElseThrow(OrderException::new);
    }

    public static String formattedMenu() {
        StringBuilder output = new StringBuilder();
        for (MenuCategory category : MenuCategory.values()) {
            output.append("<").append(category.getDisplayName()).append(">\n");
            Arrays.stream(Menu.values())
                    .filter(menu -> menu.isCategoryEquals(category))
                    .forEach(menu -> output.append(menu.displayName)
                            .append("(")
                            .append(String.format("%,d", menu.price))
                            .append("), "));
            int length = output.length();
            output.delete(length - 2, length);
            output.append("\n\n");
        }
        return output.toString().trim();
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
