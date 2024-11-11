package store.view;

import camp.nextstep.edu.missionutils.Console;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import store.model.storage.ProductQuantity;

public class InputView {
    public static final String FILE_PATH = "src/main/resources/";
    public static final String PRODUCT_FILE_NAME = "products.md";
    public static final String PROMOTION_FILE_NAME = "promotions.md";

    public List<String> readProduct() {
        return readText(PRODUCT_FILE_NAME);
    }

    public List<String> readPromotion() {
        return readText(PROMOTION_FILE_NAME);
    }

    private List<String> readText(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH + fileName));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public List<ProductQuantity> inputBuyProducts() {
        List<ProductQuantity> buyProducts = new ArrayList<>();
        System.out.println("\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String input = Console.readLine();
        String[] splitInputs = input.split(",");
        for (String splitInput : splitInputs) {
            ProductQuantity productQuantity = extractProductQuantity(splitInput, buyProducts);
            buyProducts.add(productQuantity);
        }
        return buyProducts;
    }

    private ProductQuantity extractProductQuantity(String splitInput, List<ProductQuantity> buyProducts) {
        String trimInput = splitInput.trim().replaceAll("^\\[|]$", "");
        String[] splitTrimInput = trimInput.split("-");
        String name = splitTrimInput[0];
        int quantity = Integer.parseInt(splitTrimInput[1]);
        return new ProductQuantity(name, quantity);
    }
}
