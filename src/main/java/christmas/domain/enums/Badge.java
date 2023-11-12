package christmas.domain.enums;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    public final String name;
    private final int minimumMoney;

    Badge (String name, int minimumMoney) {
        this.name = name;
        this.minimumMoney = minimumMoney;
    }

    public static Badge of(int totalDiscountMoney) {
        if (SANTA.minimumMoney <= totalDiscountMoney) return SANTA;
        if (TREE.minimumMoney <= totalDiscountMoney) return TREE;
        if (STAR.minimumMoney <= totalDiscountMoney) return STAR;
        return NONE;
    }
}
