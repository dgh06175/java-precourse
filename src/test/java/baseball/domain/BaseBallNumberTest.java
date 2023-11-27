package baseball.domain;
import baseball.domain.BaseBallNumber;
import baseball.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BaseBallNumberTest {

    @Nested
    @DisplayName("생성자")
    class ConstructorTest {
        @Test
        @DisplayName("유효한 숫자 리스트")
        void validNumbers() {
            assertThatCode(() -> new BaseBallNumber(Arrays.asList(1, 2, 3))).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("null 입력 시 예외 발생")
        void nullNumbers() {
            assertThatThrownBy(() -> new BaseBallNumber(null))
                    .isInstanceOf(InvalidInputException.class)
                    .hasMessageContaining("숫자는 정확히 3개여야 합니다.");
        }

        @ParameterizedTest
        @CsvSource({"0, 4, 5", "1, 10, 2"})
        @DisplayName("1에서 9 범위를 벗어난 숫자 리스트")
        void numbersOutOfRange(int a, int b, int c) {
            List<Integer> numbers = Arrays.asList(a, b, c);
            assertThatThrownBy(() -> new BaseBallNumber(numbers))
                    .isInstanceOf(InvalidInputException.class)
                    .hasMessageContaining("1에서 9 사이의 값 이어야 합니다.");
        }

        @ParameterizedTest
        @CsvSource({"1, 2", "1, 2, 3, 4"})
        @DisplayName("길이가 3이 아닌 숫자 리스트")
        void invalidLength(String csvNumbers) {
            List<Integer> numbers = Arrays.stream(csvNumbers.split(","))
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .toList();
            assertThatThrownBy(() -> new BaseBallNumber(numbers))
                    .isInstanceOf(InvalidInputException.class)
                    .hasMessageContaining("숫자는 정확히 3개여야 합니다.");
        }
    }

    @Nested
    @DisplayName("calcStrikeWith 메소드")
    class CalcStrikeWithTest {
        @Test
        @DisplayName("모든 숫자가 일치하는 경우")
        void allStrikes() {
            BaseBallNumber numbers = new BaseBallNumber(Arrays.asList(1, 2, 3));
            BaseBallNumber otherNumbers = new BaseBallNumber(Arrays.asList(1, 2, 3));
            int strikes = numbers.calcStrikeWith(otherNumbers);

            assertThat(strikes).isEqualTo(3);
        }

        @Test
        @DisplayName("일부 숫자만 일치하는 경우")
        void someStrikes() {
            BaseBallNumber numbers = new BaseBallNumber(Arrays.asList(1, 2, 3));
            BaseBallNumber otherNumbers = new BaseBallNumber(Arrays.asList(3, 2, 1));
            int strikes = numbers.calcStrikeWith(otherNumbers);

            assertThat(strikes).isEqualTo(1);
        }

        @Test
        @DisplayName("일치하는 숫자가 없는 경우")
        void noStrikes() {
            BaseBallNumber numbers = new BaseBallNumber(Arrays.asList(1, 2, 3));
            BaseBallNumber otherNumbers = new BaseBallNumber(Arrays.asList(4, 5, 6));
            int strikes = numbers.calcStrikeWith(otherNumbers);

            assertThat(strikes).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("calcBallWith 메소드")
    class CalcBallWithTest {
        @Test
        @DisplayName("모든 숫자가 볼인 경우")
        void allBalls() {
            BaseBallNumber numbers = new BaseBallNumber(Arrays.asList(1, 2, 3));
            BaseBallNumber otherNumbers = new BaseBallNumber(Arrays.asList(2, 3, 1));
            int balls = numbers.calcBallWith(otherNumbers);

            assertThat(balls).isEqualTo(3);
        }

        @Test
        @DisplayName("일부 숫자만 볼인 경우")
        void someBalls() {
            BaseBallNumber numbers = new BaseBallNumber(Arrays.asList(1, 2, 3));
            BaseBallNumber otherNumbers = new BaseBallNumber(Arrays.asList(1, 3, 4));
            int balls = numbers.calcBallWith(otherNumbers);

            assertThat(balls).isEqualTo(1);
        }

        @Test
        @DisplayName("볼이 없는 경우")
        void noBalls() {
            BaseBallNumber numbers = new BaseBallNumber(Arrays.asList(1, 2, 3));
            BaseBallNumber otherNumbers = new BaseBallNumber(Arrays.asList(4, 5, 6));
            int balls = numbers.calcBallWith(otherNumbers);

            assertThat(balls).isEqualTo(0);
        }
    }
}
