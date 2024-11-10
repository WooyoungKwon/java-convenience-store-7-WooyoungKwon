package store.item.promotion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PromotionFactory {
    private List<Promotion> promotions;

    public PromotionFactory() {
        promotions = new ArrayList<>();
    }

    public void addPromotions(String promotionInfo) {
        Promotion promotion = parsePromotionInfo(promotionInfo);
        promotions.add(promotion);
    }

    public Promotion parsePromotionInfo(String promotionInfo) {
        List<String> splitInput = new ArrayList<>(splitPromotionInfo(promotionInfo));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return new Promotion(
                splitInput.get(0),
                Integer.parseInt(splitInput.get(1)),
                Integer.parseInt(splitInput.get(2)),
                LocalDate.parse(splitInput.get(3), formatter).atStartOfDay(),
                LocalDate.parse(splitInput.get(4), formatter).atStartOfDay()
        );
    }

    public List<String> splitPromotionInfo(String promotionInfo) {
        return List.of(promotionInfo.split(","));
    }

    public Promotion findByName(String promotionName) {
        return promotions.stream()
                .filter(promotion -> promotion.getName().equals(promotionName))
                .findAny().orElse(null);
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }
}
