package christmas.domain.events;

import static christmas.constant.CHRISTMAS_DAY;
import static christmas.constant.END_OF_DECEMBER_DAY;
import static christmas.constant.START_OF_MONTH_DAY;

import christmas.domain.Date;
import christmas.domain.OrderedMenu;
import christmas.domain.enums.MenuCategory;

public class Special extends AbstractEvent {
    private static final int SALE_AMOUNT = 1000;
    private static final String SALE_NAME = "특별 할인";

    public Special() {
        super(SALE_NAME, START_OF_MONTH_DAY, END_OF_DECEMBER_DAY);
    }

    @Override
    public boolean isSpecificEventValid(Date date, OrderedMenu orderedMenu) {
        return isSpecial(date);
    }

    @Override
    public int calculateDiscount(Date date, OrderedMenu orderedMenu) {
        return SALE_AMOUNT;
    }

    private boolean isSpecial(Date date) {
        return date.value % 7 == 3 || date.value == CHRISTMAS_DAY;
    }
}
