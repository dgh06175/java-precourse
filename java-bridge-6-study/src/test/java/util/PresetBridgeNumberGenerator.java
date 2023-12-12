package util;

import bridge.util.BridgeNumberGenerator;

public class PresetBridgeNumberGenerator implements BridgeNumberGenerator {
    private final int presetNumber; // 예시

    public PresetBridgeNumberGenerator(int presetNumber) { // 예시
        this.presetNumber = presetNumber;
    }

    @Override
    public int generate() {
        return presetNumber;
    }
}
