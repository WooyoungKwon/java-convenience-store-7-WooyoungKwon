package store;

import java.util.Arrays;
import java.util.List;

public class Product {
    String name;
    long price;
    int count;
    boolean promotion;

    public Product(String productInfo) {
        List<String> parseInfo = parseInfo(productInfo);
        this.name = parseInfo.get(0);
        this.price = Long.parseLong(parseInfo.get(1));
        this.count = Integer.parseInt(parseInfo.get(2));
        this.promotion = Boolean.parseBoolean(parseInfo.get(3));
    }

    private List<String> parseInfo(String info) {
        return Arrays.asList(info.split(","));
    }
}
