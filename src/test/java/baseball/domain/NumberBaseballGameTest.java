package baseball.domain;
import baseball.util.SetNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class NumberBaseballGameTest {

    @Test
    @DisplayName("getResultWith 메소드 테스트")
    void testGetResultWith() {
        SetNumberGenerator generator = new SetNumberGenerator(1, 2, 3);
        NumberBaseballGame game = new NumberBaseballGame(generator);

        BaseBallNumber userNumber = new BaseBallNumber(Arrays.asList(1, 4, 3));
        Map<String, Integer> result = game.getResultWith(userNumber);

        assertThat(result).containsEntry("Strike", 2).containsEntry("Ball", 0);
    }

    @Test
    @DisplayName("isWin 메소드 테스트 - 승리 조건 충족")
    void testIsWin() {
        SetNumberGenerator generator = new SetNumberGenerator(1, 2, 3);
        NumberBaseballGame game = new NumberBaseballGame(generator);

        BaseBallNumber winningNumber = new BaseBallNumber(Arrays.asList(1, 2, 3));
        boolean isWin = game.isWin(winningNumber);

        assertThat(isWin).isTrue();
    }

    @Test
    @DisplayName("isWin 메소드 테스트 - 승리 조건 미충족")
    void testIsNotWin() {
        SetNumberGenerator generator = new SetNumberGenerator(1, 2, 3);
        NumberBaseballGame game = new NumberBaseballGame(generator);

        BaseBallNumber losingNumber = new BaseBallNumber(Arrays.asList(1, 2, 4));
        boolean isWin = game.isWin(losingNumber);

        assertThat(isWin).isFalse();
    }
}
