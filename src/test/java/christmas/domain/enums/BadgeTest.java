package christmas.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BadgeTest {
    @Test
    @DisplayName("할인 금액이 적을 때 뱃지 없음 검사")
    public void shouldReturnNoneForNoDiscount() {
        assertThat(Badge.of(4999)).isEqualTo(Badge.NONE);
    }

    @Test
    @DisplayName("할인 금액이 5000원 이상 10000원 미만일 때 뱃지 별 검사")
    public void shouldReturnStarForDiscountBetween5000And10000() {
        assertThat(Badge.of(5000)).isEqualTo(Badge.STAR);
        assertThat(Badge.of(9999)).isEqualTo(Badge.STAR);
    }

    @Test
    @DisplayName("할인 금액이 10000원 이상 20000원 미만일 때 뱃지 트리 검사")
    public void shouldReturnTreeForDiscountBetween10000And20000() {
        assertThat(Badge.of(10000)).isEqualTo(Badge.TREE);
        assertThat(Badge.of(19999)).isEqualTo(Badge.TREE);
    }

    @Test
    @DisplayName("할인 금액이 20000원 이상일 때 뱃지 산타 검사")
    public void shouldReturnSantaForDiscountOf20000OrMore() {
        assertThat(Badge.of(20000)).isEqualTo(Badge.SANTA);
        assertThat(Badge.of(30000)).isEqualTo(Badge.SANTA);
    }
}
