package christmas.domain;

import christmas.domain.enums.Menu;
import christmas.exception.OrderException;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuService {
    public static EnumMap<Menu, Integer> stringToMenu(Map<String, Integer> parsedStringOrder) {
        EnumMap<Menu, Integer> order;
        try {
            order = getMenuMap(parsedStringOrder);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new OrderException();
        }
        return order;
    }

    private static EnumMap<Menu, Integer> getMenuMap(Map<String, Integer> parsedStringOrder) {
        return parsedStringOrder.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> Menu.of(e.getKey()),
                        Map.Entry::getValue,
                        (existing, replacement) -> existing,
                        () -> new EnumMap<>(Menu.class)
                ));
    }
}
