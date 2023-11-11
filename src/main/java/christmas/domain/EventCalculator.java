package christmas.domain;

import christmas.domain.enums.Event;
import java.util.EnumMap;

public class EventCalculator {
    private final Order order;
    private final Date date;

    public EventCalculator(Order order, Date date) {
        this.order = order;
        this.date = date;
    }

    public AppliedEvents getAppliedEvents() {
        EnumMap<Event, Integer> events = new EnumMap<>(Event.class);
        for(Event event: Event.values()) {
            events.put(event, event.calculateDiscount(date, order));
        }
        return new AppliedEvents(events);
    }
}
