package store.item;

import java.util.ArrayList;
import java.util.List;
import store.io.InputHandler;
import store.item.promotion.PromotionFactory;

public class ItemService {
    private final InputHandler inputHandler;
    private final PromotionFactory promotionFactory;

    public ItemService(InputHandler inputHandler, PromotionFactory promotionFactory) {
        this.inputHandler = inputHandler;
        this.promotionFactory = promotionFactory;
    }

    public List<Item> createItems() {
        List<String> itemsInfo = inputHandler.readMdFile("products.md");
        List<Item> items = new ArrayList<>();
        for (String itemInfo : itemsInfo) {
            // 편의점에 적재
            items.add(new Item(new ItemDto(itemInfo, promotionFactory)));
        }
        return items;
    }
}
