package christmas.domain.dto;

import christmas.domain.events.Event;

public record EventDiscountPrice(Event event, int discountPrice){}
