package store;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Map;
import store.io.InputHandler;

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
            convenienceService.printAllStock();

            System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
            String orderInfo = Console.readLine();
            Order order = new Order(new OrderDto(orderInfo));

            // 영수증 출력
            Map<String, String> receipt = convenienceService.buyItem(order);
            System.out.println(receipt.get("상품명") + "\t" + receipt.get("수량") + "\t" + receipt.get("금액") + "\t" + receipt.get("증정"));

            if (!InputHandler.wantContinueBuy()) {
                break;
            }
        }
    }
}
