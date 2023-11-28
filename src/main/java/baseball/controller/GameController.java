package baseball.controller;

import static baseball.domain.DomainConstant.NOT_RETRY;
import static baseball.domain.DomainConstant.RETRY;
import static baseball.exception.ExceptionMessage.RESTART_NUMBER_EXCEPTION;

import baseball.domain.BaseBallNumber;
import baseball.domain.NumberBaseballGame;
import baseball.exception.InvalidInputException;
import baseball.util.NumberGenerator;
import baseball.view.InputView;
import baseball.view.OutputView;
import java.util.List;
import java.util.Map;

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
        NumberBaseballGame game = new NumberBaseballGame(numberGenerator);
        boolean isWin = false;
        while (!isWin) {
            BaseBallNumber userNumbers = requestUserNumber();
            Map<String, Integer> roundResult = game.getResultWith(userNumbers);
            displayRoundResult(roundResult);
            isWin = game.isWin(userNumbers);
        }
        outputView.printWinMessage();
    }

    private void displayRoundResult(Map<String, Integer> roundResult) {
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
            throw new InvalidInputException(RESTART_NUMBER_EXCEPTION.getMessage());
        }
    }
}
