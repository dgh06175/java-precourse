package christmas.domain.events;

import christmas.domain.enums.Menu;
import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import christmas.domain.Date;
import christmas.domain.OrderedMenu;

class EventTest {
    @DisplayName("이벤트 최소 금액 부족 검사")
    @Test
    void checkEventValidity() {
        Date date = new Date(25);
        OrderedMenu simpleOrder = createSimpleOrder();
        Event event = new ChristmasDDayEvent();

        int discount1= event.getDiscountOf(date, simpleOrder);
        int discount2 = event.getDiscountOf(date, simpleOrder);


        assertThat(discount1).isEqualTo(0);
        assertThat(discount2).isEqualTo(0);
    }

    @DisplayName("크리스마스 D-데이 이벤트 유효성 검사")
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 25})
    void checkChristmasDDayEventValidity(int day) {
        Date date = new Date(day);
        OrderedMenu bigOrder = createBigOrder();

        Event event = new ChristmasDDayEvent();
        assertThat(event.getDiscountOf(date, bigOrder)).isGreaterThan(0);
    }

    @DisplayName("크리스마스 D-데이 이벤트 유효성 실패 검사")
    @Test
    void checkChristmasDDayEventInValidity() {
        Date date = new Date(26);
        OrderedMenu bigOrder = createBigOrder();
        Event event = new ChristmasDDayEvent();

        int discount = event.getDiscountOf(date, bigOrder);

        assertThat(discount).isEqualTo(0);
    }

    @DisplayName("크리스마스 D-데이 할인 계산")
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 25})
    void calculateChristmasDDayDiscount(int day) {
        Date date = new Date(day);
        OrderedMenu bigOrder = createBigOrder();
        Event event = new ChristmasDDayEvent();

        int discount = event.getDiscountOf(date, bigOrder);

        assertThat(discount).isGreaterThanOrEqualTo(1000);
    }

    @DisplayName("주말 할인 이벤트 유효성 검사")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 29, 30})
    void checkWeekendEventValidity(int day) {
        Date weekendDate = new Date(day);
        OrderedMenu bigOrder = createBigOrder();
        Event event = new WeekEndEvent();

        int discount = event.getDiscountOf(weekendDate, bigOrder);

        assertThat(discount).isGreaterThan(0);
    }

    @DisplayName("주말 할인 이벤트 유효성 실패 검사")
    @ParameterizedTest
    @ValueSource(ints = {5, 6, 17, 21})
    void checkWeekendEventInValidity(int day) {
        Date weekendDate = new Date(day);
        OrderedMenu bigOrder = createBigOrder();
        Event event = new WeekEndEvent();

        int discount = event.getDiscountOf(weekendDate, bigOrder);

        assertThat(discount).isEqualTo(0);
    }

    @DisplayName("주중 할인 이벤트 유효성 검사")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 28})
    void checkWeekDayEventValidity(int day) {
        Date weekendDate = new Date(day);
        OrderedMenu bigOrder = createBigOrder();
        Event event = new WeekDayEvent();

        int discount = event.getDiscountOf(weekendDate, bigOrder);

        assertThat(discount).isEqualTo(2023 * 3);
    }

    @DisplayName("주중 할인 이벤트 유효성 실패 검사")
    @ParameterizedTest
    @ValueSource(ints = {1, 16, 30, 15})
    void checkWeekDayEventInValidity(int day) {
        Date weekendDate = new Date(day);
        OrderedMenu bigOrder = createBigOrder();
        OrderedMenu simpleOrder = createSimpleOrder();
        Event event = new WeekDayEvent();

        int discount1 = event.getDiscountOf(weekendDate, bigOrder);
        int discount2 = event.getDiscountOf(weekendDate, simpleOrder);

        assertThat(discount1).isEqualTo(0);
        assertThat(discount2).isEqualTo(0);
    }

    @DisplayName("스페셜 할인 이벤트 유효성 검사")
    @Test
    void checkSpecialEventInValidity() {
        Date specialDate1 = new Date(3);
        Date specialDate2 = new Date(25);
        Date invalidSpecialDate = new Date(26);
        OrderedMenu bigOrder = createBigOrder();
        Event event = new SpecialEvent();

        assertThat(event.getDiscountOf(specialDate1, bigOrder)).isGreaterThan(0);
        assertThat(event.getDiscountOf(specialDate2, bigOrder)).isEqualTo(1000);
        assertThat(event.getDiscountOf(invalidSpecialDate, bigOrder)).isEqualTo(0);
    }

    @DisplayName("샴페인 증정 이벤트 검사")
    @Test
    void checkGiveAwayEventInValidity() {
        Date specialDate1 = new Date(1);
        OrderedMenu orderedMenu = createBigOrder();
        OrderedMenu orderedMenu2 = createSimpleOrder();
        Event event = new GiveAwayEvent();

        int discount1 = event.getDiscountOf(specialDate1, orderedMenu);
        int discount2 = event.getDiscountOf(specialDate1, orderedMenu2);

        assertThat(discount1).isEqualTo(25000);
        assertThat(discount2).isEqualTo(0);
    }

    private OrderedMenu createBigOrder() {
        EnumMap<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);
        orderDetails.put(Menu.T_BONE_STEAK, 1);
        orderDetails.put(Menu.BARBECUE_RIBS, 2);
        orderDetails.put(Menu.TAPAS, 1);
        orderDetails.put(Menu.CHRISTMAS_PASTA, 1);
        orderDetails.put(Menu.ICE_CREAM, 3);
        return new OrderedMenu(orderDetails);
    }

    private OrderedMenu createSimpleOrder() {
        EnumMap<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);
        orderDetails.put(Menu.TAPAS, 1);
        orderDetails.put(Menu.ZERO_COLA, 1);
        return new OrderedMenu(orderDetails);
    }
}
