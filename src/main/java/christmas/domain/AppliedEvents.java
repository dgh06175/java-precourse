package christmas.domain;

import christmas.domain.dto.EventDiscountPrice;
import christmas.domain.dto.StringIntPair;
import christmas.domain.enums.Badge;
import christmas.domain.enums.Menu;
import christmas.domain.events.Event;
import christmas.domain.events.EventFactory;
import christmas.domain.events.GiveAwayEvent;
import java.util.ArrayList;
import java.util.List;

public class AppliedEvents {
    private final List<EventDiscountPrice> values;

    private AppliedEvents(List<EventDiscountPrice> values) {
        this.values = values;
    }

    public static AppliedEvents of(Date date, OrderedMenu orderedMenu) {
        return getAppliedEvents(date, orderedMenu);
    }

    private static AppliedEvents getAppliedEvents(Date date, OrderedMenu orderedMenu) {
        List<EventDiscountPrice> eventDiscountPrices = new ArrayList<>();
        for (Event item: EventFactory.getAllEvents()) {
            eventDiscountPrices.add(new EventDiscountPrice(item, item.getDiscountOf(date, orderedMenu)));
        }
        return new AppliedEvents(eventDiscountPrices);
    }

    public int getTotalDiscount() {
        return values
                .stream()
                .mapToInt(EventDiscountPrice::discountPrice)
                .sum();
    }

    public int getPriceAfterDiscount(int priceBeforeDiscount) {
        int priceAfterDiscount = priceBeforeDiscount - getTotalDiscount();
        return priceAfterDiscount + giveAwayDiscount();
    }

    private int giveAwayDiscount() {
        if (containsGiveawayEvent()) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    public boolean containsGiveawayEvent() {
        for (EventDiscountPrice eventDiscountPrice : values) {
            if (isGiveAwayApplied(eventDiscountPrice)) {
                return true;
            }
        }
        return false;
    }

    private boolean isGiveAwayApplied(EventDiscountPrice tmpEvent) {
        return tmpEvent.event() instanceof GiveAwayEvent && tmpEvent.discountPrice() > 0;
    }

    public List<StringIntPair> getEventStringAndPrice() {
        List<StringIntPair> eventStringAndPrice = new ArrayList<>();
        for (var item : values) {
            eventStringAndPrice.add(new StringIntPair(item.event().getName(), item.discountPrice()));
        }
        return eventStringAndPrice;
    }

    public Badge getBadge() {
        return Badge.of(getTotalDiscount());
    }
}
