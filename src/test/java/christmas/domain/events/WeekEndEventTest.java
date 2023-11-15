package christmas.domain.events;

import static christmas.domain.testUtil.createBigOrder;
import static christmas.domain.testUtil.createSampleOrder2;
import static christmas.domain.testUtil.eventDate;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.OrderedMenu;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekEndEventTest {
    private WeekEndEvent event;
    private OrderedMenu bigOrder;
    private LocalDate visitDate;

    @BeforeEach
    void setGiveAwayEvent() {
        event = new WeekEndEvent(eventDate);
        bigOrder = createBigOrder();
    }

    @DisplayName("주말 할인 이벤트 적용 검사")
    @ParameterizedTest
    @ValueSource(ints = {1, 9, 15, 23, 29, 30})
    void checkValidWeekEndEvent(int validDay) {
        visitDate = LocalDate.of(eventDate.getYear(), eventDate.getMonth(), validDay);
        int discount = event.getDiscountOf(visitDate, bigOrder);

        assertThat(discount).isEqualTo(4 * 2023);
    }

    @DisplayName("주말 할인 이벤트 날짜 미적용 검사")
    @ParameterizedTest
    @ValueSource(ints = {4, 12, 17, 20, 24, 25, 28, 31})
    void checkInvalidDateWeekEndEvent(int invalidDay) {
        visitDate = LocalDate.of(eventDate.getYear(), eventDate.getMonth(), invalidDay);
        int discount = event.getDiscountOf(visitDate, bigOrder);

        assertThat(discount).isEqualTo(0);
    }

    @DisplayName("주말 할인 이벤트 메뉴 카테고리 미적용 검사")
    @Test
    void checkInvalidMenuWeekEndEvent() {
        OrderedMenu order = createSampleOrder2();
        visitDate = LocalDate.of(eventDate.getYear(), eventDate.getMonth(), 16);
        int discount = event.getDiscountOf(visitDate, order);

        assertThat(discount).isEqualTo(0);
    }
}
