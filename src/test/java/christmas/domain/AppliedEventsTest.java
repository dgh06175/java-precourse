package christmas.domain;

import christmas.domain.enums.Menu;
import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AppliedEventsTest {
    @DisplayName("적용된 이벤트의 총 할인 금액 계산")
    @Test
    void calculateTotalDiscount() {
        OrderedMenu orderedMenu = createSampleOrder();
        Date date = new Date(15); // 12월 15일을 예시로 사용

        AppliedEvents appliedEvents = AppliedEvents.of(date, orderedMenu);
        int totalDiscount = appliedEvents.getTotalDiscount();
        System.out.println(totalDiscount);

        assertThat(totalDiscount).isEqualTo(2400 + 2023 * 3 + 25000);
    }

    @DisplayName("Giveaway 이벤트 포함 여부 확인")
    @Test
    void checkGiveawayEvent() {
        OrderedMenu orderedMenu1 = createSampleOrder();
        OrderedMenu orderedMenu2 = createSampleOrder2();
        Date date = new Date(15);

        AppliedEvents appliedEvents = AppliedEvents.of(date, orderedMenu1);
        AppliedEvents appliedEvents2 = AppliedEvents.of(date, orderedMenu2);
        boolean containsGiveaway1 = appliedEvents.containsGiveawayEvent();
        boolean containsGiveaway2 = appliedEvents2.containsGiveawayEvent();

        assertThat(containsGiveaway1).isTrue();
        assertThat(containsGiveaway2).isFalse();
    }

    private OrderedMenu createSampleOrder() {
        EnumMap<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);
        orderDetails.put(Menu.T_BONE_STEAK, 2);
        orderDetails.put(Menu.BARBECUE_RIBS, 1);
        return new OrderedMenu(orderDetails);
    }

    private OrderedMenu createSampleOrder2() {
        EnumMap<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);
        orderDetails.put(Menu.MUSHROOM_SOUP, 1);
        orderDetails.put(Menu.ZERO_COLA, 2);
        return new OrderedMenu(orderDetails);
    }
}
