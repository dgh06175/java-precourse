package menu.exception;

public class InvalidInputException extends IllegalArgumentException {
    private static final String ERROR_PREFIX = "[ERROR]";

    public InvalidInputException(String message) {
        super(String.format("%s %s", ERROR_PREFIX, message));
    }
}
