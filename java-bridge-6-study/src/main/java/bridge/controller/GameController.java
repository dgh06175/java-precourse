package bridge.controller;

import static bridge.exception.ErrorMessage.INVALID_MOVE_INPUT;

import bridge.domain.Bridge;
import bridge.domain.BridgeGame;
import bridge.domain.BridgeMaker;
import bridge.domain.Player;
import bridge.exception.InvalidInputException;
import bridge.util.BridgeNumberGenerator;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.function.Supplier;

public class GameController {
    private final BridgeNumberGenerator bridgeNumberGenerator;
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private BridgeGame bridgeGame;

    public GameController(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public void run() {
        bridgeGame = initBridgeGame();
        boolean continuePlaying = true;

        while(continuePlaying) {
            String moving = requestMoving();
            boolean moveSuccess = bridgeGame.move(moving);
            outputView.printMap(bridgeGame.getBridge().getBridge(), bridgeGame.getPlayerPosition(), moveSuccess);
            if (bridgeGame.isGameWin()) {
                break;
            }
            if (!moveSuccess) {
                continuePlaying = handleRetry();
            }
        }

        outputView.printResultMessage();
        outputView.printMap(bridgeGame.getBridge().getBridge(), bridgeGame.getPlayerPosition(), bridgeGame.isGameWin());
        outputView.printResult(bridgeGame.isGameWin(), bridgeGame.getAttemptCount());
    }

    private BridgeGame initBridgeGame() {
        return executeWithRetry(() -> {
            int bridgeSize = inputView.readBridgeSize();
            BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
            Bridge bridge = new Bridge(bridgeMaker.makeBridge(bridgeSize));
            return new BridgeGame(bridge, new Player());
        });
    }

    private boolean handleRetry() {
        return executeWithRetry(() -> {
            String retryCommand = inputView.readGameCommand();
            if (retryCommand.equals("R")) {
                bridgeGame.retry();
                return true;
            }
            return false;
        });
    }

    private String requestMoving() {
        return executeWithRetry(() -> {
            String moving = inputView.readMoving();
            validateMoving(moving);
            return moving;
        });
    }

    private void validateMoving(String moving) {
        if (!moving.equals("U") && !moving.equals("D")) {
            throw new InvalidInputException(INVALID_MOVE_INPUT.message);
        }
    }

    private <T> T executeWithRetry(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

}
