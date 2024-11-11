package store.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import store.model.promotion.Promotion;
import store.model.promotion.PromotionStatus;
import store.model.promotion.PromotionStatusQuantity;
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
        do {
            store.initReceipt();
            buyProducts(store);
            store.printReceipt();
        } while (inputView.inputContinue());
    }

    private void buyProducts(Store store) {
        outputView.printWelcomeMessage();
        outputView.printProducts(store.getNormalStocks(), store.getPromotionStocks());
        Map<String, Integer> userOrders = inputUserOrder(store);
        for (var userOrder : userOrders.entrySet()) {
            String name = userOrder.getKey();
            int quantity = userOrder.getValue();
            PromotionStatusQuantity promotionStatusQuantity = store.checkPromotionStatus(name, quantity);
            if (promotionStatusQuantity.promotionStatus() == PromotionStatus.PROMOTION_STOCK_NOT_ENOUGH) {
                boolean userChoice = inputView.inputPromotionStockNotEnough(name, promotionStatusQuantity.quantity());
                if (userChoice) {
                    store.buy(name, quantity);
                    continue;
                }
                store.buy(name, quantity - promotionStatusQuantity.quantity());
                continue;
            }
            if (promotionStatusQuantity.promotionStatus() == PromotionStatus.BONUS_OFFER_AVAILABLE) {
                boolean userChoice = inputView.inputBonusOffer(name, promotionStatusQuantity.quantity());
                if (userChoice) {
                    store.buy(name, quantity + promotionStatusQuantity.quantity());
                    continue;
                }
                store.buy(name, quantity);
                continue;
            }
            store.buy(name, quantity);
        }
        // 멤버십 할인
        if (inputView.inputMembership()) {
//            store.discountMembership();
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
