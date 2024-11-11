package store.item.promotion;

import java.util.List;
import store.io.InputHandler;

public class PromotionService {
    private final InputHandler inputHandler;
    private final PromotionFactory promotionFactory;

    public PromotionService(InputHandler inputHandler, PromotionFactory promotionFactory) {
        this.inputHandler = inputHandler;
        this.promotionFactory = promotionFactory;
    }

    public PromotionFactory createPromotion() {
        List<String> promotionsInfo = inputHandler.readMdFile("promotions.md");
        for (String promotionInfo : promotionsInfo) {
            promotionFactory.addPromotions(promotionInfo);
        }
        return promotionFactory;
    }
}
