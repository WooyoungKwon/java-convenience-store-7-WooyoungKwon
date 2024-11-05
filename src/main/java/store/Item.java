package store;

import java.util.Arrays;
import java.util.List;

public class Item {
    String name;
    long price;
    int count;
    boolean promotion;

    public Item(String itemInfo) {
        List<String> parseInfo = parseInfo(itemInfo);
        this.name = parseInfo.get(0);
        this.price = Long.parseLong(parseInfo.get(1));
        this.count = Integer.parseInt(parseInfo.get(2));
        this.promotion = Boolean.parseBoolean(parseInfo.get(3));
    }

    private List<String> parseInfo(String info) {
        return Arrays.asList(info.split(","));
    }
}
