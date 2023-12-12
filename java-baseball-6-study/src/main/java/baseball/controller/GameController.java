package baseball.controller;

import static baseball.domain.GameConstant.NOT_RETRY;
import static baseball.domain.GameConstant.RETRY;
import static baseball.exception.ExceptionMessage.RESTART_NUMBER_EXCEPTION;

import baseball.domain.BaseBallNumber;
import baseball.domain.GameResult;
import baseball.domain.BaseballGame;
import baseball.domain.GameStatus;
import baseball.exception.InvalidInputException;
import baseball.util.NumberGenerator;
import baseball.view.InputView;
import baseball.view.OutputView;
import java.util.List;

public class GameController {
    private final NumberGenerator numberGenerator;
    private final OutputView outputView;
    private final InputView inputView;

    public GameController(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.printStartMessage();
        do {
            runGame();
        } while (isRestartGame());
    }

    private void runGame() {
        BaseballGame game = new BaseballGame(numberGenerator);
        GameStatus status;
        do {
            BaseBallNumber userNumbers = requestUserNumber();
            status = game.play(userNumbers);
            displayRoundResult(status.result());
        } while (!status.isWin());
        outputView.printWinMessage();
    }

    private void displayRoundResult(GameResult roundResult) {
        outputView.printNumberCompareResult(roundResult);
    }

    private BaseBallNumber requestUserNumber() {
        outputView.printBaseballNumberInputMessage();
        List<Integer> inputNumbers = inputView.inputBaseballNumbers();
        return new BaseBallNumber(inputNumbers);

    }

    private boolean isRestartGame() {
        outputView.printRestartMessage();
        int restartNumber = inputView.inputRestartNumber();
        validateRestartNumber(restartNumber);
        return restartNumber == RETRY;
    }

    private void validateRestartNumber(int restartNumber) {
        if (restartNumber != RETRY && restartNumber != NOT_RETRY) {
            throw new InvalidInputException(RESTART_NUMBER_EXCEPTION.message);
        }
    }
}
