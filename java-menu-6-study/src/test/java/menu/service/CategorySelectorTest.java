package menu.service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import menu.util.Generatable;
import menu.util.RandomGenerator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.BeforeEach;
import menu.model.Category;

import java.util.List;
import org.junit.jupiter.api.Test;

import static menu.constant.Constant.DAYS_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

class CategorySelectorTest {
    private static final int MAX_CATEGORY_DUPLICATE_SIZE = 2;

    private CategorySelector categorySelector;

    @BeforeEach
    void setUp() {
        // FakeGenerator는 무작위로 1부터 5까지의 값을 순서대로 반환하도록 설정
        Generatable generator = new RandomGenerator();
        categorySelector = new CategorySelector(generator);
    }

    @Test
    void 카테고리_목록_생성_크기_검증() {
        List<Category> categories = categorySelector.getCategories();
        assertThat(categories).hasSize(DAYS_SIZE);
    }

    @RepeatedTest(100)
    void 여러_번_실행하여_카테고리_중복_최대_허용_범위_내에서_검증() {
        List<Category> categories = categorySelector.getCategories();
        assertThat(categories).hasSize(DAYS_SIZE);

        // 카테고리의 출현 빈도를 계산
        Map<Category, Long> categoryFrequency = categories.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // 각 카테고리가 최대 중복 허용 횟수를 초과하지 않았는지 검증
        categoryFrequency.forEach((category, frequency) ->
                assertThat(frequency).isLessThanOrEqualTo(MAX_CATEGORY_DUPLICATE_SIZE));
    }
}
