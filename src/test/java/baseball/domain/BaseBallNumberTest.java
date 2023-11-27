package baseball.domain;
import baseball.exception.InvalidInputException;
import baseball.util.SetNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class BaseBallNumberTest {

    @Nested
    @DisplayName("BaseBallNumber 생성자")
    class ConstructorTest {
        @Test
        @DisplayName("유효한 숫자 리스트로 객체 생성")
        void validNumbers() {
            SetNumberGenerator generator = new SetNumberGenerator(1, 2, 3);
            List<Integer> numbers = generator.generate();

            assertThatCode(() -> new BaseBallNumber(numbers)).doesNotThrowAnyException();
        }

        @ParameterizedTest
        @CsvSource({"0, 4, 5", "1, 10, 2"})
        @DisplayName("유효하지 않은 숫자 리스트로 객체 생성 시 예외 발생")
        void invalidNumbers(int a, int b, int c) {
            SetNumberGenerator generator = new SetNumberGenerator(a, b, c);
            List<Integer> numbers = generator.generate();

            assertThatThrownBy(() -> new BaseBallNumber(numbers))
                    .isInstanceOf(InvalidInputException.class)
                    .hasMessageContaining("숫자는 1에서 9 사이의 값 이어야 합니다.");
        }
    }

    @Nested
    @DisplayName("compareTo 메소드")
    class CompareToTest {
        @Test
        @DisplayName("모든 숫자가 일치하는 경우")
        void allMatch() {
            SetNumberGenerator generator1 = new SetNumberGenerator(1, 2, 3);
            SetNumberGenerator generator2 = new SetNumberGenerator(1, 2, 3);
            BaseBallNumber numbers = new BaseBallNumber(generator1.generate());
            BaseBallNumber otherNumbers = new BaseBallNumber(generator2.generate());

            Map<String, Integer> result = numbers.compareTo(otherNumbers);

            assertThat(result).containsEntry("Strike", 3).containsEntry("Ball", 0);
        }

        @Test
        @DisplayName("모든 숫자가 일치하지 않는 경우")
        void noMatch() {
            SetNumberGenerator generator1 = new SetNumberGenerator(1, 2, 3);
            SetNumberGenerator generator2 = new SetNumberGenerator(4, 5, 6);
            BaseBallNumber numbers = new BaseBallNumber(generator1.generate());
            BaseBallNumber otherNumbers = new BaseBallNumber(generator2.generate());

            Map<String, Integer> result = numbers.compareTo(otherNumbers);

            assertThat(result).containsEntry("Strike", 0).containsEntry("Ball", 0);
        }

        @Test
        @DisplayName("일부 숫자만 일치하는 경우")
        void partialMatch() {
            SetNumberGenerator generator1 = new SetNumberGenerator(1, 2, 3);
            SetNumberGenerator generator2 = new SetNumberGenerator(1, 4, 2);
            BaseBallNumber numbers = new BaseBallNumber(generator1.generate());
            BaseBallNumber otherNumbers = new BaseBallNumber(generator2.generate());

            Map<String, Integer> result = numbers.compareTo(otherNumbers);

            assertThat(result).containsEntry("Strike", 1).containsEntry("Ball", 1);
        }
    }
}
