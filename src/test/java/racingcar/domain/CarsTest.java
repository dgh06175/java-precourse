package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.util.PresetNumberGenerator;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarsTest {
    private Cars cars;
    private Car car1;
    private Car car2;

    @BeforeEach
    void setUp() {
        car1 = new Car("car1", new PresetNumberGenerator(4));
        car2 = new Car("car2", new PresetNumberGenerator(3));
        cars = new Cars(Arrays.asList(car1, car2));
    }

    @Test
    void 모든_자동차_이동_시도() {
        cars.allTryMove();
        assertThat(car1.getPosition()).isEqualTo(1);
        assertThat(car2.getPosition()).isZero();
    }

    @Test
    void 우승자_파악() {
        cars.allTryMove();
        List<String> winners = cars.getWinners();
        assertThat(winners).containsExactly("car1");
    }

    @Test
    void 시도_결과_확인() {
        cars.allTryMove();
        AttemptResult result = cars.getAttemptResult();
        assertThat(result.getValue())
                .containsEntry("car1", 1)
                .containsEntry("car2", 0);
    }
}
