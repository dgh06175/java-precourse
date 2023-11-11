package christmas.domain;

import christmas.exception.DateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DateTest {
    @DisplayName("날짜 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 31})
    void validDateTest(int validDateValue) {
        Date date = new Date(validDateValue);
        assertThat(date.value).isEqualTo(validDateValue);
    }

    @DisplayName("잘못된 날짜 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 32, 40})
    void invalidDateTest(int invalidDateValue) {
        assertThatThrownBy(() -> new Date(invalidDateValue))
                .isInstanceOf(DateException.class);
    }
}
