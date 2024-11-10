package store.model.storage;

import static store.exception.StoreError.STOCK_NAME_EMPTY;
import static store.exception.StoreError.STOCK_PRICE_INVALID;
import static store.exception.StoreError.STOCK_PROMOTION_EMPTY;
import static store.exception.StoreError.STOCK_QUANTITY_INVALID;

import store.exception.StoreException;

public record Stock(String name, int cost, int quantity, String promotion) {
    public Stock {
        validateStock(name, cost, quantity, promotion);
    }

    private void validateStock(String name, int cost, int quantity, String promotion) {
        // TODO: 더 자세한 예외 처리
        if (name == null || name.isBlank()) {
            throw new StoreException(STOCK_NAME_EMPTY);
        }
        if (cost < 0) {
            throw new StoreException(STOCK_PRICE_INVALID);
        }
        if (quantity < 0) {
            throw new StoreException(STOCK_QUANTITY_INVALID);
        }
        if (promotion == null || promotion.isBlank()) {
            throw new StoreException(STOCK_PROMOTION_EMPTY);
        }
    }
}
