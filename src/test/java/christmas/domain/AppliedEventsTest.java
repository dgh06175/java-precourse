package christmas.domain;

import christmas.domain.enums.Event;
import christmas.domain.enums.Menu;
import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AppliedEventsTest {
    @DisplayName("적용된 이벤트의 총 할인 금액 계산")
    @Test
    void calculateTotalDiscount() {
        Order order = createSampleOrder();
        Date date = new Date(15); // 12월 15일을 예시로 사용

        AppliedEvents appliedEvents = AppliedEvents.of(order, date);
        int totalDiscount = appliedEvents.getTotalDiscount();
        System.out.println(totalDiscount);

        assertThat(totalDiscount).isEqualTo(2400 + 2023 * 3 + 25000);
    }

    @DisplayName("특정 이벤트의 할인 금액 확인")
    @Test
    void getSpecificEventDiscount() {
        Order order = createSampleOrder();
        Date date = new Date(15);

        AppliedEvents appliedEvents = AppliedEvents.of(order, date);
        int discount = appliedEvents.getDiscountOf(Event.CHRISTMAS_D_DAY);
        assertThat(discount).isEqualTo(2400);
    }

    @DisplayName("Giveaway 이벤트 포함 여부 확인")
    @Test
    void checkGiveawayEvent() {
        Order order1 = createSampleOrder();
        Order order2 = createSampleOrder2();
        Date date = new Date(15);

        AppliedEvents appliedEvents = AppliedEvents.of(order1, date);
        AppliedEvents appliedEvents2 = AppliedEvents.of(order2, date);
        boolean containsGiveaway1 = appliedEvents.containsGiveawayEvent();
        boolean containsGiveaway2 = appliedEvents2.containsGiveawayEvent();

        assertThat(containsGiveaway1).isTrue();
        assertThat(containsGiveaway2).isFalse();
    }

    private Order createSampleOrder() {
        EnumMap<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);
        orderDetails.put(Menu.티본스테이크, 2);
        orderDetails.put(Menu.바비큐립, 1);
        return new Order(orderDetails);
    }

    private Order createSampleOrder2() {
        EnumMap<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);
        orderDetails.put(Menu.양송이수프, 1);
        orderDetails.put(Menu.제로콜라, 2);
        return new Order(orderDetails);
    }
}
