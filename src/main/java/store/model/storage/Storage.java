package store.model.storage;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    private final Map<String, NormalStock> normalStock = new LinkedHashMap<>();
    private final Map<String, PromotionStock> promotionStocks = new LinkedHashMap<>();

    public Storage(List<String> productLines) {
        for (String productLine : productLines.subList(1, productLines.size())) {
            List<String> splitProductLine = List.of(productLine.split(","));
            String name = splitProductLine.getFirst();
            int cost = Integer.parseInt(splitProductLine.get(1));
            int quantity = Integer.parseInt(splitProductLine.get(2));
            String promotion = splitProductLine.getLast();

            if (promotion.equalsIgnoreCase("null")) {
                normalStock.put(name, new NormalStock(name, cost, quantity));
                continue;
            }
            promotionStocks.put(name, new PromotionStock(name, cost, quantity, promotion));
            if (normalStock.get(name) == null) {
                normalStock.put(name, new NormalStock(name, cost, 0));
            }
        }
    }

    public Map<String, NormalStock> getNormalStock() {
        return Collections.unmodifiableMap(normalStock);
    }

    public Map<String, PromotionStock> getPromotionStocks() {
        return Collections.unmodifiableMap(promotionStocks);
    }
}
