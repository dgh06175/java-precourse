package christmas.domain;

import christmas.domain.enums.Event;
import java.util.EnumMap;
import java.util.Map;

public class AppliedEvents {
    private EnumMap<Event, Integer> events;

    public AppliedEvents(Map<Event, Integer> events) {
        this.events = new EnumMap<>(events);
    }

    public int getEventDiscount(Event event) {
        return events.get(event);
    }
}
