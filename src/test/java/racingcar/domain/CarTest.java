package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racingcar.exception.InvalidInputException;
import racingcar.util.PresetNumberGenerator;

class CarTest {

    @Nested
    @DisplayName("자동차 생성")
    class CreateCar {

        @Test
        @DisplayName("이름 길이가 초과하면 예외 발생")
        void throwExceptionIfNameIsTooLong() {
            String invalidName = "abcdef";
            PresetNumberGenerator generator = new PresetNumberGenerator(0);

            assertThatThrownBy(() -> new Car(invalidName, generator))
                    .isInstanceOf(InvalidInputException.class)
                    .hasMessageContaining("[ERROR] 자동차 이름은");
        }

        @Test
        @DisplayName("적절한 이름으로 정상 생성")
        void createWithValidName() {
            String validName = "car1";
            PresetNumberGenerator generator = new PresetNumberGenerator(0);

            Car car = new Car(validName, generator);

            assertThat(car.getName()).isEqualTo(validName);
            assertThat(car.getPosition()).isZero();
        }
    }

    @Nested
    @DisplayName("자동차 이동")
    class MoveCar {

        @ParameterizedTest
        @CsvSource({ "4, 1", "3, 0", "9, 1" })
        @DisplayName("특정 조건에 따라 위치 변경")
        void tryMove(int presetNumber, int expectedPosition) {
            String name = "car";
            PresetNumberGenerator generator = new PresetNumberGenerator(presetNumber);

            Car car = new Car(name, generator);
            car.tryMove();

            assertThat(car.getPosition()).isEqualTo(expectedPosition);
        }
    }

    @Nested
    @DisplayName("자동차 비교")
    class CompareCars {

        @Test
        @DisplayName("위치에 따라 자동차 비교")
        void compareToBasedOnPosition() {
            PresetNumberGenerator generatorMove = new PresetNumberGenerator(4);
            PresetNumberGenerator generatorStay = new PresetNumberGenerator(3);
            Car car1 = new Car("car1", generatorMove);
            Car car2 = new Car("car2", generatorStay);

            car1.tryMove(); // car1 이동
            car2.tryMove(); // car2 이동하지 않음

            assertThat(car1.compareTo(car2)).isPositive();
        }
    }
}
