package christmas.domain;

import christmas.domain.dto.MenuQuantity;
import christmas.domain.enums.Menu;
import christmas.exception.OrderException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class OrderedMenuTest {
    public static final String ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    @DisplayName("메뉴 개수가 하나라도 1미만일 경우 예외 발생")
    @Test
    void menuQuantityLessThan1() {
        List<MenuQuantity> parsedOrder = new ArrayList<>();
        parsedOrder.add(new MenuQuantity(Menu.MUSHROOM_SOUP, 0));

        assertThatThrownBy(() -> new OrderedMenu(parsedOrder))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ORDER_ERROR_MESSAGE);
    }

    @DisplayName("메뉴 개수 총합이 20초과일 경우 예외 발생")
    @Test
    void menuTotalQuantityOver20Test() {
        List<MenuQuantity> parsedOrder = new ArrayList<>();
        parsedOrder.add(new MenuQuantity(Menu.BARBECUE_RIBS, 5));
        parsedOrder.add(new MenuQuantity(Menu.CAESAR_SALAD, 3));
        parsedOrder.add(new MenuQuantity(Menu.MUSHROOM_SOUP, 6));
        parsedOrder.add(new MenuQuantity(Menu.ZERO_COLA, 7));

        assertThatThrownBy(() -> new OrderedMenu(parsedOrder))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ORDER_ERROR_MESSAGE);
    }

    @DisplayName("음료만_주문할_경우_예외_발생")
    @Test
    void orderOnlyDrinkTest() {
        List<MenuQuantity> parsedOrder = new ArrayList<>();
        parsedOrder.add(new MenuQuantity(Menu.ZERO_COLA, 1));
        parsedOrder.add(new MenuQuantity(Menu.CHAMPAGNE, 1));
        parsedOrder.add(new MenuQuantity(Menu.RED_WINE, 1));

        assertThatThrownBy(() -> new OrderedMenu(parsedOrder))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ORDER_ERROR_MESSAGE);
    }

    @DisplayName("총 가격 테스트")
    @Test
    void totalPriceTest() {
        List<MenuQuantity> parsedOrder = new ArrayList<>();
        parsedOrder.add(new MenuQuantity(Menu.SEAFOOD_PASTA, 2));
        parsedOrder.add(new MenuQuantity(Menu.RED_WINE, 1));
        parsedOrder.add(new MenuQuantity(Menu.CHOCOLATE_CAKE, 1));

        assertThat(new OrderedMenu(parsedOrder).getTotalPrice()).isEqualTo(145000);
    }
}
