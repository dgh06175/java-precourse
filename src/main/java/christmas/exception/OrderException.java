package christmas.exception;

public class OrderException extends EventException{
    private static final String MENU = "주문";
    public OrderException() {
        super(MENU);
    }
}
