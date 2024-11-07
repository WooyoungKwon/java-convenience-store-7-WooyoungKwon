package store;

import java.util.Arrays;
import java.util.List;

public class ItemDto {
    private String name;
    private long price;
    private int count;
    private Promotion promotion;

    public ItemDto(String itemInfo, PromotionFactory promotionFactory) {
        List<String> parseInfo = parseInfo(itemInfo);
        this.name = parseInfo.get(0);
        this.price = Long.parseLong(parseInfo.get(1));
        this.count = Integer.parseInt(parseInfo.get(2));
        this.promotion = nullValidation(parseInfo, promotionFactory);
    }

    private List<String> parseInfo(String info) {
        return Arrays.asList(info.split(","));
    }

    private Promotion nullValidation(List<String> parseInfo, PromotionFactory promotionFactory) {
        if (parseInfo.get(3).equals("null")) {
            return null;
        }
        return promotionFactory.findByName(parseInfo.get(3));
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
