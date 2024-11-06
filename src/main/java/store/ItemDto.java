package store;

import java.util.Arrays;
import java.util.List;

public class ItemDto {
    private String name;
    private long price;
    private int count;
    private Promotion promotion;

    public ItemDto(String itemInfo) {
        List<String> parseInfo = parseInfo(itemInfo);
        this.name = parseInfo.get(0);
        this.price = Long.parseLong(parseInfo.get(1));
        this.count = Integer.parseInt(parseInfo.get(2));
        this.promotion = promotion.parseString(parseInfo.get(3));
    }

    private List<String> parseInfo(String info) {
        return Arrays.asList(info.split(","));
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
