package christmas.domain.records;

import christmas.domain.events.Event;

public record EventDiscountPrice(Event event, int discountPrice){}
