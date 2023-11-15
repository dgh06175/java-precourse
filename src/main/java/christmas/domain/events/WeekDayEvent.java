package christmas.domain.events;

import static christmas.domain.events.Constant.START_OF_MONTH_DAY;

import christmas.domain.OrderedMenu;
import christmas.domain.enums.MenuCategory;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekDayEvent extends Event {
    private static final int SALE_AMOUNT = 2023;
    private static final String SALE_NAME = "평일 할인";

    public WeekDayEvent(LocalDate eventDate) {
        super(eventDate, SALE_NAME, START_OF_MONTH_DAY, eventDate.lengthOfMonth());
    }

    @Override
    protected boolean isSpecificEventValid(LocalDate visitDate, OrderedMenu orderedMenu) {
        return !isWeekEnd(visitDate);
    }

    @Override
    protected int calculateDiscount(LocalDate visitDate, OrderedMenu orderedMenu) {
        // 디저트 메뉴 개수 * 2023 반환
        return orderedMenu.getDiscountByCategory(MenuCategory.DESSERT, SALE_AMOUNT);
    }

    private boolean isWeekEnd(LocalDate visitDate) {
        return visitDate.getDayOfWeek() == DayOfWeek.FRIDAY || visitDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }
}
