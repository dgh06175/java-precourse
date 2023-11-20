package christmas.domain.events;

import static christmas.domain.events.Constant.START_OF_MONTH_DAY;

import christmas.domain.OrderedMenu;
import christmas.domain.enums.Menu;
import java.time.LocalDate;

public class GiveAwayEvent extends Event {
    private static final int GIVE_AWAY_MIN_PRICE = 120_000;
    private static final String SALE_NAME = "증정 이벤트";

    public GiveAwayEvent(LocalDate eventDate) {
        super(eventDate, SALE_NAME, START_OF_MONTH_DAY, eventDate.lengthOfMonth());
    }

    /**
     * 120,000원 이상인 경우에만 증정 이벤트 적용
     * @param visitDate 방문 날짜
     * @param orderedMenu 주문 메뉴
     * @return 적용 여부
     */
    @Override
    protected boolean isSpecificEventValid(LocalDate visitDate, OrderedMenu orderedMenu) {
        return orderedMenu.getTotalPrice() >= GIVE_AWAY_MIN_PRICE;
    }

    /**
     * 증정 금액 반환
     * @param visitDate 방문 날짜
     * @param orderedMenu 주문 메뉴
     * @return 할인금
     */
    @Override
    protected int calculateDiscount(LocalDate visitDate, OrderedMenu orderedMenu) {
        return Menu.CHAMPAGNE.getPrice();
    }
}
