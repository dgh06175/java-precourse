package store.model.storage;

import static store.exception.StoreError.STOCK_NAME_EMPTY;
import static store.exception.StoreError.STOCK_PRICE_INVALID;
import static store.exception.StoreError.STOCK_QUANTITY_INVALID;

import store.exception.StoreException;

public record NormalStock(String name, int cost, int quantity) {

    public NormalStock {
        validateStock(name, cost, quantity);
    }

    static void validateStock(String name, int cost, int quantity) {
        if (name == null || name.isBlank()) {
            throw new StoreException(STOCK_NAME_EMPTY);
        }
        if (cost < 0) {
            throw new StoreException(STOCK_PRICE_INVALID);
        }
        if (quantity < 0) {
            throw new StoreException(STOCK_QUANTITY_INVALID);
        }
    }
}