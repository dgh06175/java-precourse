package baseball.controller;

import baseball.domain.NumberBaseballGame;
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
        NumberBaseballGame game = new NumberBaseballGame(numberGenerator);
        List<Integer> userNumbers;
        do {
            outputView.printBaseballNumberInputMessage();
            userNumbers = inputView.inputBaseballNumbers();
            outputView.printNumberCompareResult(game.getResultWith(userNumbers));
        } while (!game.isWin(userNumbers));
        outputView.printWinMessage();
    }

    private boolean isRestartGame() {
        outputView.printRestartMessage();
        return inputView.inputRetryNumber() == 1;
    }
}
