package christmas.domain;

import christmas.domain.enums.Menu;
import christmas.exception.OrderException;
import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class OrderTest {
    public static final String ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    @DisplayName("메뉴 개수가 1미만일 경우 예외 발생")
    @Test
    void menuQuantityLessThan1() {
        EnumMap<Menu, Integer> parsedOrder = new EnumMap<>(Menu.class);
        parsedOrder.put(Menu.양송이수프, 0);

        assertThatThrownBy(() -> new Order(parsedOrder))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ORDER_ERROR_MESSAGE);
    }

    @DisplayName("메뉴 개수가 20초과일 경우 예외 발생")
    @Test
    void menuTotalQuantityOver20Test() {
        EnumMap<Menu, Integer> parsedOrder = new EnumMap<>(Menu.class);
        parsedOrder.put(Menu.바비큐립, 5);
        parsedOrder.put(Menu.시저샐러드, 3);
        parsedOrder.put(Menu.양송이수프, 6);
        parsedOrder.put(Menu.제로콜라, 7);


        assertThatThrownBy(() -> new Order(parsedOrder))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ORDER_ERROR_MESSAGE);
    }

    @DisplayName("음료만_주문할_경우_예외_발생")
    @Test
    void orderOnlyDrinkTest() {
        EnumMap<Menu, Integer> parsedOrder = new EnumMap<>(Menu.class);
        parsedOrder.put(Menu.제로콜라, 1);
        parsedOrder.put(Menu.샴페인, 1);
        parsedOrder.put(Menu.레드와인, 1);

        assertThatThrownBy(() -> new Order(parsedOrder))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ORDER_ERROR_MESSAGE);
    }

    @DisplayName("총 가격 테스트")
    @Test
    void totalPriceTest() {
        EnumMap<Menu, Integer> parsedOrder = new EnumMap<>(Menu.class);
        parsedOrder.put(Menu.해산물파스타, 2);
        parsedOrder.put(Menu.레드와인, 1);
        parsedOrder.put(Menu.초코케이크, 1);

        assertThat(new Order(parsedOrder).getTotalPrice()).isEqualTo(145000);
    }
}
