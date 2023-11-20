package christmas.domain.enums;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    public final String displayName;
    private final int minDiscount;

    Badge (String displayName, int minDiscount) {
        this.displayName = displayName;
        this.minDiscount = minDiscount;
    }

    /**
     * 총 할인 금액에 알맞은 배지의 이름을 반환한다.
     * @param totalDiscountMoney 총 할인 금액
     * @return 배지
     */
    public static Badge of(int totalDiscountMoney) {
        if (SANTA.minDiscount <= totalDiscountMoney) return SANTA;
        if (TREE.minDiscount <= totalDiscountMoney) return TREE;
        if (STAR.minDiscount <= totalDiscountMoney) return STAR;
        return NONE;
    }
}
