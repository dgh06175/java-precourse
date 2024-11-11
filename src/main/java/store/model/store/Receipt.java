package store.model.store;

import java.util.HashMap;
import java.util.Map;
import store.model.storage.NormalProduct;

public class Receipt {
    private Map<String, NormalProduct> products = new HashMap<>();
    private Map<String, NormalProduct> promotions = new HashMap<>();

    public void addProduct(NormalProduct product) {
        if (products.containsKey(product.name())) {
            products.computeIfPresent(
                    product.name(),
                    (k, existProduct) -> new NormalProduct(
                            product.name(),
                            product.cost(),
                            existProduct.quantity() + product.quantity()
                    )
            );
            return;
        }
        products.put(product.name(), product);
    }

    public void addPromotion(NormalProduct promotion) {
        if (promotions.containsKey(promotion.name())) {
            promotions.computeIfPresent(
                    promotion.name(),
                    (k, existPromotion) -> new NormalProduct(
                            promotion.name(),
                            promotion.cost(),
                            existPromotion.quantity() + promotion.quantity()
                    )
            );
            return;
        }
        promotions.put(promotion.name(), promotion);
    }

    public void init() {
        products.clear();
        promotions.clear();
    }

    public void print() {
        System.out.println();
        System.out.println("==============W 편의점================");
        System.out.printf("%-19s%-10s%s\n", "상품명", "수량", "가격");
        for (var entry : products.entrySet()) {
            NormalProduct product = entry.getValue();
            System.out.printf("%-19s%-10s%,d\n", product.name(), product.quantity(),
                    product.cost() * product.quantity());
        }
        System.out.println("=============증      정===============");
        for (var entry : promotions.entrySet()) {
            NormalProduct promotion = entry.getValue();
            System.out.printf("%-19s%d\n", promotion.name(), promotion.quantity());
        }
        System.out.println("====================================");
        System.out.printf("%-19s%-10d%,d\n", "총구매액", getTotalQuantity(), getTotalCost());
        System.out.printf("%-29s%,-10d\n", "행사할인", -1 * getPromotionCost());
        System.out.printf("%-29s%,-10d\n", "멤버십할인", -1 * 0);
        System.out.printf("%-29s %,d\n", "내실돈", getPayCost());
        System.out.println();
    }

    private int getTotalQuantity() {
        return products.values().stream()
                .mapToInt(NormalProduct::quantity)
                .sum();
    }

    private int getTotalCost() {
        return products.values().stream()
                .mapToInt(product -> product.quantity() * product.cost())
                .sum();
    }

    private int getPromotionCost() {
        return promotions.values().stream()
                .mapToInt(promotion -> promotion.quantity() * promotion.cost())
                .sum();
    }

    private int getPayCost() {
        return getTotalCost() - getPromotionCost();
    }
}
