package store;

import java.util.ArrayList;
import java.util.List;

public class Convenience {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getAllStock() {
        return items;
    }
}
