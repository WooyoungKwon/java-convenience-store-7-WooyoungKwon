package store;

import java.util.Arrays;
import java.util.List;

public class Item {
    private final String name;
    private long price;
    private int count;
    private Promotion promotion;

    public Item(ItemDto itemDto) {
        this.name = itemDto.getName();
        this.price = itemDto.getPrice();
        this.count = itemDto.getCount();
        this.promotion = itemDto.getPromotion();
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

    public Promotion getPromotion() {
        return promotion;
    }
}
