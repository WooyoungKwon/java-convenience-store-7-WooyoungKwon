package store;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ItemTest {
    @DisplayName("상품을 생성한다.")
    @Test
    void 상품을_생성한다() {
        // given
        String itemInfo = "콜라,1000,10,null";

        // when
        Item item = new Item(itemInfo);

        // then
        assertEquals("콜라", item.name);
    }

    @DisplayName("생성한 상품은 편의점에 적재한다.")
    @Test
    void 생성한_상품은_편의점에_적재한다() {
        // given
        String itemInfo = "콜라,1000,10,null";
        Item item = new Item(itemInfo);

        // when
        Convenience store = new Convenience();
        store.addItem(item);

        // then

        assertEquals(10, store.getItem("콜라").count);

    }

//    @DisplayName("상품을 구매하면 재고가 n개만큼 줄어든다")
//    @Test
//    void 상품을_구매하면_재고가_n개만큼_줄어든다() {
//        // given
//        String productInfo = "사이다,1000,8,탄산2+1";
//        Item item = new Item(productInfo);
//
//        Convenience store = new Convenience();
//        store.addItem(item);
//
//        String order = "[사이다-5]";
//
//        // when
//        store.buyItems(order);
//
//        // then
//        assertEquals();
//
//    }
}
