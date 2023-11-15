package christmas.domain;

import christmas.domain.dto.MenuQuantity;
import christmas.domain.dto.StringIntPair;
import christmas.domain.enums.Menu;
import christmas.exception.OrderException;
import java.util.ArrayList;
import java.util.List;

public class MenuService {
    /**
     * 메뉴 이름 문자열과 개수를 받아서 메뉴 객체와 개수로 변환
     * @param parsedStringOrder 메뉴 이름 문자열과 개수
     * @return 메뉴 객체와 그 개수
     */
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
