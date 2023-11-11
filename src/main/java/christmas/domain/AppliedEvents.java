package christmas.domain;

import christmas.domain.enums.Badge;
import christmas.domain.enums.Event;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<String, Integer> getEventStringAndPrice() {
        return events.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().name,
                        Map.Entry::getValue,
                        (existing, replacement) -> existing
                ));
    }

    public Badge getBadge() {
        return Badge.of(getTotalDiscount());
    }
}
