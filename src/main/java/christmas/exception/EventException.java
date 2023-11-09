package christmas.exception;

public class EventException extends IllegalArgumentException {
    private static final String PREFIX = "[ERROR]";
    private static final String FRONT_MESSAGE = "유효하지 않은";
    private static final String LAST_MESSAGE = "입니다. 다시 입력해 주세요.";
    public EventException(String element) {
        super(String.format("%s %s %s%s", PREFIX,FRONT_MESSAGE, element, LAST_MESSAGE));
    }
}
