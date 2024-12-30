package store.model.store;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import store.model.storage.NormalProduct;

public class Receipt {
    private Map<String, NormalProduct> products = new HashMap<>();
    private Map<String, NormalProduct> promotions = new HashMap<>();

    public void addProduct(NormalProduct product) {
        if (products.containsKey(product.name())) {
            products.computeIfPresent(
                    product.name(),
                    (k, existProduct) -> new NormalProduct(
                            product.name(),
                            product.cost(),
                            existProduct.quantity() + product.quantity()
                    )
            );
            return;
        }
        products.put(product.name(), product);
    }

    public void addPromotion(NormalProduct promotion) {
        if (promotions.containsKey(promotion.name())) {
            promotions.computeIfPresent(
                    promotion.name(),
                    (k, existPromotion) -> new NormalProduct(
                            promotion.name(),
                            promotion.cost(),
                            existPromotion.quantity() + promotion.quantity()
                    )
            );
            return;
        }
        promotions.put(promotion.name(), promotion);
    }

    public void init() {
        products.clear();
        promotions.clear();
    }

    public Map<String, NormalProduct> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    public Map<String, NormalProduct> getPromotions() {
        return Collections.unmodifiableMap(promotions);
    }
}
