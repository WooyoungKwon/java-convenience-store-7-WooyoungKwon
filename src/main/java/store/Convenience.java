package store;

import java.util.HashMap;
import java.util.Map;

public class Convenience {
    private final Map<String, Item> items = new HashMap<>();

    public void addItem(Item item) {
        if (item.getPromotion() != Promotion.NOTHING) {
            items.put("프로모션 " + item.getName(), item);
        } else if (item.getPromotion() == Promotion.NOTHING) {
            items.put(item.getName(), item);
        }
    }

    public Item getItem(String itemName) {
        return items.get(itemName);
    }

    public void buyItem(Order order) {
        Item item = items.get(order.getName());
        item.decreaseStock(order.getCount());
    }

//    public int buyItems(String order) {
//
//    }
}
