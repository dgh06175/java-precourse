package store.model.promotion;

import static store.exception.StoreError.PROMOTION_BUY_NEGATIVE;
import static store.exception.StoreError.PROMOTION_END_DATE_NULL;
import static store.exception.StoreError.PROMOTION_GET_NEGATIVE;
import static store.exception.StoreError.PROMOTION_NAME_EMPTY;
import static store.exception.StoreError.PROMOTION_START_DATE_AFTER_END_DATE;
import static store.exception.StoreError.PROMOTION_START_DATE_NULL;

import java.time.LocalDate;
import store.exception.StoreException;

public record PromotionItem(String name, int buy, int get, LocalDate start_date, LocalDate end_date) {
    public PromotionItem {
        // TODO: 더 자세한 예외 처리
        if (name == null || name.isBlank()) {
            throw new StoreException(PROMOTION_NAME_EMPTY);
        }
        if (buy < 0) {
            throw new StoreException(PROMOTION_BUY_NEGATIVE);
        }
        if (get < 0) {
            throw new StoreException(PROMOTION_GET_NEGATIVE);
        }
        if (start_date == null) {
            throw new StoreException(PROMOTION_START_DATE_NULL);
        }
        if (end_date == null) {
            throw new StoreException(PROMOTION_END_DATE_NULL);
        }
        if (start_date.isAfter(end_date)) {
            throw new StoreException(PROMOTION_START_DATE_AFTER_END_DATE);
        }
    }
}
