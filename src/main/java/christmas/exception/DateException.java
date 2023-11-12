package christmas.exception;

public class DateException extends EventException{
    private static final String DATE = "날짜";

    public DateException() {
        super(DATE);
    }
}
