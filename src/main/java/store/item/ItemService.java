package store.item;

import java.util.ArrayList;
import java.util.List;
import store.io.InputHandler;
import store.item.promotion.PromotionFactory;

public class ItemService {
    private final InputHandler inputHandler;
    private final PromotionFactory promotionFactory;

    List<Item> items = new ArrayList<>();

    public ItemService(InputHandler inputHandler, PromotionFactory promotionFactory) {
        this.inputHandler = inputHandler;
        this.promotionFactory = promotionFactory;
    }

    public List<Item> createItems() {
        List<String> itemsInfo = inputHandler.readMdFile("products.md");
        List<String> addAdditionalItems = new ArrayList<>();
        addAdditionalItems.add("오렌지주스,1800,0,null");
        addAdditionalItems.add("탄산수,1200,0,null");
        for (String itemInfo : itemsInfo) {
            ItemDto itemDto = new ItemDto(itemInfo, promotionFactory);
            items.add(new Item(itemDto));
            addAdditionalItem(itemDto, addAdditionalItems);
        }
        return items;
    }

    private void addAdditionalItem(ItemDto itemDto, List<String> itemInfos) {
        for (String itemInfo : itemInfos) {
            ItemDto additionalItemDto = new ItemDto(itemInfo, promotionFactory);
            if (itemDto.getName().equals(additionalItemDto.getName())) {
                items.add(new Item(additionalItemDto));
                return;
            }
        }
    }
}
