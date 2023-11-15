package christmas.view;

import christmas.domain.dto.StringIntPair;
import christmas.domain.enums.Menu;
import java.time.LocalDate;
import java.util.List;

public class OutputView {
    private static final String MENU = "<우테코 식당 메뉴>";
    private static final String INIT_MESSAGE = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.";
    private static final String READ_DATE_MESSAGE = "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String READ_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String EVENT_START_MESSAGE = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_HEADER = "<주문 메뉴>";
    private static final String PRICE_BEFORE_SALE_HEADER = "<할인 전 총주문 금액>";
    private static final String GIVEAWAY_HEADER = "<증정 메뉴>";
    private static final String NO_ITEMS = "없음";
    private static final String EVENT_LIST_HEADER = "<혜택 내역>";
    private static final String TOTAL_DISCOUNT_HEADER = "<총혜택 금액>";
    private static final String EXPECTED_PRICE_HEADER = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_HEADER = "<%d월 이벤트 배지>";

    public void printFormattedMenu() {
        printEmptyLine();
        printMessage(MENU);
        printEmptyLine();
        printMessage(Menu.formattedMenu());
        printEmptyLine();
    }

    public void printReadDateMessage(LocalDate visitDate) {
        printMessageWithFormat(INIT_MESSAGE, visitDate.getMonthValue());
        printMessageWithFormat(READ_DATE_MESSAGE, visitDate.getMonthValue());
    }

    public void printReadOrderMessage() {
        printMessage(READ_ORDER_MESSAGE);
    }

    public void printEventStartMessage(LocalDate visitMyDate) {
        printMessageWithFormat(EVENT_START_MESSAGE,
                visitMyDate.getMonthValue(),
                visitMyDate.getDayOfMonth()
        );
    }

    public void printMenu(List<StringIntPair> orders) {
        printEmptyLine();
        printMessage(ORDER_MENU_HEADER);
        for (var item: orders) {
            printMessageWithFormat("%s %d개", item.string(), item.integer());
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
            printMessageWithFormat("%s %d개", Menu.CHAMPAGNE.displayName, 1);
        } else {
            printMessage(NO_ITEMS);
        }
    }

    public void printEventList(List<StringIntPair> eventStringDiscountPrice) {
        printEmptyLine();
        printMessage(EVENT_LIST_HEADER);
        if (isPriceZero(eventStringDiscountPrice)) {
            printMessage(NO_ITEMS);
            return;
        }
        for(var item: eventStringDiscountPrice) {
            int price = item.integer();
            if (price == 0) continue;
            printMessageWithFormat("%s: -%,d원", item.string(), price);
        }
    }

    private static boolean isPriceZero(List<StringIntPair> eventStringDiscountPrice) {
        return eventStringDiscountPrice.stream()
                .map(StringIntPair::integer)
                .mapToInt(i -> i)
                .sum() == 0;
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

    public void printEventBadge(String BadgeName, LocalDate visitDate) {
        printEmptyLine();
        printMessageWithFormat(EVENT_BADGE_HEADER, visitDate.getMonthValue());
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
