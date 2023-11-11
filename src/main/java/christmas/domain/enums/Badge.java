package christmas.domain.enums;

import net.bytebuddy.dynamic.scaffold.TypeInitializer.None;

public enum Badge {
    없음(0),
    별(5_000),
    트리(10_000),
    산타(20_000);

    public final int minimumMoney;

    Badge (int minimumMoney) {
        this.minimumMoney = minimumMoney;
    }

    public static Badge of(int totalDiscountMoney) {
        if (산타.minimumMoney <= totalDiscountMoney) return 산타;
        if (트리.minimumMoney <= totalDiscountMoney) return 트리;
        if (별.minimumMoney <= totalDiscountMoney) return 별;
        return 없음;
    }
}
