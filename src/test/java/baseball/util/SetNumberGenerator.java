package baseball.util;

import baseball.domain.BaseBallNumber;
import java.util.Arrays;
import java.util.List;

public class SetNumberGenerator implements NumberGenerator {
    List<Integer> setNumbers;
    public SetNumberGenerator(int a, int b, int c) {
        setNumbers = Arrays.asList(a, b, c);
    }

    @Override
    public List<Integer> generate() {
        return setNumbers;
    }
}
