package christmas.view;

import christmas.domain.enums.Menu;
import java.util.Map;

public class OutputView {
    private static final String MONEY_FORMAT = "%,d";
    private static final String MENU = "<우테코 식당 메뉴>";
    private static final String INIT_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String READ_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String READ_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String EVENT_START_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_HEADER = "<주문 메뉴>";
    private static final String PRICE_BEFORE_SALE_HEADER = "<할인 전 총주문 금액>";
    private static final String GIVEAWAY_HEADER = "<증정 메뉴>";
    private static final String NO_ITEMS = "없음";
    private static final String EVENT_LIST_HEADER = "<혜택 내역>";
    private static final String TOTAL_DISCOUNT_HEADER = "<총혜택 금액>";
    private static final String EXPECTED_PRICE_HEADER = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_HEADER = "<12월 이벤트 배지>";

    public void printFormattedMenu() {
        printEmptyLine();
        printMessage(MENU);
        printEmptyLine();
        printMessage(Menu.formattedMenu());
        printEmptyLine();
    }

    public void printReadDateMessage() {
        printMessage(INIT_MESSAGE);
        printMessage(READ_DATE_MESSAGE);
    }

    public void printReadOrderMessage() {
        printMessage(READ_ORDER_MESSAGE);
    }

    public void printEventStartMessage(int day) {
        printMessageWithFormat(EVENT_START_MESSAGE, day);
    }

    public void printMenu(Map<String, Integer> orders) {
        printEmptyLine();
        printMessage(ORDER_MENU_HEADER);
        for (String menuName: orders.keySet()) {
            printMessageWithFormat("%s %d개", menuName, orders.get(menuName));
        }
    }

    public void printPriceBeforeSale(int priceBeforeSale) {
        printEmptyLine();
        printMessage(PRICE_BEFORE_SALE_HEADER);
        printMessageWithFormat("%,d원", priceBeforeSale);
    }

    public void printGiveAway(boolean hasGiveAway) {
        printEmptyLine();
        printMessage(GIVEAWAY_HEADER);
        if (hasGiveAway) {
            printMessageWithFormat("%s %d개", Menu.CHAMPAGNE.name, 1);
        } else {
            printMessage(NO_ITEMS);
        }
    }

    public void printEventList(Map<String, Integer> eventStringAndPrice) {
        printEmptyLine();
        printMessage(EVENT_LIST_HEADER);
        if (eventStringAndPrice.values().stream().mapToInt(i -> i).sum() == 0) {
            printMessage(NO_ITEMS);
            return;
        }
        for(String eventString: eventStringAndPrice.keySet()) {
            int price = eventStringAndPrice.get(eventString);
            if (price == 0) continue;
            printMessageWithFormat("%s: -%,d원", eventString, price);
        }
    }

    public void printTotalDiscount(int totalDiscount) {
        printEmptyLine();
        printMessage(TOTAL_DISCOUNT_HEADER);
        if (totalDiscount == 0) {
            printMessage("0원");
            return;
        }
        printMessageWithFormat("-%,d원", totalDiscount);
    }

    public void printExpectedPrice(int expectedPrice) {
        printEmptyLine();
        printMessage(EXPECTED_PRICE_HEADER);
        printMessageWithFormat("%,d원", expectedPrice);
    }

    public void printEventBadge(String BadgeName) {
        printEmptyLine();
        printMessage(EVENT_BADGE_HEADER);
        printMessage(BadgeName);
    }

    public void printErrorMessage(Exception e) {
        printMessage(e.getMessage());
    }

    private void printEmptyLine() {
        System.out.println();
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private void printMessageWithFormat(String format, Object... args) {
        System.out.printf(format + "\n", args);
    }
}
