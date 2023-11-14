package christmas.domain;

import christmas.domain.enums.Badge;
import christmas.domain.enums.Menu;
import christmas.domain.events.Event;
import christmas.domain.events.EventFactory;
import christmas.domain.events.GiveAwayEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.List;

public class AppliedEvents {
    private final Map<Event, Integer> eventDiscounts;

    private AppliedEvents(Map<Event, Integer> eventDiscounts) {
        this.eventDiscounts = new HashMap<>(eventDiscounts);
    }

    public static AppliedEvents of(Date date, OrderedMenu orderedMenu) {
        Map<Event, Integer> eventDiscounts = new HashMap<>();
        for (Event event: EventFactory.getAllEvents()) {
            eventDiscounts.put(event, event.getDiscountOf(date, orderedMenu));
        }
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
        for (Map.Entry<Event, Integer> tmpEntry: eventDiscounts.entrySet()) {
            if (isGiveAwayApplied(tmpEntry)) {
                return true;
            }
        }
        return false;
    }

    private boolean isGiveAwayApplied(Entry<Event, Integer> tmpEntry) {
        return tmpEntry.getKey() instanceof GiveAwayEvent && tmpEntry.getValue() > 0;
    }

    public Map<String, Integer> getEventStringAndPrice() {
        Map<String, Integer> eventStringAndPrice = new HashMap<>();
        for (Map.Entry<Event, Integer> entry : eventDiscounts.entrySet()) {
            eventStringAndPrice.put(entry.getKey().getName(), entry.getValue());
        }
        return eventStringAndPrice;
    }

    public Badge getBadge() {
        return Badge.of(getTotalDiscount());
    }
}
