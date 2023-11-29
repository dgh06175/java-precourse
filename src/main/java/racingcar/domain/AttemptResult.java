package racingcar.domain;

import java.util.Map;

public class AttemptResult {
    private Map<String, Integer> value;

    public void put(String name, int position) {
        value.put(name, position);
    }

    public Map<String, Integer> getValue() {
        return value;
    }
}
