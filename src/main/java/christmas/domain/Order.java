package christmas.domain;

import christmas.domain.enums.Menu;
import christmas.domain.enums.MenuCategory;
import christmas.exception.OrderException;
import java.util.EnumMap;

public class Order {
    private static final int MAX_MENU_QUANTITY = 20;
    private final EnumMap<Menu, Integer> value;

    public Order(EnumMap<Menu, Integer> parsedOrder) {
        validateOrder(parsedOrder);
        this.value = new EnumMap<>(parsedOrder);
    }

    public int getTotalPrice() {
        return value.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    private void validateOrder(EnumMap<Menu, Integer> order) {
        if (isAnyMenuQuantityLowerThanOne(order)) {
            throw new OrderException();
        }
        if (getTotalMenuQuantity(order) > MAX_MENU_QUANTITY) {
            throw new OrderException();
        }
        if (isAllMenuCategoryIsDrink(order)) {
            throw new OrderException();
        }
    }

    private boolean isAnyMenuQuantityLowerThanOne(EnumMap<Menu, Integer> order) {
        return order.values().stream().anyMatch(quantity -> quantity < 1);
    }

    private int getTotalMenuQuantity(EnumMap<Menu, Integer> order) {
        return order.values().stream().mapToInt(Integer::intValue).sum();
    }

    private boolean isAllMenuCategoryIsDrink(EnumMap<Menu, Integer> order) {
        return order.keySet().stream().allMatch(menu -> menu.getCategory() == MenuCategory.DRINK);
    }
}
