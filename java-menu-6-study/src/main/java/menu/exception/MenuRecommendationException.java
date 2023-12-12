package menu.exception;

public class MenuRecommendationException extends IllegalStateException {
    private static final String ERROR_PREFIX = "[ERROR]";

    public MenuRecommendationException(String message) {
        super(String.format("%s %s", ERROR_PREFIX, message));
    }
}
