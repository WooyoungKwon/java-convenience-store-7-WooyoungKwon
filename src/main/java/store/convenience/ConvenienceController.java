package store.convenience;

import static store.io.ConstMessage.WANT_MEMBERSHIP_DISCOUNT;

import java.util.List;
import store.io.ConstMessage;
import store.io.InputHandler;
import store.io.OutputHandler;
import store.item.Item;
import store.item.ItemDto;
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

    public ConvenienceController(PromotionService promotionService,
                                 ConvenienceService convenienceService,
                                 OrderService orderService,
                                 ReceiptService receiptService) {
        this.promotionService = promotionService;
        this.convenienceService = convenienceService;
        this.orderService = orderService;
        this.receiptService = receiptService;
    }

    public void run() {
        loadIItemsToConvenience();
        while (true) {
            printInfo();
            Receipt receipt = null;
            try {
                receipt = orderProcess();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
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
        List<Order> orders = orderService.createOrders(InputHandler.inputOrder());
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
            if (item.getName().equals("오렌지주스")) {
                convenienceService.addItemToConvenience(new Item(new ItemDto("오렌지주스,1800,0,null")));
            }
            if (item.getName().equals("탄산수")) {
                convenienceService.addItemToConvenience(new Item(new ItemDto("탄산수,1200,0,null")));
            }
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
}
