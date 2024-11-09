package store;

import static store.io.ConstMessage.WANT_MEMBERSHIP_DISCOUNT;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import store.io.ConstMessage;
import store.io.InputHandler;
import store.io.OutputHandler;

public class Application {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();

        // 편의점 생성
        Convenience convenience = new Convenience();

        // 프로모션 생성
        PromotionFactory promotionFactory = new PromotionFactory();
        List<String> promotionsInfo = inputHandler.readMdFile("promotions.md");
        for (String promotionInfo : promotionsInfo) {
            promotionFactory.addPromotions(promotionInfo);
        }

        // 아이템 생성
        List<String> itemsInfo = inputHandler.readMdFile("products.md");
        for (String itemInfo : itemsInfo) {
            // 편의점에 적재
            convenience.addItem(new Item(new ItemDto(itemInfo, promotionFactory)));
        }

        ConvenienceService convenienceService = new ConvenienceService(convenience, new ConvenienceValidation());
        // 주문 생성
        while (true) {
            OutputHandler.printWelcome();
            // 모든 재고 출력
            convenienceService.printAllStock();
            // 주문 입력 받기
            Receipt receipt = new Receipt(new ArrayList<>());
            List<String> ordersInfo = InputHandler.inputOrder();
            boolean isApplyMembership = InputHandler.yesOrNo(WANT_MEMBERSHIP_DISCOUNT);
            try {
                for (String orderInfo : ordersInfo) {
                    Order order = new Order(new OrderDto(orderInfo));
                    // 구매
                    ReceiptDto receiptDto = convenienceService.buyItem(order);
                    receipt.addReceipt(receiptDto);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
            } catch (RuntimeException e) {
                continue;
            }
            receipt.create();
            if (isApplyMembership) {
                receipt.membershipDiscounting();
            }
            receipt.calculateFinalPrice();
            // 영수증 출력
            OutputHandler.printReceipt(receipt);

            if (!InputHandler.yesOrNo(ConstMessage.WANT_CONTINUE_BUY)) {
                break;
            }
        }
    }
}
