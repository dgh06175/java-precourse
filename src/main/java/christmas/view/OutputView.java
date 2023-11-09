package christmas.view;

public class OutputView {
    private static final String READ_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String READ_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    public void printReadDateMessage() {
        System.out.println(READ_DATE_MESSAGE);
    }

    public void printReadMenuMessage() {
        System.out.println(READ_MENU_MESSAGE);
    }

    public void printMenu() {
        System.out.println("<주문 메뉴>");
        // ...
    }
}
