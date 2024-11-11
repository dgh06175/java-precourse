package store.model.storage;

import static store.exception.StoreError.STOCK_PROMOTION_EMPTY;
import static store.model.storage.NormalProduct.validateStock;

import store.exception.StoreException;

public record PromotionProduct(String name, int cost, int quantity, String promotion) {

    public PromotionProduct {
        validatePromotionStock(name, cost, quantity, promotion);
    }

    private static void validatePromotionStock(String name, int cost, int quantity, String promotion) {
        validateStock(name, cost, quantity);
        if (promotion == null || promotion.isBlank()) {
            throw new StoreException(STOCK_PROMOTION_EMPTY);
        }
    }

    public PromotionProduct pickUpStock(int quantity) {
        return new PromotionProduct(name, cost, this.quantity - quantity, promotion);
    }
}