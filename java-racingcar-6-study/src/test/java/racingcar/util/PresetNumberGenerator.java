package racingcar.util;

public class PresetNumberGenerator implements NumberGenerator {
    private final int presetNumber; // 예시

    public PresetNumberGenerator(int presetNumber) { // 예시
        this.presetNumber = presetNumber;
    }

    @Override
    public int generate() {
        return presetNumber;
    }
}
