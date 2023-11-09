package christmas.domain;

import christmas.exception.DateException;
import christmas.exception.MenuException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;

public class InputParserTest {

    private InputParser inputParser;

    @BeforeEach
    void setUp() {
        inputParser = new InputParser();
    }

    @DisplayName("날짜")
    @Test
    void testParseDate_ValidInput() {
        String validInputDate = "12";

        int parsedDate = inputParser.parseDate(validInputDate);

        assertThat(parsedDate).isEqualTo(12);
    }

    @Test
    void testParseDate_InvalidInput_ShouldThrowException() {
        // Arrange
        String invalidInputDate = "-1";

        // Act & Assert
        assertThatExceptionOfType(DateException.class).isThrownBy(() -> {
            inputParser.parseDate(invalidInputDate);
        });
    }

    @Test
    void testParseOrder_ValidInput() {
        String validInputOrder = "티본스테이크-1,바비큐립-2,초코케이크-3";

        Map<String, Integer> parsedOrder = inputParser.parseOrder(validInputOrder);

        assertThat(parsedOrder)
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        "티본스테이크", 1,
                        "바비큐립", 2,
                        "초코케이크", 3
                ));
    }

    @Test
    void testParseOrder_InvalidInput_ShouldThrowException() {
        String invalidInputOrder = "티본스테이크1,바비큐립-2-초코케이크-3";

        assertThatExceptionOfType(MenuException.class).isThrownBy(() -> {
            inputParser.parseOrder(invalidInputOrder);
        });
    }
}
