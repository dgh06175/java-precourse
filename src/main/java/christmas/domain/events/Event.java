package christmas.domain.events;

import christmas.domain.OrderedMenu;
import java.time.LocalDate;

public abstract class Event {
    private static final int EVENT_MIN_PRICE = 10_000;
    private final String name;
    private final LocalDate startDay;
    private final LocalDate endDay;

    Event(LocalDate eventDate, String name, int startDay, int endDay) {
        this.name = name;
        this.startDay = LocalDate.of(eventDate.getYear(), eventDate.getMonth(), startDay);
        this.endDay = LocalDate.of(eventDate.getYear(), eventDate.getMonth(), endDay);
    }

    public int getDiscountOf(LocalDate visitDate, OrderedMenu orderedMenu) {
        if (!isEventValid(visitDate, orderedMenu)) {
            return 0;
        }
        return calculateDiscount(visitDate, orderedMenu);
    }

    public String getName() {
        return name;
    }

    private boolean isEventValid(LocalDate visitDate, OrderedMenu orderedMenu) {
        return isDateIncludeInEventDays(visitDate) && isTotalPriceValid(orderedMenu) && isSpecificEventValid(visitDate, orderedMenu);
    }

    private boolean isDateIncludeInEventDays(LocalDate visitDate) {
        return visitDate.isAfter(startDay) || visitDate.isEqual(startDay) &&
        (visitDate.isBefore(endDay) || visitDate.isEqual(endDay));
    }

    private boolean isTotalPriceValid(OrderedMenu orderedMenu) {
        return orderedMenu.getTotalPrice() >= EVENT_MIN_PRICE;
    }

    protected abstract int calculateDiscount(LocalDate visitDate, OrderedMenu orderedMenu);

    protected abstract boolean isSpecificEventValid(LocalDate visitDate, OrderedMenu orderedMenu);
}
