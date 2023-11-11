package christmas.domain;

import christmas.domain.enums.Menu;
import christmas.exception.MenuException;
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
        assertThat(result.get(Menu.레드와인)).isEqualTo(2);
        assertThat(result.get(Menu.바비큐립)).isEqualTo(3);
    }

    @DisplayName("유효하지 않은_ 자열 예외 테스트")
    @Test
    void invalidStringTest() {
        Map<String, Integer> parsedStringOrder = Map.of("라면", 1);

        assertThatThrownBy(() -> MenuService.stringToMenu(parsedStringOrder))
                .isInstanceOf(MenuException.class);
    }
}
