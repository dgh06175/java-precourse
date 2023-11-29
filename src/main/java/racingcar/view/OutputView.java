package racingcar.view;

import java.util.Map;
import racingcar.domain.AttemptResult;
import racingcar.domain.GameResult;

public class OutputView {
    public void printAttemptResultStartMessage() {
        System.out.println("실행 결과");
    }

    public void printAttemptResult(AttemptResult attemptResult) {
        Map<String, Integer> cars = attemptResult.getValue();
        for (var car: cars.entrySet()) {
            System.out.printf("%s : %s\n", car.getKey(), "-".repeat(car.getValue()));
        }
        System.out.println();
    }

    public void printGameResult(GameResult gameResult) {
        System.out.print("최종 우승자 : ");
        System.out.println(String.join(", ", gameResult.getCarNames()));
    }
}
