package christmas.domain;

import christmas.domain.enums.Badge;
import christmas.domain.enums.Menu;
import christmas.domain.events.Event;
import christmas.domain.events.EventFactory;
import christmas.domain.events.GiveAwayEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

public class AppliedEvents {
    private final Map<Event, Integer> eventDiscounts;

    private AppliedEvents(Map<Event, Integer> eventDiscounts) {
        this.eventDiscounts = new HashMap<>(eventDiscounts);
    }

    public static AppliedEvents of(Date date, OrderedMenu orderedMenu) {
        List<Event> events = EventFactory.getAllEvents();
        Map<Event, Integer> eventDiscounts = events.stream()
                .collect(Collectors.toMap(
                        event -> event,
                        event -> event.getDiscountOf(date, orderedMenu)
                ));
        return new AppliedEvents(eventDiscounts);
    }

    public int getTotalDiscount() {
        return eventDiscounts.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getPriceAfterDiscount(int priceBeforeDiscount) {
        int priceAfterDiscount = priceBeforeDiscount - getTotalDiscount();
        if (containsGiveawayEvent()) {
            priceAfterDiscount += Menu.CHAMPAGNE.getPrice();
        }
        return priceAfterDiscount;
    }

    public boolean containsGiveawayEvent() {
        return eventDiscounts.entrySet().stream()
                .anyMatch(entry ->
                        entry.getKey() instanceof GiveAwayEvent && entry.getValue() > 0
                );
    }

    public Map<String, Integer> getEventStringAndPrice() {
        return eventDiscounts.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        Map.Entry::getValue,
                        (existing, replacement) -> existing
                ));
    }

    public Badge getBadge() {
        return Badge.of(getTotalDiscount());
    }
}
