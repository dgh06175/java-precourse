package christmas.domain.events;

import static christmas.domain.testUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.Date;
import christmas.domain.OrderedMenu;

class EventTest {
    private Event event;
    private OrderedMenu bigOrder;
    private OrderedMenu simpleOrder;
    private Date date;

    @BeforeEach
    void setGiveAwayEvent() {
        bigOrder = createBigOrder();
        simpleOrder = createSimpleOrder();
    }

    @DisplayName("이벤트 최소 금액 부족 검사")
    @Test
    void checkEventInValidity() {
        date = new Date(25);
        event = new ChristmasDDayEvent();

        int discount1= event.getDiscountOf(date, simpleOrder);

        assertThat(discount1).isEqualTo(0);
    }

    @DisplayName("이벤트 최소 금액 만족 검사")
    @Test
    void checkEventValidity() {
        date = new Date(25);
        event = new ChristmasDDayEvent();

        int discount1= event.getDiscountOf(date, bigOrder);

        assertThat(discount1).isGreaterThan(0);
    }
}
