package christmas.exception;

public class InputException extends EventException {
    private static final String INPUT = "입력";
    public InputException() {
        super(INPUT);
    }
}
