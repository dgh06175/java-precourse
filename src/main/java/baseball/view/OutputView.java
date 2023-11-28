package baseball.view;

import static baseball.domain.DomainConstant.BALL;
import static baseball.domain.DomainConstant.STRIKE;
import static baseball.view.ViewConstant.BALL_KR;
import static baseball.view.ViewConstant.BASEBALL_NUMBER_INPUT_MESSAGE;
import static baseball.view.ViewConstant.NOTHING;
import static baseball.view.ViewConstant.RESTART_MESSAGE;
import static baseball.view.ViewConstant.START_MESSAGE;
import static baseball.view.ViewConstant.STRIKE_KR;
import static baseball.view.ViewConstant.WIN_MESSAGE;

import java.util.Map;

public class OutputView {
    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printBaseballNumberInputMessage() {
        System.out.print(BASEBALL_NUMBER_INPUT_MESSAGE);
    }

    public void printNumberCompareResult(Map<String, Integer> result) {
        int strike = result.get(STRIKE);
        int ball = result.get(BALL);
        if (strike == 0 && ball == 0) {
            System.out.println(NOTHING);
            return;
        }
        if (ball > 0) {
            System.out.printf("%d" + BALL_KR, ball);
        }
        if (ball > 0 && strike > 0) {
            System.out.print(" ");
        }
        if (strike > 0) {
            System.out.printf("%d" + STRIKE_KR, strike);
        }
        System.out.println();
    }

    public void printWinMessage() {
        System.out.println(WIN_MESSAGE);
    }

    public void printRestartMessage() {
        System.out.println(RESTART_MESSAGE);
    }
}
