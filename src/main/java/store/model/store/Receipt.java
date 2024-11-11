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
        System.out.println("==============W 편의점================");
        System.out.printf("%-19s%-10s%s\n", "상품명", "수량", "가격");
        for (var entry : products.entrySet()) {
            NormalProduct product = entry.getValue();
            System.out.printf("%-19s%-10s%,d\n", product.name(), product.quantity(),
                    product.cost() * product.quantity());
        }
        System.out.println("=============증\t정===============");
        for (var entry : promotions.entrySet()) {
            NormalProduct promotion = entry.getValue();
            System.out.printf("%s\t\t%d\n", promotion.name(), promotion.quantity());
        }
        System.out.println("====================================");
        System.out.printf("총구매액\t\t%d\t%,d\n", getTotalQuantity(), getTotalCost());
        System.out.printf("행사할인\t\t\t%,d\n", -1 * getPromotionCost());
//        System.out.printf("멤버십할인\t\t\t$,d\n", -1 * getMembershipCost());
        System.out.printf("내실돈\t\t\t %,d\n", getPayCost());
    }

    // 총 구매 수량 구하는 메소드
    private int getTotalQuantity() {
        return products.values().stream()
                .mapToInt(NormalProduct::quantity)
                .sum();
    }

    // 총 구매 금액 구하는 메소드
    private int getTotalCost() {
        return products.values().stream()
                .mapToInt(product -> product.quantity() * product.cost())
                .sum();
    }

    // 행사 할인 금액 구하는 메소드
    private int getPromotionCost() {
        return promotions.values().stream()
                .mapToInt(promotion -> promotion.quantity() * promotion.cost())
                .sum();
    }

    // 실제 결제 금액 구하는 메소드 (할인 후)
    private int getPayCost() {
        return getTotalCost() - getPromotionCost();
    }
}
