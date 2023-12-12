package menu.util;

import static menu.constant.Constant.DAYS_SIZE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomGenerator implements Generatable {
    @Override
    public int getRandomNumber() {
        return Randoms.pickNumberInRange(1, DAYS_SIZE);
    }

    @Override
    public String getRandomElementOf(List<String> values) {
        return Randoms.shuffle(values).get(0);
    }
}
