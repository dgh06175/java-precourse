package christmas.domain;

import christmas.domain.enums.Menu;
import christmas.exception.OrderException;
import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuServiceTest {
    @DisplayName("정상 문자열 변환 테스트")
    @Test
    void validParseTest() {
        Map<String, Integer> parsedStringOrder = Map.of(
                "레드와인", 2,
                "바비큐립", 3
        );

        EnumMap<Menu, Integer> result = MenuService.stringToMenu(parsedStringOrder);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(Menu.RED_WINE)).isEqualTo(2);
        assertThat(result.get(Menu.BARBECUE_RIBS)).isEqualTo(3);
    }

    @DisplayName("유효하지 않은 문자열 예외 테스트")
    @Test
    void invalidStringTest() {
        Map<String, Integer> parsedStringOrder = Map.of("라면", 1);

        assertThatThrownBy(() -> MenuService.stringToMenu(parsedStringOrder))
                .isInstanceOf(OrderException.class);
    }
}
