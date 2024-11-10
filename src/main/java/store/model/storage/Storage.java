package store.model.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {
    private final List<Stock> products = new ArrayList<>();

    public Storage(List<String> productLines) {
        for (String productLine : productLines.subList(1, productLines.size())) {
            List<String> splitProductLine = List.of(productLine.split(","));
            String name = splitProductLine.getFirst();
            int cost = Integer.parseInt(splitProductLine.get(1));
            int quantity = Integer.parseInt(splitProductLine.get(2));
            String promotion = splitProductLine.getLast();

            Stock stock = new Stock(name, cost, quantity, promotion);
            this.products.add(stock);
        }
    }

    public List<Stock> getProducts() {
        return Collections.unmodifiableList(products);
    }
}
