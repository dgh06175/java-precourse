package christmas.view;

import christmas.domain.Date;
import christmas.domain.enums.Menu;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("###,###");
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

    public void printReadDateMessage() {
        System.out.println(INIT_MESSAGE);
        System.out.println(READ_DATE_MESSAGE);
    }

    public void printReadOrderMessage() {
        System.out.println(READ_ORDER_MESSAGE);
    }

    public void printMenu(Map<String, Integer> orders, Date date) {
        System.out.printf(EVENT_START_MESSAGE, date.value);
        System.out.println();
        System.out.println();
        System.out.println(ORDER_MENU_HEADER);
        for (String menuName: orders.keySet()) {
            System.out.printf("%s %d개\n", menuName, orders.get(menuName));
        }
    }

    public void printPriceBeforeSale(int priceBeforeSale) {
        System.out.println();
        System.out.println(PRICE_BEFORE_SALE_HEADER);
        String formattedPrice = MONEY_FORMAT.format(priceBeforeSale);
        System.out.printf("%s원\n", formattedPrice);
    }

    public void printGiveAway(boolean hasGiveAway) {
        System.out.println();
        System.out.println(GIVEAWAY_HEADER);
        if (hasGiveAway) {
            System.out.printf("%s %d개\n", Menu.CHAMPAGNE.name, 1);
        } else {
            System.out.println(NO_ITEMS);
        }
    }

    public void printEventList(Map<String, Integer> eventStringAndPrice) {
        System.out.println();
        System.out.println(EVENT_LIST_HEADER);
        if (eventStringAndPrice.values().stream().mapToInt(i -> i).sum() == 0) {
            System.out.println(NO_ITEMS);
            return;
        }
        for(String eventString: eventStringAndPrice.keySet()) {
            String price = MONEY_FORMAT.format(eventStringAndPrice.get(eventString));
            if (price.equals("0")) continue;
            System.out.printf("%s: -%s원\n", eventString, price);
        }
    }

    public void printTotalDiscount(int totalDiscount) {
        System.out.println();
        System.out.println(TOTAL_DISCOUNT_HEADER);
        if (totalDiscount == 0) {
            System.out.println("0원");
            return;
        }
        String price = MONEY_FORMAT.format(totalDiscount);
        System.out.printf("-%s원\n", price);
    }

    public void printExpectedPrice(int expectedPrice) {
        System.out.println();
        System.out.println(EXPECTED_PRICE_HEADER);
        String price = MONEY_FORMAT.format(expectedPrice);
        System.out.println(price + "원");
    }

    public void printEventBadge(String BadgeName) {
        System.out.println();
        System.out.println(EVENT_BADGE_HEADER);
        System.out.println(BadgeName);
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
