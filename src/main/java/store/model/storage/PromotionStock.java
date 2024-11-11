package store.model.storage;

import static store.exception.StoreError.STOCK_PROMOTION_EMPTY;
import static store.model.storage.NormalStock.validateStock;

import store.exception.StoreException;

public record PromotionStock(String name, int cost, int quantity, String promotion) {

    public PromotionStock {
        validatePromotionStock(name, cost, quantity, promotion);
    }

    private static void validatePromotionStock(String name, int cost, int quantity, String promotion) {
        validateStock(name, cost, quantity);
        if (promotion == null || promotion.isBlank()) {
            throw new StoreException(STOCK_PROMOTION_EMPTY);
        }
    }
}