package christmas.domain.events;

import static christmas.domain.events.Constant.START_OF_MONTH_DAY;

import christmas.domain.OrderedMenu;
import christmas.domain.enums.MenuCategory;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekEndEvent extends Event {
    private static final String SALE_NAME = "주말 할인";

    public WeekEndEvent(LocalDate eventDate) {
        super(eventDate, SALE_NAME, START_OF_MONTH_DAY, eventDate.lengthOfMonth());
    }

    @Override
    protected boolean isSpecificEventValid(LocalDate visitDate, OrderedMenu orderedMenu) {
        return isWeekEnd(visitDate);
    }

    /**
     * 주말일 경우 메인 매뉴 개수 * 연도 할인
     * @param visitDate
     * @param orderedMenu
     * @return
     */
    @Override
    protected int calculateDiscount(LocalDate visitDate, OrderedMenu orderedMenu) {
        // 메인 메뉴 개수 * 2023 반환
        return orderedMenu.getDiscountByCategory(MenuCategory.MAIN, visitDate.getYear());
    }

    private boolean isWeekEnd(LocalDate visitDate) {
        return visitDate.getDayOfWeek() == DayOfWeek.FRIDAY || visitDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }
}
