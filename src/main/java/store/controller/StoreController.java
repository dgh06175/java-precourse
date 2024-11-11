package store.controller;

import java.util.List;
import store.model.promotion.Promotion;
import store.model.storage.ProductQuantity;
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
        // TODO: 재시작 기능 추가
        List<ProductQuantity> userBuyProducts = inputView.inputBuyProducts();
    }

    private Store initStore() {
        List<String> products = inputView.readProduct();
        Storage storage = new Storage(products);
        List<String> promotions = inputView.readPromotion();
        Promotion promotion = new Promotion(promotions);
        return new Store(storage, promotion);
    }
}
