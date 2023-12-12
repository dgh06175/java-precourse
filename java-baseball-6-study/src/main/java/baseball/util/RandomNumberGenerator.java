package baseball.util;

import static baseball.domain.GameConstant.MAX_NUMBER;
import static baseball.domain.GameConstant.MIN_NUMBER;
import static baseball.domain.GameConstant.NUMBER_COUNT;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator{
    @Override
    public List<Integer> generate() {
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < NUMBER_COUNT) {
            int randomNumber = Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return computer;
    }
}
