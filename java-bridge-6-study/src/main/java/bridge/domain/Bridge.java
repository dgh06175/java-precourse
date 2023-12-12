package bridge.domain;

import java.util.Collections;
import java.util.List;

public class Bridge {
    private final List<String> bridgeParts;

    public Bridge(List<String> bridgeParts) {
        this.bridgeParts = bridgeParts;
    }

    public boolean canPlayerMove(int position, String location) {
        return bridgeParts.get(position).equals(location);
    }

    public List<String> getBridge() {
        return Collections.unmodifiableList(bridgeParts);
    }

    public int getBridgeSize() {
        return bridgeParts.size();
    }
}
