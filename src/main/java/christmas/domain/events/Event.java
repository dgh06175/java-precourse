package christmas.domain.events;

import christmas.domain.Date;
import christmas.domain.OrderedMenu;

public interface Event {
    int getDiscountOf(Date date, OrderedMenu orderedMenu);
    String getName();
}
