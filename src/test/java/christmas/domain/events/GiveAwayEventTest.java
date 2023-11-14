package christmas.domain.events;

import static christmas.domain.testUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Date;
import christmas.domain.OrderedMenu;
import christmas.domain.enums.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiveAwayEventTest {
    private GiveAwayEvent event;
    private OrderedMenu bigOrder;
    private OrderedMenu simpleOrder;
    private Date date;

    @BeforeEach
    void setGiveAwayEvent() {
        event = new GiveAwayEvent();
        bigOrder = createBigOrder();
        simpleOrder = createSimpleOrder();
    }

    @DisplayName("증정 할인 이벤트 적용 검사")
    @Test
    void checkValidGiveAway() {
        date = new Date(1);
        int discount = event.getDiscountOf(date, bigOrder);

        assertThat(discount).isEqualTo(Menu.CHAMPAGNE.getPrice());
    }

    @DisplayName("증정 할인 이벤트 미적용 검사")
    @Test
    void checkInvalidGiveAway() {
        date = new Date(1);
        int discount = event.getDiscountOf(date, simpleOrder);

        assertThat(discount).isEqualTo(0);
    }
}
