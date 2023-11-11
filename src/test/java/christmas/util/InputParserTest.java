package christmas.util;

import christmas.exception.EventException;
import christmas.exception.InputException;
import christmas.exception.MenuException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class InputParserTest {
    private InputParser inputParser;

    @BeforeEach
    void setUp() {
        inputParser = new InputParser();
    }

    @DisplayName("날짜 입력 테스트")
    @Test
    void testParseDate_ValidInput() {
        String validInputDate = "12";

        int parsedDate = inputParser.parseDate(validInputDate);

        assertThat(parsedDate).isEqualTo(12);
    }

    @DisplayName("잘못된 날짜 입력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "0" })
    void testParseDate_InvalidInput_ShouldThrowException(String invalidDateInput) {
        assertThatThrownBy(() -> inputParser.parseDate(invalidDateInput))
                .isInstanceOf(Exception.class)
                .hasMessageContaining(("[ERROR]"));
    }

    @DisplayName("문자 날짜 입력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"One", "1일" })
    void testParseDate_StringInput_ShouldThrowException(String invalidDateInput) {
        assertThatThrownBy(()->inputParser.parseDate(invalidDateInput))
                .isInstanceOf(NumberFormatException.class);
    }

    @DisplayName("메뉴 입력 테스트")
    @Test
    void testParseOrder_ValidInput() {
        String validInputOrder = "티본스테이크-1,바비큐립-2,초코케이크-3,레드와인-7";

        Map<String, Integer> parsedOrder = inputParser.parseOrder(validInputOrder);

        assertThat(parsedOrder)
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        "티본스테이크", 1,
                        "바비큐립", 2,
                        "초코케이크", 3,
                        "레드와인", 7
                ));
    }

    @DisplayName("잘못된 메뉴 형식 입력 테스트")
    @Test
    void testParseOrder_InvalidInput_ShouldThrowException() {
        String invalidInputOrder = "티본스테이크1,바비큐립-2-초코케이크-3";

        assertThatExceptionOfType(MenuException.class).isThrownBy(() -> {
            inputParser.parseOrder(invalidInputOrder);
        });
    }

    @DisplayName("중복된 메뉴 입력 테스트")
    @Test
    void testParseOrder_DuplicateInput_ShouldThrowException() {
        String duplicateInputOrder = "바비큐립-1,바비큐립-2,초코케이크-3";

        assertThatExceptionOfType(MenuException.class).isThrownBy(() -> {
            inputParser.parseOrder(duplicateInputOrder);
        });
    }
}
