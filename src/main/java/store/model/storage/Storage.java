package store.model.storage;

import static store.exception.StoreError.STOCK_NOT_FOUND;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import store.exception.StoreException;

public class Storage {
    private final Map<String, NormalProduct> normalStock = new LinkedHashMap<>();
    private final Map<String, PromotionProduct> promotionStocks = new LinkedHashMap<>();

    public Storage(List<String> productLines) {
        for (String productLine : productLines.subList(1, productLines.size())) {
            List<String> splitProductLine = List.of(productLine.split(","));
            String name = splitProductLine.getFirst();
            int cost = Integer.parseInt(splitProductLine.get(1));
            int quantity = Integer.parseInt(splitProductLine.get(2));
            String promotion = splitProductLine.getLast();

            if (promotion.equalsIgnoreCase("null")) {
                normalStock.put(name, new NormalProduct(name, cost, quantity));
                continue;
            }
            promotionStocks.put(name, new PromotionProduct(name, cost, quantity, promotion));
            if (normalStock.get(name) == null) {
                normalStock.put(name, new NormalProduct(name, cost, 0));
            }
        }
    }

    public NormalProduct getNormalStockOf(String name) {
        if (!normalStock.containsKey(name)) {
            throw new StoreException(STOCK_NOT_FOUND);
        }
        return normalStock.get(name);
    }

    public PromotionProduct getPromotionStockOf(String name) {
        if (!promotionStocks.containsKey(name)) {
            throw new StoreException(STOCK_NOT_FOUND);
        }
        return promotionStocks.get(name);
    }

    public Map<String, NormalProduct> getNormalStocks() {
        return Collections.unmodifiableMap(normalStock);
    }

    public Map<String, PromotionProduct> getPromotionStocks() {
        return Collections.unmodifiableMap(promotionStocks);
    }

    public boolean hasNotProduct(String name) {
        return !normalStock.containsKey(name) && !promotionStocks.containsKey(name);
    }

    public boolean hasNotEnoughStock(String name, int quantity) {
        int normalQuantity = 0;
        int promotionQuantity = 0;
        if (normalStock.containsKey(name)) {
            normalQuantity = normalStock.get(name).quantity();
        }
        if (promotionStocks.containsKey(name)) {
            promotionQuantity = promotionStocks.get(name).quantity();
        }
        int stockCount = normalQuantity + promotionQuantity;
        return stockCount < quantity;
    }
}
