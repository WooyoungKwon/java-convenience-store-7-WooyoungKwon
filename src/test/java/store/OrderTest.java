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
        Order order = new Order(inputOrder);

        // then
        assertEquals("콜라", order.getName());
        assertEquals(3, order.getCount());
    }
}
