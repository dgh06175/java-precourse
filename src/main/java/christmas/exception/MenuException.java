package christmas.exception;

public class MenuException extends EventException{
    private static final String MENU = "메뉴";
    public MenuException() {
        super(MENU);
    }
}
