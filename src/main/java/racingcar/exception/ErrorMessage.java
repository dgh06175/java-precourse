package racingcar.exception;

public enum ErrorMessage {
    INVALID_INPUT("유효하지 않은 입력입니다. 다시 입력해 주세요."),
    EMPTY_INPUT("입력값은 비어있을 수 없습니다."),
    NOT_A_NUMBER_INPUT("입력값은 숫자여야 합니다."),
    NAME_LENGTH_ERROR("자동차 이름은 5자 이하여야 합니다."),
    RACE_ERROR("레이싱 횟수 에러 발생");

    public final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
