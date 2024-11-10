package store.item;

import java.util.Arrays;
import java.util.List;
import store.item.promotion.Promotion;
import store.item.promotion.PromotionFactory;

public class ItemDto {
    private String name;
    private int price;
    private int count;
    private Promotion promotion;

    public ItemDto(String itemInfo) {
        List<String> parseInfo = parseInfo(itemInfo);
        this.name = parseInfo.get(0);
        this.price = Integer.parseInt(parseInfo.get(1));
        this.count = Integer.parseInt(parseInfo.get(2));
        this.promotion = null;
    }

    public ItemDto(String itemInfo, PromotionFactory promotionFactory) {
        List<String> parseInfo = parseInfo(itemInfo);
        this.name = parseInfo.get(0);
        this.price = Integer.parseInt(parseInfo.get(1));
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

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
