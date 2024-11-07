package store;

import static store.io.ConstErrorMessage.OVER_QUANTITY;

import java.util.Map;

public class ConvenienceUtils {

    private ConvenienceValidation validation;

    public ConvenienceUtils(ConvenienceValidation validation) {
        this.validation = validation;
    }

    public String createItemKey(Item item) {
        if (item.getPromotion() != null) {
            return "프로모션 " + item.getName();
        }
        return item.getName();
    }

    public Item findItem(Map<String, Item> items, Order order, String answer) {
        String itemName = order.getName();
        if (items.containsKey("프로모션 " + itemName)) {
            return items.get("프로모션 " + itemName);
        }
        Item item = items.get(itemName);
        int excessQuantity = getExcessQuantity(item.getCount(), order.getCount());
        if (excessQuantity <= 0) {
            throw new IllegalArgumentException(OVER_QUANTITY);
        }
        return item;
    }

    public int getExcessQuantity(int itemCount, int orderCount) {
        if (itemCount - orderCount <= 0) {
            throw new IllegalArgumentException(OVER_QUANTITY);
        }
        return itemCount - orderCount;
    }
}
