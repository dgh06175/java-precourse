package christmas.domain.events;

import static christmas.domain.testUtil.createBigOrder;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Date;
import christmas.domain.OrderedMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasDDayEventTest {
    private ChristmasDDayEvent event;
    private OrderedMenu bigOrder;
    private Date date;

    @BeforeEach
    void setGiveAwayEvent() {
        event = new ChristmasDDayEvent();
        bigOrder = createBigOrder();
    }

    @DisplayName("디데이 할인 이벤트 적용 검사")
    @Test
    void checkValidChristmasDDayEvent() {
        date = new Date(19);
        int discount = event.getDiscountOf(date, bigOrder);

        assertThat(discount).isEqualTo(2800);
    }

    @DisplayName("디데이 할인 이벤트 미적용 검사")
    @Test
    void checkInvalidChristmasDDayEvent() {
        date = new Date(26);
        int discount = event.getDiscountOf(date, bigOrder);

        assertThat(discount).isEqualTo(0);
    }
}
