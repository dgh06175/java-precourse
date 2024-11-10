package store.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
}
