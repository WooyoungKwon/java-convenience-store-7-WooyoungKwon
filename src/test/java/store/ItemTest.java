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
        String itemInfo = "콜라,1000,10,null";

        // when
        Item item = new Item(new ItemDto(itemInfo));

        // then
        assertEquals("콜라", item.getName());
    }

    @DisplayName("생성한 상품은 편의점에 적재한다.")
    @Test
    void 생성한_상품은_편의점에_적재한다() {
        // given
        String itemInfo1 = "콜라,1000,10,null";
        Item item1 = new Item(new ItemDto(itemInfo1));
        String itemInfo2 = "사이다,1500,20,반짝할인";
        Item item2 = new Item(new ItemDto(itemInfo2));

        // when
        Convenience store = new Convenience();
        store.addItem(item1);
        store.addItem(item2);

        // then

        assertEquals(10, store.getItem("콜라").getCount());
        assertEquals(20, store.getItem("프로모션 사이다").getCount());

    }

    @DisplayName("동일 상품에 여러 프로모션은 적용할 수 없다.")
    @Test
    void 동일_상품에_여러_프로모션은_적용할_수_없다() {
        // given
        String itemInfo = "콜라,1000,10,반짝할인";
        Item item = new Item(new ItemDto(itemInfo));

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            item.addPromotion(Promotion.SODA_ONE_PLUS_ONE);
                });
    }
}
