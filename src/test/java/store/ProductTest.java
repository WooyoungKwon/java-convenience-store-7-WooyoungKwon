package store;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductTest {
    @DisplayName("상품을 생성한다.")
    @Test
    void 상품을_생성한다() {
        // given
        String productInfo = "콜라,1000,10,null";

        // when
        Product product = new Product(productInfo);

        // then
        assertEquals(product.name, "콜라");
    }
}
