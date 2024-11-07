package store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ItemTest {
    @DisplayName("상품을 생성한다.")
    @Test
    void 상품을_생성한다() {
        // given
        PromotionFactory factory = new PromotionFactory();
        String itemInfo = "콜라,1000,10,null";

        // when
        Item item = new Item(new ItemDto(itemInfo, factory));

        // then
        assertEquals("콜라", item.getName());
    }

    @DisplayName("생성한 상품은 편의점에 적재한다.")
    @Test
    void 생성한_상품은_편의점에_적재한다() {
        // given
        PromotionFactory factory = new PromotionFactory();
        factory.addPromotions("반짝할인,1,1,2024-11-01,2024-11-30");

        String itemInfo1 = "콜라,1000,10,null";
        Item item1 = new Item(new ItemDto(itemInfo1, factory));
        String itemInfo2 = "사이다,1500,20,반짝할인";
        Item item2 = new Item(new ItemDto(itemInfo2, factory));

        // when
        Convenience store = new Convenience();
        store.addItem(item1);
        store.addItem(item2);

        // then
        assertEquals(10, store.findItem("콜라").getCount());
        assertEquals(null, store.findItem("콜라").getPromotion());
        assertEquals(20, store.findItem("프로모션 사이다").getCount());
        assertEquals("반짝할인", store.findItem("프로모션 사이다").getPromotion().getName());

    }

    @DisplayName("동일 상품에 여러 프로모션은 적용할 수 없다.")
    @Test
    void 동일_상품에_여러_프로모션은_적용할_수_없다() {
        // given
        PromotionFactory factory = new PromotionFactory();
        factory.addPromotions("탄산1+1,1,1,2024-01-01,2024-12-31");

        String itemInfo = "콜라,1000,10,탄산1+1";
        Item item = new Item(new ItemDto(itemInfo, factory));

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            item.addPromotion(new Promotion("탄산1+2", 1, 2, "2024-01-01", "2024-12-31"));
                });
    }
}
