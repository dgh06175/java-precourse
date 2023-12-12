package bridge;

import bridge.controller.GameController;
import bridge.util.BridgeNumberGenerator;
import bridge.util.BridgeRandomNumberGenerator;

public class Application {

    public static void main(String[] args) {
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        new GameController(bridgeNumberGenerator).run();
    }
}
