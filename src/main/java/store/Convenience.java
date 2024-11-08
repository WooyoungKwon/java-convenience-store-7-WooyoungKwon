package store;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Convenience {
    ConvenienceValidation validation = new ConvenienceValidation();
    ConvenienceUtils utils = new ConvenienceUtils(validation);

    private final Map<String, Item> items = new LinkedHashMap<>();

    public void addItem(Item item) {
        items.put(utils.createItemKey(item), item);
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public Item findItem(String itemName) {
        return items.get(itemName);
    }

    public Item getPromotionItem(String itemName) {
        return items.get("프로모션 " + itemName);
    }
}
