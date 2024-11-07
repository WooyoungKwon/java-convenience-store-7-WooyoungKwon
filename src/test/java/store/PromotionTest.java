package store;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PromotionTest {
    @Test
    void 프로모션_정보를_생성한다() {
        // given
        String inputPromotion = "탄산2+1";

        // when
        Promotion promotion = new Promotion("탄산2+1", 1, 1, "2024-01-01", "2024-12-31");

        // then
        assertEquals(promotion.getName(), inputPromotion);
    }

    @Test
    void 구매_시_프로모션_재고를_우선적으로_차감한다() {
        //given

        // 프로모션 생성
        PromotionFactory promotionFactory = new PromotionFactory();
        promotionFactory.addPromotions("탄산2+1,2,3,2024-01-01,2024-12-31");

        // 제품 생성
        String promotionItemInfo = "콜라,1000,10,탄산2+1";
        Item promotionItem = new Item(new ItemDto(promotionItemInfo, promotionFactory));
        String itemInfo = "콜라,1000,10,null";
        Item item = new Item(new ItemDto(itemInfo, promotionFactory));
        // 편의점에 제품 적재
        Convenience convenience = new Convenience();
        ConvenienceValidation validation = new ConvenienceValidation();
        convenience.addItem(promotionItem);
        convenience.addItem(item);
        ConvenienceService convenienceService = new ConvenienceService(convenience, validation);

        // when
        String orderInfo1 = "[콜라-1]";
        Order order = new Order(new OrderDto(orderInfo1));
        convenienceService.buyItem(order);

        // then
        assertEquals(9, promotionItem.getCount());
    }

    @Test
    void 프로모션_재고가_없으면_일반_제품_재고를_판매한다() {
        // given
        PromotionFactory promotionFactory = new PromotionFactory();
        promotionFactory.addPromotions("탄산2+1,2,3,2024-01-01,2024-12-31");

        String itemInfo1 = "콜라,1000,0,탄산2+1";
        Item item1 = new Item(new ItemDto(itemInfo1, promotionFactory));
        String itemInfo2 = "콜라,1000,10,null";
        Item item2 = new Item(new ItemDto(itemInfo2, promotionFactory));

        Convenience convenience = new Convenience();
        ConvenienceValidation validation = new ConvenienceValidation();
        convenience.addItem(item1);
        convenience.addItem(item2);
        ConvenienceService convenienceService = new ConvenienceService(convenience, validation);

        String orderInfo = "[콜라-1]";
        Order order = new Order(new OrderDto(orderInfo));
        convenienceService.buyItem(order);

        assertEquals(9, item2.getCount());
    }
}