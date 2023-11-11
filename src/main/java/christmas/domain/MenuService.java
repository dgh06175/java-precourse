package christmas.domain;

import christmas.domain.enums.Menu;
import christmas.exception.MenuException;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuService {
    public static EnumMap<Menu, Integer> stringToMenu(Map<String, Integer> parsedStringOrder) {
        EnumMap<Menu, Integer> order;
        try {
            order = parsedStringOrder.entrySet().stream()
                    .collect(Collectors.toMap(
                            e -> Menu.valueOf(e.getKey()),
                            Map.Entry::getValue,
                            (existing, replacement) -> existing,
                            () -> new EnumMap<>(Menu.class)
                    ));
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new MenuException();
        }
        return order;
    }
}
