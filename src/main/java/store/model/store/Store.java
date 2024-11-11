package store.model.store;

import java.util.Map;
import store.model.promotion.Promotion;
import store.model.storage.NormalStock;
import store.model.storage.PromotionStock;
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

    public Map<String, NormalStock> getNormalStocks() {
        return storage.getNormalStock();
    }

    public Map<String, PromotionStock> getPromotionStocks() {
        return storage.getPromotionStocks();
    }
}
