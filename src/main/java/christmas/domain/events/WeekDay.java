package christmas.domain.events;

import static christmas.constant.END_OF_DECEMBER_DAY;
import static christmas.constant.START_OF_MONTH_DAY;

import christmas.domain.Date;
import christmas.domain.OrderedMenu;
import christmas.domain.enums.MenuCategory;

public class WeekDay extends AbstractEvent {
    private static final int SALE_AMOUNT = 2023;
    private static final String SALE_NAME = "평일 할인";

    public WeekDay() {
        super(SALE_NAME, START_OF_MONTH_DAY, END_OF_DECEMBER_DAY);
    }

    @Override
    public boolean isSpecificEventValid(Date date, OrderedMenu orderedMenu) {
        return !date.isWeekend();
    }

    @Override
    public int calculateDiscount(Date date, OrderedMenu orderedMenu) {
        // 디저트 메뉴 개수 * 2023 반환
        return orderedMenu.getDiscountByCategory(MenuCategory.DESSERT, SALE_AMOUNT);
    }
}
