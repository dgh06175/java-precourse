package menu.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import menu.exception.MenuRecommendationException;

class CategoryTest {
    @Test
    void 정상적인_인덱스로_카테고리_가져오기() {
        assertThat(Category.get(1)).isEqualTo(Category.JAPANESE);
        assertThat(Category.get(2)).isEqualTo(Category.KOREAN);
        assertThat(Category.get(3)).isEqualTo(Category.CHINESE);
        assertThat(Category.get(4)).isEqualTo(Category.ASIAN);
        assertThat(Category.get(5)).isEqualTo(Category.WESTERN);
    }

    @Test
    void 잘못된_인덱스로_카테고리_가져오기_실패() {
        Throwable thrown = catchThrowable(() -> Category.get(0));
        assertThat(thrown).isInstanceOf(MenuRecommendationException.class)
                .hasMessageContaining("잘못된 카테고리 인덱스 입니다.");
    }

    @Test
    void 카테고리_한글_이름_확인() {
        assertThat(Category.JAPANESE.getKrName()).isEqualTo("일식");
        assertThat(Category.KOREAN.getKrName()).isEqualTo("한식");
        assertThat(Category.CHINESE.getKrName()).isEqualTo("중식");
        assertThat(Category.ASIAN.getKrName()).isEqualTo("아시안");
        assertThat(Category.WESTERN.getKrName()).isEqualTo("양식");
    }
}
