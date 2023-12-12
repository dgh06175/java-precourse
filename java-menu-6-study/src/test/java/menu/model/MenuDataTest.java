package menu.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.List;
import menu.exception.InvalidInputException;

class MenuDataTest {

    @Test
    void 카테고리별_메뉴_목록_정상_가져오기() {
        assertThat(MenuData.getMenusByCategory(Category.JAPANESE))
                .containsExactly("규동", "우동", "미소시루", "스시", "가츠동", "오니기리", "하이라이스", "라멘", "오코노미야끼");
        assertThat(MenuData.getMenusByCategory(Category.KOREAN))
                .containsExactly("김밥", "김치찌개", "쌈밥", "된장찌개", "비빔밥", "칼국수", "불고기", "떡볶이", "제육볶음");
        assertThat(MenuData.getMenusByCategory(Category.CHINESE))
                .containsExactly("깐풍기", "볶음면", "동파육", "짜장면", "짬뽕", "마파두부", "탕수육", "토마토 달걀볶음", "고추잡채");
        assertThat(MenuData.getMenusByCategory(Category.ASIAN))
                .containsExactly("팟타이", "카오 팟", "나시고렝", "파인애플 볶음밥", "쌀국수", "똠얌꿍", "반미", "월남쌈", "분짜");
        assertThat(MenuData.getMenusByCategory(Category.WESTERN))
                .containsExactly("라자냐", "그라탱", "뇨끼", "끼슈", "프렌치 토스트", "바게트", "스파게티", "피자", "파니니");
    }

    @Test
    void 유효한_메뉴_검증() {
        assertThat(MenuData.getMenusByCategory(Category.JAPANESE)).contains("규동");
        MenuData.isValidMenu("규동");
    }

    @Test
    void 유효하지_않은_메뉴_검증_실패() {
        Throwable thrown = catchThrowable(() -> MenuData.isValidMenu("가상의 메뉴"));
        assertThat(thrown).isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("잘못된 메뉴 입력입니다.");
    }
}
