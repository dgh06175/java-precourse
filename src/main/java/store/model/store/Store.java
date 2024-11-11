package store.model.store;

import static store.exception.StoreError.STORAGE_ITEM_NOT_EXIST;
import static store.exception.StoreError.STORAGE_ITEM_STOCK_NOT_ENOUGH;

import java.util.Map;
import store.exception.StoreException;
import store.model.promotion.Promotion;
import store.model.promotion.PromotionItem;
import store.model.promotion.PromotionStatus;
import store.model.promotion.PromotionStatusQuantity;
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
        if (storage.hasNotProduct(name)) {
            throw new StoreException(STORAGE_ITEM_NOT_EXIST);
        }
        if (storage.hasNotEnoughStock(name, quantity)) {
            throw new StoreException(STORAGE_ITEM_STOCK_NOT_ENOUGH);
        }
    }

    public PromotionStatusQuantity checkPromotionStatus(String name, int requestQuantity) {
        if (isPromotionNotAvailable(name)) {
            return new PromotionStatusQuantity(PromotionStatus.OK, 0);
        }
        int promotionQuantity = storage.getPromotionStockOf(name).quantity();
        int buy = promotion.getPromotionItemOf(storage.getPromotionStockOf(name).promotion()).buy();
        int get = promotion.getPromotionItemOf(storage.getPromotionStockOf(name).promotion()).get();
        int normal_count = storage.getNormalStockOf(name).quantity();

        if (requestQuantity > promotionQuantity + normal_count) {
            throw new StoreException(STORAGE_ITEM_STOCK_NOT_ENOUGH);
        }

        if (promotionQuantity < requestQuantity) {
            return new PromotionStatusQuantity(
                    PromotionStatus.PROMOTION_STOCK_NOT_ENOUGH,
                    (requestQuantity - promotionQuantity) + (promotionQuantity % (buy + get))
            );
        }

        int remain = requestQuantity % (buy + get);
        if (remain < buy) {
            return new PromotionStatusQuantity(PromotionStatus.OK, 0);
        }

        if (remain == buy) {
            if (requestQuantity + get > promotionQuantity) {
                return new PromotionStatusQuantity(PromotionStatus.OK, 0);
            }
            return new PromotionStatusQuantity(PromotionStatus.BONUS_OFFER_AVAILABLE, get);
        }

        return new PromotionStatusQuantity(PromotionStatus.OK, 0);
    }

    public void buy(String name, int quantity) {
        // TODO: 구매 처리
    }

    private boolean isPromotionNotAvailable(String name) {
        PromotionProduct promotionStock;
        try {
            promotionStock = storage.getPromotionStockOf(name);
        } catch (StoreException e) {
            return true;
        }
        return !promotion.isAvailable(promotionStock.promotion());
    }

    private PromotionItem getPromotionItem(String name) {
        PromotionProduct promotionStock = storage.getPromotionStockOf(name);
        return promotion.getPromotionItemOf(promotionStock.promotion());
    }

    public Map<String, NormalProduct> getNormalStocks() {
        return storage.getNormalStocks();
    }

    public Map<String, PromotionProduct> getPromotionStocks() {
        return storage.getPromotionStocks();
    }
}
