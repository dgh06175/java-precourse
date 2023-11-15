package christmas.domain;

import christmas.domain.dto.EventDiscountPrice;
import christmas.domain.dto.StringIntPair;
import christmas.domain.enums.Badge;
import christmas.domain.enums.Menu;
import christmas.domain.events.Event;
import christmas.domain.events.EventFactory;
import christmas.domain.events.GiveAwayEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppliedEvents {
    private final List<EventDiscountPrice> values;

    private AppliedEvents(List<EventDiscountPrice> values) {
        this.values = values;
    }

    public static AppliedEvents of(LocalDate visitDate, OrderedMenu orderedMenu) {
        return getAppliedEvents(visitDate, orderedMenu);
    }

    /**
     * 방문 날짜와 주문 메뉴를 받아, 적용되는 모든 이벤트와 그에 맞는 할인금 저장
     * @param visitDate 방문 날짜
     * @param orderedMenu 주문 메뉴
     */
    private static AppliedEvents getAppliedEvents(LocalDate visitDate, OrderedMenu orderedMenu) {
        List<EventDiscountPrice> eventDiscountPrices = new ArrayList<>();
        for (Event item: EventFactory.getAllEvents(visitDate)) {
            eventDiscountPrices.add(new EventDiscountPrice(item, item.getDiscountOf(visitDate, orderedMenu)));
        }
        return new AppliedEvents(eventDiscountPrices);
    }

    /**
     * @return 총혜택 금액
     */
    public int getTotalDiscount() {
        return values
                .stream()
                .mapToInt(EventDiscountPrice::discountPrice)
                .sum();
    }

    /**
     * @return 할인 후 예상 결제 금액
     */
    public int getPriceAfterDiscount(int priceBeforeDiscount) {
        int priceAfterDiscount = priceBeforeDiscount - getTotalDiscount();
        return priceAfterDiscount + giveAwayDiscount();
    }

    public boolean containsGiveawayEvent() {
        for (EventDiscountPrice eventDiscountPrice : values) {
            if (isGiveAwayApplied(eventDiscountPrice)) {
                return true;
            }
        }
        return false;
    }

    private int giveAwayDiscount() {
        if (containsGiveawayEvent()) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    private boolean isGiveAwayApplied(EventDiscountPrice tmpEvent) {
        return tmpEvent.event() instanceof GiveAwayEvent && tmpEvent.discountPrice() > 0;
    }

    /**
     * @return 출력을 위한 이벤트 문자열과 할인금 dto
     */
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
