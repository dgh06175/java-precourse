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

    @Override
    protected boolean isSpecificEventValid(LocalDate visitDate, OrderedMenu orderedMenu) {
        return orderedMenu.getTotalPrice() >= GIVE_AWAY_MIN_PRICE;
    }

    @Override
    protected int calculateDiscount(LocalDate visitDate, OrderedMenu orderedMenu) {
        // 총 주문 금액 12만원 이상일때 샴페인 증정
        return Menu.CHAMPAGNE.getPrice();
    }
}
