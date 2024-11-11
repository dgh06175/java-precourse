package store.view;

import java.util.Map;
import store.model.storage.NormalStock;
import store.model.storage.PromotionStock;

public class OutputView {
    public void printWelcomeMessage() {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.\n");
    }

    //- 콜라 1,000원 10개 탄산2+1
    public void printProducts(Map<String, NormalStock> normalStocks, Map<String, PromotionStock> promotionStocks) {
        for (var normalStock : normalStocks.entrySet()) {
            String name = normalStock.getKey();
            int cost = normalStock.getValue().cost();

            int promotionQuantity = getPromotionQuantity(promotionStocks, name);

            int normalQuantity = normalStocks.get(name).quantity();

            // 프로모션은 재고없으면 출력안함
            if (promotionQuantity > 0) {
                System.out.printf("- %s %,d원 %d개 %s\n", name, cost, promotionQuantity,
                        promotionStocks.get(name).promotion());
            }
            // 노말은 재고없어도 출력함
            if (normalQuantity > 0) {
                System.out.printf("- %s %,d원 %d개\n", name, cost, normalQuantity);
            } else {
                System.out.printf("- %s %,d원 재고 없음\n", name, cost);
            }
        }
    }

    private int getPromotionQuantity(Map<String, PromotionStock> promotionStocks, String name) {
        PromotionStock promotionStock = promotionStocks.get(name);
        if (promotionStock == null) {
            return 0;
        }
        return promotionStocks.get(name).quantity();
    }
}
