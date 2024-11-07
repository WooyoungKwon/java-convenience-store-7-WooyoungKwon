package store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    @DisplayName("주문을 생성한다.")
    @Test
    void 주문을_생성한다() {
        // given
        String inputOrder = "[콜라-3]";

        // when
        Order order = new Order(new OrderDto(inputOrder));

        // then
        assertEquals("콜라", order.getName());
        assertEquals(3, order.getCount());
    }

    @DisplayName("재고에 없는 상품을 주문하면 에러가 발생한다.")
    @Test
    void 재고에_없는_상품을_주문하면_에러가_발생한다() {
        // given
        Convenience convenience = new Convenience();
        ConvenienceValidation validation = new ConvenienceValidation();
        String inputOrder = "[새콤달콤-3]";
        ConvenienceService service = new ConvenienceService(convenience, validation);

        // when & then
        Order order = new Order(new OrderDto(inputOrder));
        assertThrows(IllegalArgumentException.class, () ->
                service.buyItem(order)
        );
    }

    @DisplayName("상품을 구매하면 재고가 n개만큼 줄어든다")
    @Test
    void 상품을_구매하면_재고가_n개만큼_줄어든다() {
        // given
        PromotionFactory factory = new PromotionFactory();
        String productInfo = "사이다,1000,8,null";
        Item item = new Item(new ItemDto(productInfo, factory));

        Convenience store = new Convenience();
        ConvenienceValidation validation = new ConvenienceValidation();
        store.addItem(item);
        ConvenienceService storeService = new ConvenienceService(store, validation);

        String inputOrder = "[사이다-5]";

        // when
        Order order = new Order(new OrderDto(inputOrder));
        storeService.buyItem(order);

        // then
        assertEquals(3, item.getCount());
    }

    @DisplayName("상품의 재고가 없으면 에러가 발생한다.")
    @Test
    void 상품의_재고가_없으면_에러가_발생한다() {
        // given
        PromotionFactory factory = new PromotionFactory();
        String productInfo = "사이다,1000,3,null";
        Item item = new Item(new ItemDto(productInfo, factory));

        Convenience store = new Convenience();
        ConvenienceValidation validation = new ConvenienceValidation();
        store.addItem(item);
        ConvenienceService storeService = new ConvenienceService(store, validation);

        String inputOrder = "[사이다-4]";
        Order order = new Order(new OrderDto(inputOrder));

        // when & then
        assertThrows(IllegalArgumentException.class, () ->
                storeService.buyItem(order)
        );
    }
}
