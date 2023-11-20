package christmas.domain.events;

import static christmas.domain.testUtil.createBigOrder;
import static christmas.domain.testUtil.eventDate;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.OrderedMenu;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialEventTest {
    private SpecialEvent event;
    private OrderedMenu bigOrder;
    private LocalDate visitDate;

    @BeforeEach
    void setGiveAwayEvent() {
        event = new SpecialEvent(eventDate);
        bigOrder = createBigOrder();
    }

    @DisplayName("특별 할인 이벤트 적용 검사")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void checkValidSpecialEvent(int validDay) {
        visitDate = LocalDate.of(eventDate.getYear(), eventDate.getMonth(), validDay);
        int discount = event.getDiscountOf(visitDate, bigOrder);

        assertThat(discount).isEqualTo(1000);
    }

    @DisplayName("특별 할인 이벤트 미적용 검사")
    @ParameterizedTest
    @ValueSource(ints = {1, 9, 18, 26, 23, 30})
    void checkInvalidSpecialEvent(int invalidDay) {
        visitDate = LocalDate.of(eventDate.getYear(), eventDate.getMonth(), invalidDay);
        int discount = event.getDiscountOf(visitDate, bigOrder);

        assertThat(discount).isEqualTo(0);
    }
}
