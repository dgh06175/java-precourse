package christmas.domain.enums;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Date;
import christmas.domain.Order;
import java.util.EnumMap;

public class EventTest {

    @DisplayName("이벤트 최소 금액 부족 검사")
    @Test
    void checkEventValidity() {
        Date date = new Date(25);
        Order order = createSampleOrder2();

        assertThat(Event.CHRISTMAS_D_DAY.isEventValid(date, order)).isFalse();
    }

    @DisplayName("크리스마스 D-데이 이벤트 유효성 검사")
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 25})
    void checkChristmasDDayEventValidity(int day) {
        Date date = new Date(day);
        Order order = createSampleOrder();
        assertThat(Event.CHRISTMAS_D_DAY.isEventValid(date, order)).isTrue();
    }

    @DisplayName("크리스마스 D-데이 이벤트 유효성 실패 검사")
    @Test
    void checkChristmasDDayEventInValidity() {
        Date date = new Date(26);

        Order order = createSampleOrder();

        assertThat(Event.CHRISTMAS_D_DAY.isEventValid(date, order)).isFalse();
    }

    @DisplayName("크리스마스 D-데이 할인 계산")
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 25})
    void calculateChristmasDDayDiscount(int day) {
        Date date = new Date(day);

        Order order = createSampleOrder();
        int discount = Event.CHRISTMAS_D_DAY.calculateDiscount(date, order);

        assertThat(discount).isGreaterThanOrEqualTo(1000);
    }

    @DisplayName("주말 할인 이벤트 유효성 검사")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 29, 30})
    void checkWeekendEventValidity(int day) {
        Date weekendDate = new Date(day);

        Order order = createSampleOrder();

        assertThat(Event.WEEKEND.isEventValid(weekendDate, order)).isTrue();
    }

    @DisplayName("주말 할인 이벤트 유효성 실패 검사")
    @ParameterizedTest
    @ValueSource(ints = {5, 6, 17, 21})
    void checkWeekendEventInValidity(int day) {
        Date weekendDate = new Date(day);

        Order order = createSampleOrder();

        assertThat(Event.WEEKEND.isEventValid(weekendDate, order)).isFalse();
    }

    @DisplayName("주중 할인 이벤트 유효성 검사")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 28})
    void checkWeekDayEventValidity(int day) {
        Date weekendDate = new Date(day);

        Order order = createSampleOrder();

        assertThat(Event.WEEKDAY.isEventValid(weekendDate, order)).isTrue();
        assertThat(Event.WEEKDAY.calculateDiscount(weekendDate, order))
                .isEqualTo(2023 * 3);
    }

    @DisplayName("주중 할인 이벤트 유효성 실패 검사")
    @ParameterizedTest
    @ValueSource(ints = {1, 16, 30, 15})
    void checkWeekDayEventInValidity(int day) {
        Date weekendDate = new Date(day);

        Order order = createSampleOrder();
        Order order2 = createSampleOrder2();

        assertThat(Event.WEEKDAY.isEventValid(weekendDate, order)).isFalse();
        assertThat(Event.WEEKDAY.isEventValid(new Date(1), order2)).isFalse();
    }

    @DisplayName("스페셜 할인 이벤트 유효성 검사")
    @Test
    void checkSpecialEventInValidity() {
        Date specialDate1 = new Date(3);
        Date specialDate2 = new Date(25);
        Date invalidSpecialDate = new Date(26);

        Order order = createSampleOrder();

        assertThat(Event.SPECIAL.isEventValid(specialDate1, order)).isTrue();
        assertThat(Event.SPECIAL.calculateDiscount(specialDate2, order)).isEqualTo(1000);
        assertThat(Event.SPECIAL.isEventValid(invalidSpecialDate, order)).isFalse();
    }

    @DisplayName("샴페인 증정 이벤트 검사")
    @Test
    void checkGiveAwayEventInValidity() {
        Date specialDate1 = new Date(1);

        Order order = createSampleOrder();
        Order order2 = createSampleOrder2();

        int discount1 = Event.GIVEAWAY.calculateDiscount(specialDate1, order);
        int discount2 = Event.GIVEAWAY.calculateDiscount(specialDate1, order2);

        assertThat(Event.GIVEAWAY.isEventValid(specialDate1, order)).isTrue();
        assertThat(discount1).isEqualTo(25000);

        assertThat(Event.GIVEAWAY.isEventValid(specialDate1, order2)).isFalse();
        assertThat(discount2).isEqualTo(0);
    }

    private Order createSampleOrder() {
        EnumMap<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);
        orderDetails.put(Menu.티본스테이크, 1);
        orderDetails.put(Menu.바비큐립, 2);
        orderDetails.put(Menu.타파스, 1);
        orderDetails.put(Menu.크리스마스파스타, 1);
        orderDetails.put(Menu.아이스크림, 3);
        return new Order(orderDetails);
    }

    private Order createSampleOrder2() {
        EnumMap<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);
        orderDetails.put(Menu.타파스, 1);
        orderDetails.put(Menu.제로콜라, 1);
        return new Order(orderDetails);
    }
}
