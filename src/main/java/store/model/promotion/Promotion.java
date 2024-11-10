package store.model.promotion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Promotion {
    private final List<PromotionItem> promotionItems = new ArrayList<>();

    public Promotion(List<String> promotionLines) {
        for (String promotionLine : promotionLines.subList(1, promotionLines.size())) {
            List<String> splitPromotionLine = List.of(promotionLine.split(","));
            String name = splitPromotionLine.getFirst();
            int buy = Integer.parseInt(splitPromotionLine.get(1));
            int get = Integer.parseInt(splitPromotionLine.get(2));
            LocalDate start_date = parseToLocalDate(splitPromotionLine.get(3));
            LocalDate end_date = parseToLocalDate(splitPromotionLine.get(4));
            PromotionItem promotionItem = new PromotionItem(name, buy, get, start_date, end_date);
            promotionItems.add(promotionItem);
        }
    }

    private LocalDate parseToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }
}
