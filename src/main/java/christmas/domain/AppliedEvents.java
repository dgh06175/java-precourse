package christmas.domain;

import christmas.domain.enums.Event;
import java.util.EnumMap;
import java.util.Map;

public class AppliedEvents {
    private final EnumMap<Event, Integer> events;

    private AppliedEvents(Map<Event, Integer> events) {
        this.events = new EnumMap<>(events);
    }

    public static AppliedEvents of(Order order, Date date) {
        EnumMap<Event, Integer> events = new EnumMap<>(Event.class);
        for(Event event: Event.values()) {
            events.put(event, event.calculateDiscount(date, order));
        }
        return new AppliedEvents(events);
    }

    public int getTotalDiscount() {
        return events.keySet().stream()
                .mapToInt(event -> events.getOrDefault(event, 0))
                .sum();
    }

    public int getDiscountOf(Event event) {
        return events.get(event);
    }

    public boolean containsGiveawayEvent() {
        return events.get(Event.GIVEAWAY) > 0;
    }
}
