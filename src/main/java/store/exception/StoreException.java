package store.exception;

public class StoreException extends IllegalArgumentException {
    private static final String ERROR_PREFIX = "[ERROR]";

    public StoreException(StoreError error) {
        super(String.format("%s %s", ERROR_PREFIX, error.message));
    }
}