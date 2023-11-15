package christmas.domain.events;

import static christmas.domain.testUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.OrderedMenu;

class EventTest {
    private Event event;
    private OrderedMenu bigOrder;
    private OrderedMenu simpleOrder;
    private LocalDate visitDate;

    @BeforeEach
    void setGiveAwayEvent() {
        bigOrder = createBigOrder();
        simpleOrder = createSimpleOrder();
    }

    @DisplayName("이벤트 최소 금액 부족 검사")
    @Test
    void checkEventInValidity() {
        visitDate = LocalDate.of(eventDate.getYear(), eventDate.getMonth(), 25);
        event = new ChristmasDDayEvent(eventDate);

        int discount1 = event.getDiscountOf(visitDate, simpleOrder);

        assertThat(discount1).isEqualTo(0);
    }

    @DisplayName("이벤트 최소 금액 만족 검사")
    @Test
    void checkEventValidity() {
        visitDate = LocalDate.of(eventDate.getYear(), eventDate.getMonth(), 25);
        event = new ChristmasDDayEvent(eventDate);

        int discount1= event.getDiscountOf(visitDate, bigOrder);

        assertThat(discount1).isGreaterThan(0);
    }
}
