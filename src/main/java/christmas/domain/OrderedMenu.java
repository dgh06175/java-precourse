package christmas.domain;

import christmas.domain.enums.Menu;
import christmas.domain.enums.MenuCategory;
import christmas.exception.OrderException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class OrderedMenu {
    private static final int MAX_MENU_QUANTITY = 20;
    private final EnumMap<Menu, Integer> value;

    public OrderedMenu(EnumMap<Menu, Integer> parsedOrder) {
        validateOrder(parsedOrder);
        this.value = new EnumMap<>(parsedOrder);
    }

    public int getTotalPrice() {
        return value.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice(entry.getValue()))
                .sum();
    }

    public Map<String, Integer> getMenuStringAndCount() {
        Map<String, Integer> menuStringAndCount = new HashMap<>();
        for(Map.Entry<Menu, Integer> entry: value.entrySet()) {
            menuStringAndCount.put(entry.getKey().displayName, entry.getValue());
        }
        return menuStringAndCount;
    }

    public int getDiscountByCategory(MenuCategory category, int discountAmount) {
        return value.entrySet().stream()
                .filter(entry -> entry.getKey().isCategoryEquals(category))
                .mapToInt(Entry::getValue)
                .sum() * discountAmount;
    }

    private void validateOrder(EnumMap<Menu, Integer> order) {
        if (isAnyMenuQuantityLowerThanOne(order)) {
            throw new OrderException();
        }
        if (isTotalMenuQuantityMoreThanMax(order)) {
            throw new OrderException();
        }
        if (isEveryMenuCategoryIsDrink(order)) {
            throw new OrderException();
        }
    }

    private boolean isAnyMenuQuantityLowerThanOne(EnumMap<Menu, Integer> order) {
        return order.values().stream().anyMatch(quantity -> quantity < 1);
    }

    private boolean isTotalMenuQuantityMoreThanMax(EnumMap<Menu, Integer> order) {
        return order.values().stream().mapToInt(Integer::intValue).sum() > MAX_MENU_QUANTITY;
    }

    private boolean isEveryMenuCategoryIsDrink(EnumMap<Menu, Integer> order) {
        return order.keySet().stream().allMatch(menu -> menu.isCategoryEquals(MenuCategory.DRINK));
    }
}
