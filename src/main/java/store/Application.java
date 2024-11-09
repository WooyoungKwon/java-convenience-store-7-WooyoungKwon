package store;

import java.util.List;
import java.util.Map;
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
            String orderInfo = InputHandler.inputOrder();
            try {
                Order order = new Order(new OrderDto(orderInfo));
                // 구매
                Map<String, String> receipt = convenienceService.buyItem(order);
                // 영수증 출력
                System.out.println(receipt.get("상품명") + "\t" + receipt.get("수량") + "\t" + receipt.get("금액") + "\t" + receipt.get("증정"));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (RuntimeException e) {
                continue;
            }

            if (!InputHandler.yesOrNo(ConstMessage.WANT_CONTINUE_BUY)) {
                break;
            }
        }
    }
}
