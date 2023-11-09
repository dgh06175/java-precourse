package christmas.domain.enums;
public enum Menu {
    MUSHROOM_SOUP(MenuCategory.APPETIZER, 6000),
    TAPAS(MenuCategory.APPETIZER, 5500),
    CAESAR_SALAD(MenuCategory.APPETIZER, 8000),

    T_BONE_STEAK(MenuCategory.MAIN, 55000),
    BBQ_RIB(MenuCategory.MAIN, 54000),
    SEAFOOD_PASTA(MenuCategory.MAIN, 35000),
    CHRISTMAS_PASTA(MenuCategory.MAIN, 25000),

    CHOCOLATE_CAKE(MenuCategory.DESSERT, 15000),
    ICE_CREAM(MenuCategory.DESSERT, 5000),

    ZERO_COLA(MenuCategory.DRINK, 3000),
    RED_WINE(MenuCategory.DRINK, 60000),
    CHAMPAGNE(MenuCategory.DRINK, 25000);

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
