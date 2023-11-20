package christmas.domain.events;

import static christmas.domain.testUtil.createBigOrder;
import static christmas.domain.testUtil.eventDate;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.OrderedMenu;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasDDayEventTest {
    private ChristmasDDayEvent event;
    private OrderedMenu bigOrder;

    @BeforeEach
    void setGiveAwayEvent() {
        event = new ChristmasDDayEvent(eventDate);
        bigOrder = createBigOrder();
    }

    @DisplayName("디데이 할인 이벤트 적용 검사")
    @Test
    void checkValidChristmasDDayEvent() {
        LocalDate visitDate = LocalDate.of(eventDate.getYear(), eventDate.getMonth(), 19);
        int discount = event.getDiscountOf(visitDate, bigOrder);

        assertThat(discount).isEqualTo(2800);
    }

    @DisplayName("디데이 할인 이벤트 미적용 검사")
    @Test
    void checkInvalidChristmasDDayEvent() {
        LocalDate visitDate = LocalDate.of(eventDate.getYear(), eventDate.getMonth(), 26);
        int discount = event.getDiscountOf(visitDate, bigOrder);

        assertThat(discount).isEqualTo(0);
    }
}
