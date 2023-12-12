package menu.service;

import static menu.constant.Constant.DAYS_SIZE;

import java.util.ArrayList;
import java.util.List;
import menu.model.Category;
import menu.util.Generatable;

public class CategorySelector {
    private static final int MAX_CATEGORY_DUPLICATE_SIZE = 2;

    private final Generatable generator;

    public CategorySelector(Generatable generator) {
        this.generator = generator;
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        while (categories.size() != DAYS_SIZE) {
            Category category = Category.get(generator.getRandomNumber());
            if (isCategoryNotExceedingLimit(categories, category)) {
                categories.add(category);
            }
        }
        return categories;
    }

    private boolean isCategoryNotExceedingLimit(List<Category> categories, Category category) {
        long count = categories.stream()
                .filter(category::equals)
                .count();
        return count < MAX_CATEGORY_DUPLICATE_SIZE;
    }
}
