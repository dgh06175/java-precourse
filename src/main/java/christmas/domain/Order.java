package christmas.domain;

import christmas.domain.enums.Menu;
import christmas.domain.enums.MenuCategory;
import christmas.exception.OrderException;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private static final int MAX_MENU_QUANTITY = 20;
    private final EnumMap<Menu, Integer> value;

    public Order(EnumMap<Menu, Integer> parsedOrder) {
        validateOrder(parsedOrder);
        this.value = new EnumMap<>(parsedOrder);
    }

    public int getTotalPrice() {
        return value.entrySet().stream()
                .mapToInt(entry -> entry.getKey().price * entry.getValue())
                .sum();
    }

    public Map<String, Integer> getMenuStringAndCount() {
        return value.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().name(), // 키: 메뉴 이름
                        Map.Entry::getValue, // 값: 수량
                        (existing, replacement) -> existing // 충돌 발생 시 기존 값을 유지
                ));
    }

    public int getCountOfMenuCategory(MenuCategory category) {
        int count = 0;
        for(Menu menu: value.keySet()) {
            if (menu.menuCategory == category) {
                count += value.get(menu);
            }
        }
        return count;
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
        return order.keySet().stream().allMatch(menu -> menu.menuCategory == MenuCategory.DRINK);
    }
}
