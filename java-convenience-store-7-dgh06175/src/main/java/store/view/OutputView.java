package store.view;

import java.util.Map;
import store.model.storage.NormalProduct;
import store.model.storage.PromotionProduct;

public class OutputView {
    public void printWelcomeMessage() {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.\n");
    }

    public void printProducts(Map<String, NormalProduct> normalStocks, Map<String, PromotionProduct> promotionStocks) {
        for (var entry : normalStocks.entrySet()) {
            String name = entry.getKey();
            NormalProduct normalProduct = normalStocks.get(name);
            PromotionProduct promotionProduct = promotionStocks.get(name);
            if (promotionProduct != null) {
                printPromotionStock(promotionProduct);
            }
            printNormalStock(normalProduct);
        }
    }

    private void printNormalStock(NormalProduct normalProduct) {
        if (normalProduct.quantity() > 0) {
            System.out.printf("- %s %,d원 %d개\n", normalProduct.name(), normalProduct.cost(), normalProduct.quantity());
            return;
        }
        System.out.printf("- %s %,d원 재고 없음\n", normalProduct.name(), normalProduct.cost());
    }

    private void printPromotionStock(PromotionProduct promotionProduct) {
        if (promotionProduct.quantity() > 0) {
            System.out.printf("- %s %,d원 %d개 %s\n",
                    promotionProduct.name(),
                    promotionProduct.cost(),
                    promotionProduct.quantity(),
                    promotionProduct.promotion());
            return;
        }
        System.out.printf("- %s %,d원 재고 없음\n", promotionProduct.name(), promotionProduct.cost());
    }

    public void printReceipt(Map<String, NormalProduct> products, Map<String, NormalProduct> promotions) {
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
        System.out.printf("%-19s%-10d%,d\n", "총구매액", getTotalQuantity(products), getTotalCost(products));
        System.out.printf("%-29s%,-10d\n", "행사할인", -1 * getPromotionCost(promotions));
        System.out.printf("%-29s%,-10d\n", "멤버십할인", -1 * 0);
        System.out.printf("%-29s %,d\n", "내실돈", getPayCost(products, promotions));
        System.out.println();
    }

    private int getTotalQuantity(Map<String, NormalProduct> products) {
        return products.values().stream()
                .mapToInt(NormalProduct::quantity)
                .sum();
    }

    private int getTotalCost(Map<String, NormalProduct> products) {
        return products.values().stream()
                .mapToInt(product -> product.quantity() * product.cost())
                .sum();
    }

    private int getPromotionCost(Map<String, NormalProduct> promotions) {
        return promotions.values().stream()
                .mapToInt(promotion -> promotion.quantity() * promotion.cost())
                .sum();
    }

    private int getPayCost(Map<String, NormalProduct> products, Map<String, NormalProduct> promotions) {
        return getTotalCost(products) - getPromotionCost(promotions);
    }
}
