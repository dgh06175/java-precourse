package christmas.domain.events;

import christmas.domain.Date;
import christmas.domain.OrderedMenu;

public abstract class AbstractEvent implements Event {
    private static final int EVENT_MIN_PRICE = 10_000;
    private final String name;
    private final Date startDay;
    private final Date endDay;

    AbstractEvent(String name, int startDay, int endDay) {
        this.name = name;
        this.startDay = new Date(startDay);
        this.endDay = new Date(endDay);
    }

    @Override
    public int getDiscountOf(Date date, OrderedMenu orderedMenu) {
        if (!isEventValid(date, orderedMenu)) {
            return 0;
        }
        return calculateDiscount(date, orderedMenu);
    }

    @Override
    public String getName() {
        return name;
    }

    private boolean isEventValid(Date date, OrderedMenu orderedMenu) {
        return isDateValid(date) && isTotalPriceValid(orderedMenu) && isSpecificEventValid(date, orderedMenu);
    }

    private boolean isDateValid(Date date) {
        return date.isDateBetweenClosed(this.startDay, this.endDay);
    }

    private boolean isTotalPriceValid(OrderedMenu orderedMenu) {
        return orderedMenu.getTotalPrice() >= EVENT_MIN_PRICE;
    }

    protected abstract int calculateDiscount(Date date, OrderedMenu orderedMenu);

    protected abstract boolean isSpecificEventValid(Date date, OrderedMenu orderedMenu);
}
