package christmas.domain;

import christmas.domain.enums.Menu;
import christmas.domain.enums.MenuCategory;
import christmas.exception.OrderException;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class OrderedMenu {
    private static final int MAX_MENU_QUANTITY = 20;
    private final EnumMap<Menu, Integer> value;

    public OrderedMenu(EnumMap<Menu, Integer> parsedOrder) {
        validateOrder(parsedOrder);
        this.value = new EnumMap<>(parsedOrder);
    }

    /**
     * @return OrderedMenu 에 저장된 메뉴들 가격 총합
     */
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
        if (isAnyMenuQuantityLowerThanOne(order.values())) {
            throw new OrderException();
        }
        if (isTotalMenuQuantityMoreThanMax(order.values())) {
            throw new OrderException();
        }
        if (isEveryMenuCategoryIsDrink(order.keySet())) {
            throw new OrderException();
        }
    }

    private boolean isAnyMenuQuantityLowerThanOne(Collection<Integer> quantities) {
        return quantities.stream().anyMatch(quantity -> quantity < 1);
    }

    private boolean isTotalMenuQuantityMoreThanMax(Collection<Integer> quantities) {
        return quantities.stream().mapToInt(i -> i).sum() > MAX_MENU_QUANTITY;
    }

    private boolean isEveryMenuCategoryIsDrink(Set<Menu> menuSet) {
        return menuSet.stream().allMatch(menu -> menu.isCategoryEquals(MenuCategory.DRINK));
    }
}
