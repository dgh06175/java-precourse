package menu.model;

import java.util.stream.Stream;
import menu.exception.MenuRecommendationException;

public enum Category {
    JAPANESE(1, "일식"),
    KOREAN(2, "한식"),
    CHINESE(3, "중식"),
    ASIAN(4, "아시안"),
    WESTERN(5, "양식");

    private final int index;
    private final String krName;

    Category(int index, String krName) {
        this.index = index;
        this.krName = krName;
    }

    public static Category get(int index) {
        return Stream.of(Category.values())
                .filter(category -> category.index == index)
                .findFirst()
                .orElseThrow(() -> new MenuRecommendationException("잘못된 카테고리 인덱스 입니다."));
    }

    public String getKrName() {
        return krName;
    }
}
