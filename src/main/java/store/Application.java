package store;

import store.convenience.Convenience;
import store.convenience.ConvenienceController;
import store.convenience.ConvenienceService;
import store.convenience.ConvenienceValidation;
import store.io.InputHandler;
import store.item.promotion.PromotionFactory;
import store.item.promotion.PromotionService;
import store.order.OrderService;
import store.receipt.ReceiptService;

public class Application {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        PromotionFactory promotionFactory = new PromotionFactory();
        Convenience convenience = new Convenience();
        ConvenienceValidation convenienceValidation = new ConvenienceValidation();

        ConvenienceController controller = new ConvenienceController(
                new PromotionService(inputHandler, promotionFactory),
                new ConvenienceService(convenience, convenienceValidation),
                new OrderService(),
                new ReceiptService());

        controller.run();
    }
}
