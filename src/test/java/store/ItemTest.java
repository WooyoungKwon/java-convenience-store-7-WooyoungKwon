package store;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String itemInfo = "콜라,1000,10,null";
        Item item = new Item(new ItemDto(itemInfo));

        // when
        Convenience store = new Convenience();
        store.addItem(item);

        // then

        assertEquals(10, store.getItem("콜라").getCount());

    }
}
