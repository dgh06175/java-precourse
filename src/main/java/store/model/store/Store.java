package store.model.store;

import static store.exception.StoreError.STORAGE_ITEM_NOT_EXIST;
import static store.exception.StoreError.STORAGE_ITEM_STOCK_NOT_ENOUGH;

import java.util.Map;
import store.exception.StoreException;
import store.model.promotion.Promotion;
import store.model.promotion.PromotionStatus;
import store.model.storage.NormalProduct;
import store.model.storage.PromotionProduct;
import store.model.storage.Storage;

public class Store {
    private final Storage storage;
    private final Promotion promotion;
    private final Receipt receipt;

    public Store(Storage storage, Promotion promotion) {
        this.storage = storage;
        this.promotion = promotion;
        this.receipt = new Receipt();
    }

    public void validateProduct(String name, int quantity) {
        if (!storage.hasProduct(name)) {
            throw new StoreException(STORAGE_ITEM_NOT_EXIST);
        }
        if (!storage.hasStock(name, quantity)) {
            throw new StoreException(STORAGE_ITEM_STOCK_NOT_ENOUGH);
        }
    }

    public PromotionStatus checkPromotionStatus(String name, int quantity) {
        // 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량만큼 가져오지 않았을 경우
        if (!isPromotionEligible(name, quantity)) {
            return PromotionStatus.BONUS_OFFER_AVAILABLE;
        }
        // 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우
        if (!isPromotionStockSufficient(name, quantity)) {
            return PromotionStatus.PROMOTION_STOCK_NOT_ENOUGH;
        }
        return PromotionStatus.OK;
    }

    public void buy(String name, int quantity) {
        // TODO: 구매 처리
    }

    public Map<String, NormalProduct> getNormalStocks() {
        return storage.getNormalStock();
    }

    public Map<String, PromotionProduct> getPromotionStocks() {
        return storage.getPromotionStocks();
    }

    private boolean isPromotionEligible(String name, int quantity) {
        // TODO: 구현
        return true;
    }

    private boolean isPromotionStockSufficient(String name, int quantity) {
        // TODO: 구현
        return true;
    }
}
