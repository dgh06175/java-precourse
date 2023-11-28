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

import baseball.domain.GameResult;
import java.util.Map;

public class OutputView {
    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printBaseballNumberInputMessage() {
        System.out.print(BASEBALL_NUMBER_INPUT_MESSAGE);
    }

    public void printNumberCompareResult(GameResult result) {
        System.out.println(result.formatResult());
    }


    public void printWinMessage() {
        System.out.println(WIN_MESSAGE);
    }

    public void printRestartMessage() {
        System.out.println(RESTART_MESSAGE);
    }
}
