package christmas.domain.events;

import static christmas.domain.events.Constant.CHRISTMAS_DAY;
import static christmas.domain.events.Constant.START_OF_MONTH_DAY;

import christmas.domain.OrderedMenu;
import java.time.LocalDate;
import java.time.Month;

public class ChristmasDDayEvent extends Event {
    private static final int SALE_START_AMOUNT = 1000;
    private static final int SALE_TIMES_AMOUNT = 100;
    private static final String SALE_NAME = "크리스마스 디데이 할인";

    public ChristmasDDayEvent(LocalDate eventDate) {
        super(eventDate, SALE_NAME, START_OF_MONTH_DAY, CHRISTMAS_DAY);
    }

    /**
     * 12월 1~25일만 적용
     * @param visitDate 방문 날짜
     * @param orderedMenu 주문 메뉴
     * @return 적용 여부
     */
    @Override
    protected boolean isSpecificEventValid(LocalDate visitDate, OrderedMenu orderedMenu) {
        return visitDate.getMonth() == Month.DECEMBER && visitDate.getDayOfMonth() <= CHRISTMAS_DAY;
    }

    /**
     * 할인금은 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
     * @param visitDate 방문 날짜
     * @param orderedMenu 주문 메뉴
     * @return 할인금
     */
    @Override
    protected int calculateDiscount(LocalDate visitDate, OrderedMenu orderedMenu) {
        return SALE_START_AMOUNT + (visitDate.getDayOfMonth() - 1) * SALE_TIMES_AMOUNT;
    }
}
