package store.view;

import static store.exception.StoreError.INPUT_NOT_VALID_FORMAT;

import camp.nextstep.edu.missionutils.Console;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.exception.StoreException;

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

    public Map<String, Integer> inputUserOrder() {
        Map<String, Integer> buyProducts = new HashMap<>();
        System.out.println("\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String input = Console.readLine();
        String[] splitInputs = input.split(",");
        try {
            for (String splitInput : splitInputs) {
                String name = extractName(splitInput);
                int quantity = extractQuantity(splitInput);
                buyProducts.put(name, buyProducts.getOrDefault(name, 0) + quantity);
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            throw new StoreException(INPUT_NOT_VALID_FORMAT);
        }
        return buyProducts;
    }

    private String extractName(String input) {
        String trimInput = input.trim().replaceAll("^\\[|]$", "");
        String[] splitTrimInput = trimInput.split("-");
        return splitTrimInput[0];
    }

    private int extractQuantity(String input) {
        String trimInput = input.trim().replaceAll("^\\[|]$", "");
        String[] splitTrimInput = trimInput.split("-");
        return Integer.parseInt(splitTrimInput[1]);
    }
}
