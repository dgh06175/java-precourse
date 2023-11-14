package christmas.domain.events;

import static christmas.domain.Constant.END_OF_DECEMBER_DAY;
import static christmas.domain.Constant.START_OF_MONTH_DAY;

import christmas.domain.Date;
import christmas.domain.OrderedMenu;

public class SpecialEvent extends AbstractEvent {
    private static final int SALE_AMOUNT = 1000;
    private static final String SALE_NAME = "특별 할인";

    public SpecialEvent() {
        super(SALE_NAME, START_OF_MONTH_DAY, END_OF_DECEMBER_DAY);
    }

    @Override
    protected boolean isSpecificEventValid(Date date, OrderedMenu orderedMenu) {
        return isSpecial(date);
    }

    @Override
    protected int calculateDiscount(Date date, OrderedMenu orderedMenu) {
        return SALE_AMOUNT;
    }

    private boolean isSpecial(Date date) {
        return date.isSunday() || date.isChristmas();
    }
}
