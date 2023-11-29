package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.util.PresetNumberGenerator;

class RaceTest {

    @Nested
    @DisplayName("경주 진행")
    class RaceProgress {

        @Test
        @DisplayName("적정 횟수 내에서 경주 진행")
        void doAttemptWithinMaxAttempt() {
            List<Car> cars = Arrays.asList(new Car("car1", new PresetNumberGenerator(5)),
                    new Car("car2", new PresetNumberGenerator(3)));
            Race race = new Race(cars, 5);

            AttemptResult attemptResult = race.doAttempt();

            assertThat(attemptResult.getValue()).isNotEmpty();
            assertThat(race.isRaceEnd()).isFalse();
        }

        @Test
        @DisplayName("최대 횟수를 초과하면 예외 발생")
        void throwExceptionWhenRaceEnd() {
            List<Car> cars = Arrays.asList(new Car("car1", new PresetNumberGenerator(5)),
                    new Car("car2", new PresetNumberGenerator(3)));
            Race race = new Race(cars, 1);
            race.doAttempt();

            assertThatThrownBy(race::doAttempt)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 레이싱 횟수 에러 발생");
        }
    }

    @Nested
    @DisplayName("게임 결과")
    class GameResults {

        @Test
        @DisplayName("우승자 확인")
        void getWinnersAfterRace() {
            List<Car> cars = Arrays.asList(new Car("win", new PresetNumberGenerator(5)),
                    new Car("lose", new PresetNumberGenerator(3)));
            Race race = new Race(cars, 1);
            race.doAttempt();

            GameResult gameResult = race.getGameResult();

            assertThat(gameResult.getCarNames()).containsExactly("win");
        }
    }
}
