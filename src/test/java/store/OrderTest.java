package store;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @DisplayName("상품을 구매하면 재고가 n개만큼 줄어든다")
    @Test
    void 상품을_구매하면_재고가_n개만큼_줄어든다() {
        // given
        String productInfo = "사이다,1000,8,탄산2+1";
        Item item = new Item(new ItemDto(productInfo));

        Convenience store = new Convenience();
        store.addItem(item);

        String inputOrder = "[사이다-5]";

        // when
        Order order = new Order(new OrderDto(inputOrder));
        store.buyItem(order);

        // then
        assertEquals(3, item.getCount());
    }}
