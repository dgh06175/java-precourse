package christmas.domain.enums;

import static org.assertj.core.api.Assertions.*;
import christmas.exception.OrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTest {
    @Test
    @DisplayName("메뉴 이름 반환 검사")
    public void shouldReturnCorrectMenuItemForName() {
        assertThat(Menu.of("티본스테이크")).isEqualTo(Menu.T_BONE_STEAK);
    }

    @Test
    @DisplayName("존재하지 않는 메뉴 이름 조회 예외 발생 검사")
    public void shouldThrowExceptionForInvalidMenuName() {
        assertThatThrownBy(() -> Menu.of("라면"))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining("주문");
    }

    @Test
    @DisplayName("메뉴의 문자열을 정확하게 반환 검사")
    public void shouldReturnFormattedStringOfAllMenuItems() {
        String formattedMenu = Menu.formattedMenu();
        assertThat(formattedMenu).contains("티본스테이크");
        assertThat(formattedMenu).contains("초코케이크");
    }

    @Test
    @DisplayName("메뉴 가격 반환 검사")
    public void shouldReturnCorrectPriceForMenuItem() {
        assertThat(Menu.CHOCOLATE_CAKE.getPrice()).isEqualTo(15000);
    }

    @Test
    @DisplayName("주어진 수량에 따른 메뉴 가격 계산 검사")
    public void shouldCalculateCorrectPriceForGivenCount() {
        assertThat(Menu.CHOCOLATE_CAKE.getPrice(2)).isEqualTo(30000);
    }
}
