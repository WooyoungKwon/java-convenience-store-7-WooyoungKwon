package store;

import java.util.HashMap;
import java.util.Map;

public class Convenience {
    private final Map<String, Item> items = new HashMap<>();

    public void addItem(Item item) {
        boolean isPromotion = item.promotion;
        if (isPromotion) {
            items.put("프로모션 " + item.name, item);
        } else if (!isPromotion) {
            items.put(item.name, item);
        }
    }

    public Item getItem(String itemName) {
        return items.get(itemName);
    }

    public Map<String, Item> getAllStock() {
        return items;
    }

//    public int buyItems(String order) {
//
//    }
}
