package store;

import java.util.HashMap;
import java.util.Map;

public class Convenience {
    ConvenienceUtils utils = new ConvenienceUtils();
    ConvenienceValidation validation = new ConvenienceValidation();

    private final Map<String, Item> items = new HashMap<>();

    public void addItem(Item item) {
        items.put(utils.createItemKey(item), item);
    }


    public Item getItem(String itemName) {
        return items.get(itemName);
    }

    public Item getPromotionItem(String itemName) {
        return items.get("프로모션 " + itemName);
    }

    public void buyItem(Order order) {
        String itemName = order.getName();
        validation.isItemExist(items, order);
        Item item = items.get(itemName);
        validation.isExcessQuantity(order, item);
        item.decreaseStock(order.getCount());
    }

//    public int buyItems(String order) {
//
//    }
}
