package christmas.domain.events;

import static christmas.domain.Constant.CHRISTMAS_DAY;
import static christmas.domain.Constant.START_OF_MONTH_DAY;

import christmas.domain.Date;
import christmas.domain.OrderedMenu;

public class ChristmasDDay extends AbstractEvent {
    private static final int SALE_START_AMOUNT = 1000;
    private static final int SALE_TIMES_AMOUNT = 100;
    private static final String SALE_NAME = "크리스마스 디데이 할인";

    public ChristmasDDay() {
        super(SALE_NAME, START_OF_MONTH_DAY, CHRISTMAS_DAY);
    }

    @Override
    public boolean isSpecificEventValid(Date date, OrderedMenu orderedMenu) {
        return true;
    }

    @Override
    public int calculateDiscount(Date date, OrderedMenu orderedMenu) {
        // 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
        int daysToChristmas = date.value - START_OF_MONTH_DAY;
        return SALE_START_AMOUNT + daysToChristmas * SALE_TIMES_AMOUNT;
    }
}
