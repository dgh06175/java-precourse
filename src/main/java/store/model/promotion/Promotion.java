package store.model.promotion;

import static store.exception.StoreError.PROMOTION_NOT_FOUND;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import store.exception.StoreException;

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

    public boolean isAvailable(String name) {
        PromotionItem promotionItem = getPromotionItemOf(name);
        LocalDate now = DateTimes.now().toLocalDate();
        return isBetween(now, promotionItem.start_date(), promotionItem.end_date());
    }

    private boolean isBetween(LocalDate now, LocalDate start_date, LocalDate end_date) {
        if (start_date.isEqual(now) || end_date.isEqual(now)) {
            return true;
        }
        return start_date.isBefore(now) && end_date.isAfter(now);
    }

    public PromotionItem getPromotionItemOf(String promotionName) {
        return promotionItems.stream()
                .filter(item -> item.name().equals(promotionName))
                .findFirst()
                .orElseThrow(() -> new StoreException(PROMOTION_NOT_FOUND));
    }

    private LocalDate parseToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }
}
