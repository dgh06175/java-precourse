package baseball.exception;

import static baseball.domain.GameConstant.MAX_NUMBER;
import static baseball.domain.GameConstant.MIN_NUMBER;
import static baseball.domain.GameConstant.NUMBER_COUNT;

public enum ExceptionMessage {
    RESTART_NUMBER_EXCEPTION("1 또는 2를 입력해주세요"),
    NUMBER_COUNT_EXCEPTION("숫자는 정확히 " + NUMBER_COUNT + "개를 입력해야 합니다."),
    NUMBER_SIZE_EXCEPTION("숫자는 " + MIN_NUMBER + "에서 " + MAX_NUMBER + " 사이의 값 이어야 합니다."),
    DUPLICATE_NUMBER_EXCEPTION("숫자는 중복되지 않아야 합니다."),
    RETRY_NUMBER_COUNT_INVALID_EXCEPTION("한 자리 숫자를 입력해야 합니다."),
    NUMBER_INPUT_STRING_EXCEPTION("숫자로만 이루어진 값을 입력해주세요."),
    INTEGER_EXCEPTION("정수 값을 입력해주세요.");

    public final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
