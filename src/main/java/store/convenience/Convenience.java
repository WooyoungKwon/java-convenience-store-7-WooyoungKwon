package store.convenience;

import java.util.LinkedHashMap;
import java.util.Map;
import store.item.Item;

public class Convenience {
    ConvenienceUtils utils = new ConvenienceUtils();

    private final Map<String, Item> items = new LinkedHashMap<>();

    public void addItem(Item item) {
        items.put(utils.createItemKey(item), item);
    }

    public Item findItem(String itemName) {
        return items.get(itemName);
    }

    public Map<String, Item> getItems() {
        return items;
    }
}
