package christmas.domain.enums;

public enum Menu {
    양송이수프(MenuCategory.APPETIZER, 6000),
    타파스(MenuCategory.APPETIZER, 5500),
    시저샐러드(MenuCategory.APPETIZER, 8000),

    티본스테이크(MenuCategory.MAIN, 55000),
    바비큐립(MenuCategory.MAIN, 54000),
    해산물파스타(MenuCategory.MAIN, 35000),
    크리스마스파스타(MenuCategory.MAIN, 25000),

    초코케이크(MenuCategory.DESSERT, 15000),
    아이스크림(MenuCategory.DESSERT, 5000),

    제로콜라(MenuCategory.DRINK, 3000),
    레드와인(MenuCategory.DRINK, 60000),
    샴페인(MenuCategory.DRINK, 25000);

    private final MenuCategory menuCategory;
    private final int price;

    Menu(MenuCategory menuCategory, int price) {
        this.menuCategory = menuCategory;
        this.price = price;
    }

    public MenuCategory getCategory() {
        return menuCategory;
    }

    public int getPrice() {
        return price;
    }
}
