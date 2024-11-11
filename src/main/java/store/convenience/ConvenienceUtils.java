package store.convenience;

import store.item.Item;

public class ConvenienceUtils {
    public String createItemKey(Item item) {
        if (item.getPromotion() != null) {
            return "프로모션 " + item.getName();
        }
        return item.getName();
    }
}
