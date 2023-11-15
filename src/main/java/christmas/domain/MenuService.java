package christmas.domain;

import christmas.domain.dto.MenuQuantity;
import christmas.domain.dto.StringIntPair;
import christmas.domain.enums.Menu;
import christmas.exception.OrderException;
import java.util.ArrayList;
import java.util.List;

public class MenuService {
    public static List<MenuQuantity> stringToMenu(List<StringIntPair> parsedStringOrder) {
        List<MenuQuantity> order;
        try {
            order = getMenuQuantities(parsedStringOrder);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new OrderException();
        }
        return order;
    }

    private static List<MenuQuantity> getMenuQuantities(List<StringIntPair> parsedStringOrder) {
        List<MenuQuantity> menuQuantityList = new ArrayList<>();
        for(var item: parsedStringOrder) {
            menuQuantityList.add(new MenuQuantity(Menu.of(item.string()), item.integer()));
        }
        return menuQuantityList;
    }
}
