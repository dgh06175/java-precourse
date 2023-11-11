package christmas.domain.enums;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Date;
import christmas.domain.OrderedMenu;
import java.util.EnumMap;

public class EventTest {

    @DisplayName("이벤트 최소 금액 부족 검사")
    @Test
    void checkEventValidity() {
        Date date = new Date(25);
        OrderedMenu orderedMenu = createSampleOrder2();

        assertThat(Event.CHRISTMAS_D_DAY.isEventValid(date, orderedMenu)).isFalse();
    }

    @DisplayName("크리스마스 D-데이 이벤트 유효성 검사")
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 25})
    void checkChristmasDDayEventValidity(int day) {
        Date date = new Date(day);
        OrderedMenu orderedMenu = createSampleOrder();
        assertThat(Event.CHRISTMAS_D_DAY.isEventValid(date, orderedMenu)).isTrue();
    }

    @DisplayName("크리스마스 D-데이 이벤트 유효성 실패 검사")
    @Test
    void checkChristmasDDayEventInValidity() {
        Date date = new Date(26);

        OrderedMenu orderedMenu = createSampleOrder();

        assertThat(Event.CHRISTMAS_D_DAY.isEventValid(date, orderedMenu)).isFalse();
    }

    @DisplayName("크리스마스 D-데이 할인 계산")
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 25})
    void calculateChristmasDDayDiscount(int day) {
        Date date = new Date(day);

        OrderedMenu orderedMenu = createSampleOrder();
        int discount = Event.CHRISTMAS_D_DAY.calculateDiscount(date, orderedMenu);

        assertThat(discount).isGreaterThanOrEqualTo(1000);
    }

    @DisplayName("주말 할인 이벤트 유효성 검사")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 29, 30})
    void checkWeekendEventValidity(int day) {
        Date weekendDate = new Date(day);

        OrderedMenu orderedMenu = createSampleOrder();

        assertThat(Event.WEEKEND.isEventValid(weekendDate, orderedMenu)).isTrue();
    }

    @DisplayName("주말 할인 이벤트 유효성 실패 검사")
    @ParameterizedTest
    @ValueSource(ints = {5, 6, 17, 21})
    void checkWeekendEventInValidity(int day) {
        Date weekendDate = new Date(day);

        OrderedMenu orderedMenu = createSampleOrder();

        assertThat(Event.WEEKEND.isEventValid(weekendDate, orderedMenu)).isFalse();
    }

    @DisplayName("주중 할인 이벤트 유효성 검사")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 28})
    void checkWeekDayEventValidity(int day) {
        Date weekendDate = new Date(day);

        OrderedMenu orderedMenu = createSampleOrder();

        assertThat(Event.WEEKDAY.isEventValid(weekendDate, orderedMenu)).isTrue();
        assertThat(Event.WEEKDAY.calculateDiscount(weekendDate, orderedMenu))
                .isEqualTo(2023 * 3);
    }

    @DisplayName("주중 할인 이벤트 유효성 실패 검사")
    @ParameterizedTest
    @ValueSource(ints = {1, 16, 30, 15})
    void checkWeekDayEventInValidity(int day) {
        Date weekendDate = new Date(day);

        OrderedMenu orderedMenu = createSampleOrder();
        OrderedMenu orderedMenu2 = createSampleOrder2();

        assertThat(Event.WEEKDAY.isEventValid(weekendDate, orderedMenu)).isFalse();
        assertThat(Event.WEEKDAY.isEventValid(new Date(1), orderedMenu2)).isFalse();
    }

    @DisplayName("스페셜 할인 이벤트 유효성 검사")
    @Test
    void checkSpecialEventInValidity() {
        Date specialDate1 = new Date(3);
        Date specialDate2 = new Date(25);
        Date invalidSpecialDate = new Date(26);

        OrderedMenu orderedMenu = createSampleOrder();

        assertThat(Event.SPECIAL.isEventValid(specialDate1, orderedMenu)).isTrue();
        assertThat(Event.SPECIAL.calculateDiscount(specialDate2, orderedMenu)).isEqualTo(1000);
        assertThat(Event.SPECIAL.isEventValid(invalidSpecialDate, orderedMenu)).isFalse();
    }

    @DisplayName("샴페인 증정 이벤트 검사")
    @Test
    void checkGiveAwayEventInValidity() {
        Date specialDate1 = new Date(1);

        OrderedMenu orderedMenu = createSampleOrder();
        OrderedMenu orderedMenu2 = createSampleOrder2();

        int discount1 = Event.GIVEAWAY.calculateDiscount(specialDate1, orderedMenu);
        int discount2 = Event.GIVEAWAY.calculateDiscount(specialDate1, orderedMenu2);

        assertThat(Event.GIVEAWAY.isEventValid(specialDate1, orderedMenu)).isTrue();
        assertThat(discount1).isEqualTo(25000);

        assertThat(Event.GIVEAWAY.isEventValid(specialDate1, orderedMenu2)).isFalse();
        assertThat(discount2).isEqualTo(0);
    }

    private OrderedMenu createSampleOrder() {
        EnumMap<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);
        orderDetails.put(Menu.T_BONE_STEAK, 1);
        orderDetails.put(Menu.BARBECUE_RIBS, 2);
        orderDetails.put(Menu.TAPAS, 1);
        orderDetails.put(Menu.CHRISTMAS_PASTA, 1);
        orderDetails.put(Menu.ICE_CREAM, 3);
        return new OrderedMenu(orderDetails);
    }

    private OrderedMenu createSampleOrder2() {
        EnumMap<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);
        orderDetails.put(Menu.TAPAS, 1);
        orderDetails.put(Menu.ZERO_COLA, 1);
        return new OrderedMenu(orderDetails);
    }
}
