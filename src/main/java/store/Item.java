package store;

import java.util.Arrays;
import java.util.List;

public class Item {
    private final String name;
    private long price;
    private int count;
    private boolean promotion;

    public Item(ItemDto itemDto) {
        this.name = itemDto.getName();
        this.price = itemDto.getPrice();
        this.count = itemDto.getCount();
        this.promotion = itemDto.isPromotion();
    }

    public void decreaseStock(int count) {
        this.count -= count;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public boolean isPromotion() {
        return promotion;
    }
}
