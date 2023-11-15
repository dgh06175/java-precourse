package christmas.domain.events;

import static christmas.domain.events.Constant.START_OF_MONTH_DAY;

import christmas.domain.OrderedMenu;
import christmas.domain.enums.MenuCategory;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekDayEvent extends Event {
    private static final String SALE_NAME = "평일 할인";

    public WeekDayEvent(LocalDate eventDate) {
        super(eventDate, SALE_NAME, START_OF_MONTH_DAY, eventDate.lengthOfMonth());
    }

    @Override
    protected boolean isSpecificEventValid(LocalDate visitDate, OrderedMenu orderedMenu) {
        return !isWeekEnd(visitDate);
    }

    /**
     * 주중일 경우 디저트 개수 * 연 할인
     * @param visitDate 방문 날짜
     * @param orderedMenu 주문 메뉴
     * @return 할인금
     */
    @Override
    protected int calculateDiscount(LocalDate visitDate, OrderedMenu orderedMenu) {
        return orderedMenu.getDiscountByCategory(MenuCategory.DESSERT, visitDate.getYear());
    }

    private boolean isWeekEnd(LocalDate visitDate) {
        return visitDate.getDayOfWeek() == DayOfWeek.FRIDAY || visitDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }
}
