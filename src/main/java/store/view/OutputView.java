package store.view;

import java.util.Map;
import store.model.storage.NormalStock;
import store.model.storage.PromotionStock;

public class OutputView {
    public void printWelcomeMessage() {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.\n");
    }

    public void printProducts(Map<String, NormalStock> normalStocks, Map<String, PromotionStock> promotionStocks) {
        for (var entry : normalStocks.entrySet()) {
            String name = entry.getKey();

            NormalStock normalStock = normalStocks.get(name);
            PromotionStock promotionStock = promotionStocks.get(name);

            if (promotionStock != null) {
                printPromotionStock(promotionStock);
            }
            if (normalStock != null) {
                printNormalStock(normalStock);
            }
        }
    }

    private void printNormalStock(NormalStock normalStock) {
        if (normalStock.quantity() > 0) {
            System.out.printf("- %s %,d원 %d개\n", normalStock.name(), normalStock.cost(), normalStock.quantity());
            return;
        }
        System.out.printf("- %s %,d원 재고 없음\n", normalStock.name(), normalStock.cost());
    }

    private void printPromotionStock(PromotionStock promotionStock) {
        if (promotionStock.quantity() > 0) {
            System.out.printf("- %s %,d원 %d개 %s\n",
                    promotionStock.name(),
                    promotionStock.cost(),
                    promotionStock.quantity(),
                    promotionStock.promotion());
            return;
        }
        System.out.printf("- %s %,d원 재고 없음\n", promotionStock.name(), promotionStock.cost());
    }
}
