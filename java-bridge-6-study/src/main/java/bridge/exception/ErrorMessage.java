package bridge.exception;

public enum ErrorMessage {
    INVALID_INPUT("유효하지 않은 입력입니다. 다시 입력해 주세요."),
    INVALID_BRIDGE_SIZE_INPUT("다리 길이는 3부터 20 사이의 숫자여야 합니다."),
    INVALID_MOVE_INPUT("이동할 칸은 \"U\" 또는 \"D\" 여야 합니다."),
    INVALID_GAME_COMMAND_INPUT("재시작 명령은 \"R\" 또는 \"Q\" 여야 합니다.");

    public final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
