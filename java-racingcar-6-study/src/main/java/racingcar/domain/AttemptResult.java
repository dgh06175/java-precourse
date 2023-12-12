package racingcar.domain;

import java.util.HashMap;
import java.util.Map;

public class AttemptResult {
    private final Map<String, Integer> value = new HashMap<>();

    public void put(String name, int position) {
        value.put(name, position);
    }

    public Map<String, Integer> getValue() {
        return value;
    }
}
