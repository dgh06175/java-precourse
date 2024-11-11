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
}
