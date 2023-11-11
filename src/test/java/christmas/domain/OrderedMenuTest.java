package christmas.domain;

import christmas.domain.enums.Menu;
import christmas.exception.OrderException;
import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class OrderedMenuTest {
    public static final String ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    @DisplayName("메뉴 개수가 1미만일 경우 예외 발생")
    @Test
    void menuQuantityLessThan1() {
        EnumMap<Menu, Integer> parsedOrder = new EnumMap<>(Menu.class);
        parsedOrder.put(Menu.MUSHROOM_SOUP, 0);

        assertThatThrownBy(() -> new OrderedMenu(parsedOrder))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ORDER_ERROR_MESSAGE);
    }

    @DisplayName("메뉴 개수가 20초과일 경우 예외 발생")
    @Test
    void menuTotalQuantityOver20Test() {
        EnumMap<Menu, Integer> parsedOrder = new EnumMap<>(Menu.class);
        parsedOrder.put(Menu.BARBECUE_RIBS, 5);
        parsedOrder.put(Menu.CAESAR_SALAD, 3);
        parsedOrder.put(Menu.MUSHROOM_SOUP, 6);
        parsedOrder.put(Menu.ZERO_COLA, 7);


        assertThatThrownBy(() -> new OrderedMenu(parsedOrder))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ORDER_ERROR_MESSAGE);
    }

    @DisplayName("음료만_주문할_경우_예외_발생")
    @Test
    void orderOnlyDrinkTest() {
        EnumMap<Menu, Integer> parsedOrder = new EnumMap<>(Menu.class);
        parsedOrder.put(Menu.ZERO_COLA, 1);
        parsedOrder.put(Menu.CHAMPAGNE, 1);
        parsedOrder.put(Menu.RED_WINE, 1);

        assertThatThrownBy(() -> new OrderedMenu(parsedOrder))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ORDER_ERROR_MESSAGE);
    }

    @DisplayName("총 가격 테스트")
    @Test
    void totalPriceTest() {
        EnumMap<Menu, Integer> parsedOrder = new EnumMap<>(Menu.class);
        parsedOrder.put(Menu.SEAFOOD_PASTA, 2);
        parsedOrder.put(Menu.RED_WINE, 1);
        parsedOrder.put(Menu.CHOCOLATE_CAKE, 1);

        assertThat(new OrderedMenu(parsedOrder).getTotalPrice()).isEqualTo(145000);
    }
}
