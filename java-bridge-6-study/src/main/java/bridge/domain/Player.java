package bridge.domain;

public class Player {
    private int position;

    public Player() {
        position = -1;
    }

    public boolean tryMove(Bridge bridge, String location) {
        position++;
        return bridge.canPlayerMove(position, location);
    }

    public int getPosition() {
        return position;
    }

    public boolean reachEndOf(Bridge bridge) {
        return bridge.getBridgeSize() - 1 == position;
    }
}
