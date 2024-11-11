package store.model.storage;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, NormalProduct> getNormalStock() {
        return Collections.unmodifiableMap(normalStock);
    }

    public Map<String, PromotionProduct> getPromotionStocks() {
        return Collections.unmodifiableMap(promotionStocks);
    }

    public boolean hasProduct(String name) {
        return normalStock.containsKey(name) || promotionStocks.containsKey(name);
    }

    public boolean hasStock(String name, int quantity) {
        int stockCount = normalStock.get(name).quantity() + promotionStocks.get(name).quantity();
        return stockCount >= quantity;
    }
}
