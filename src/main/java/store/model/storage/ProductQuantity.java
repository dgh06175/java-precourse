package store.model.storage;

import static store.exception.StoreError.STOCK_NAME_EMPTY;
import static store.exception.StoreError.STOCK_QUANTITY_INVALID;

import store.exception.StoreException;

public record ProductQuantity(String name, int quantity) {
    public ProductQuantity {
        validateProductQuantity(name, quantity);
    }

    private void validateProductQuantity(String name, int quantity) {
        if (name == null || name.isBlank()) {
            throw new StoreException(STOCK_NAME_EMPTY);
        }
        if (quantity < 0) {
            throw new StoreException(STOCK_QUANTITY_INVALID);
        }
    }
}
