package store.convenience;

import static store.io.ConstMessage.WANT_MEMBERSHIP_DISCOUNT;

import java.util.List;
import store.io.ConstMessage;
import store.io.InputHandler;
import store.io.OutputHandler;
import store.item.Item;
import store.item.ItemService;
import store.item.promotion.PromotionFactory;
import store.item.promotion.PromotionService;
import store.order.Order;
import store.order.OrderService;
import store.receipt.Receipt;
import store.receipt.ReceiptDto;
import store.receipt.ReceiptService;

public class ConvenienceController {
    private final PromotionService promotionService;
    private final ConvenienceService convenienceService;
    private final OrderService orderService;
    private final ReceiptService receiptService;

    private ConvenienceController(PromotionService promotionService, ConvenienceService convenienceService,
                                  OrderService orderService, ReceiptService receiptService) {
        this.promotionService = promotionService;
        this.convenienceService = convenienceService;
        this.orderService = orderService;
        this.receiptService = receiptService;
    }

    public void run() {
        loadIItemsToConvenience();
        while (true) {
            printInfo();
            Receipt receipt;
            try {
                receipt = orderProcess();
            } catch (RuntimeException e) {
                continue;
            }
            membershipProcess(receipt);
            OutputHandler.printReceipt(receipt);
            if (programExitCondition()) {
                break;
            }
        }
    }

    private void membershipProcess(Receipt receipt) {
        boolean answer = InputHandler.yesOrNo(WANT_MEMBERSHIP_DISCOUNT);
        if (answer) {
            receipt.membershipDiscounting();
        }
        if (receipt == null) {
            return;
        }
        receipt.calculateFinalPrice();
    }

    private Receipt orderProcess() {
        List<Order> orders = orderService.createOrders();
        List<ReceiptDto> receiptDtos = convenienceService.purchaseWithReceipt(orders);
        Receipt receipt = receiptService.createReceipt(receiptDtos);
        receipt.calculatePrice();
        return receipt;
    }

    private void loadIItemsToConvenience() {
        PromotionFactory promotions = promotionService.createPromotion();
        ItemService itemService = new ItemService(new InputHandler(), promotions);
        List<Item> items = itemService.createItems();
        for (Item item : items) {
            convenienceService.addItemToConvenience(item);
        }
    }

    private void printInfo() {
        OutputHandler.printWelcome();
        convenienceService.printAllStock();
    }

    private boolean programExitCondition() {
        if (InputHandler.yesOrNo(ConstMessage.WANT_CONTINUE_BUY)) {
            return false;
        }
        return true;
    }

    public static ConvenienceController build() {
        InputHandler inputHandler = new InputHandler();
        PromotionFactory promotionFactory = new PromotionFactory();
        Convenience convenience = new Convenience();
        ConvenienceValidation convenienceValidation = new ConvenienceValidation();
        return new ConvenienceController(
                new PromotionService(inputHandler, promotionFactory),
                new ConvenienceService(convenience, convenienceValidation),
                new OrderService(),
                new ReceiptService());
    }
}
