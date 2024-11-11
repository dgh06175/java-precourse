package store.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import store.model.promotion.Promotion;
import store.model.promotion.PromotionStatus;
import store.model.storage.Storage;
import store.model.store.Store;
import store.view.InputView;
import store.view.OutputView;

public class StoreController {
    private final InputView inputView;
    private final OutputView outputView;

    public StoreController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Store store = initStore();
        outputView.printWelcomeMessage();
        outputView.printProducts(store.getNormalStocks(), store.getPromotionStocks());
        Map<String, Integer> userOrders = inputUserOrder(store);
        for (var userOrder : userOrders.entrySet()) {
            String name = userOrder.getKey();
            int quantity = userOrder.getValue();
            PromotionStatus promotionStatus = store.checkPromotionStatus(name, quantity);
            if (promotionStatus == PromotionStatus.OK) {
                store.buy(name, quantity);
            }
        }
    }

    private Store initStore() {
        List<String> products = inputView.readProduct();
        Storage storage = new Storage(products);
        List<String> promotions = inputView.readPromotion();
        Promotion promotion = new Promotion(promotions);
        return new Store(storage, promotion);
    }

    private Map<String, Integer> inputUserOrder(Store store) {
        return executeWithRetry(() -> {
            Map<String, Integer> userBuyProducts = inputView.inputUserOrder();
            for (var userBuyProduct : userBuyProducts.entrySet()) {
                store.validateProduct(userBuyProduct.getKey(), userBuyProduct.getValue());
            }
            return userBuyProducts;
        });
    }

    private <T> T executeWithRetry(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
