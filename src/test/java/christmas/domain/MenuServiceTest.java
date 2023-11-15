    package christmas.domain;

    import christmas.domain.dto.MenuQuantity;
    import christmas.domain.dto.StringIntPair;
    import christmas.domain.enums.Menu;
    import christmas.exception.OrderException;
    import java.util.List;
    import org.junit.jupiter.api.*;

    import static org.assertj.core.api.Assertions.assertThat;
    import static org.assertj.core.api.Assertions.assertThatThrownBy;

    class MenuServiceTest {
        @DisplayName("정상 문자열 변환 테스트")
        @Test
        void validParseTest() {
            List<StringIntPair> parsedStringOrder = List.of(
                    new StringIntPair("레드와인", 2),
                    new StringIntPair("바비큐립", 3)
            );

            List<MenuQuantity> result = MenuService.stringToMenu(parsedStringOrder);

            assertThat(result).containsExactlyInAnyOrder(
                    new MenuQuantity(Menu.RED_WINE, 2),
                    new MenuQuantity(Menu.BARBECUE_RIBS, 3)
            );
        }

        @DisplayName("유효하지 않은 문자열 예외 테스트")
        @Test
        void invalidStringTest() {
            List<StringIntPair> parsedStringOrder = List.of(
                    new StringIntPair("라면", 1)
            );

            assertThatThrownBy(() -> MenuService.stringToMenu(parsedStringOrder))
                    .isInstanceOf(OrderException.class);
        }
    }
