package christmas.domain.enums;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    public final String name;
    private final int minDiscount;

    Badge (String name, int minDiscount) {
        this.name = name;
        this.minDiscount = minDiscount;
    }

    public static Badge of(int totalDiscountMoney) {
        if (SANTA.minDiscount <= totalDiscountMoney) return SANTA;
        if (TREE.minDiscount <= totalDiscountMoney) return TREE;
        if (STAR.minDiscount <= totalDiscountMoney) return STAR;
        return NONE;
    }
}
