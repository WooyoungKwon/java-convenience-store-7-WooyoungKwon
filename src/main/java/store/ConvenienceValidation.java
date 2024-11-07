package store;

import static store.io.ConstErrorMessage.NOT_EXIST_ITEM;
import static store.io.ConstErrorMessage.OVER_QUANTITY;

import java.util.Map;

public class ConvenienceValidation {
    public void isItemExist(Map<String, Item> items, String itemName) {
        if (!items.containsKey(itemName) && !items.containsKey("프로모션 " + itemName)) {
            throw new IllegalArgumentException(NOT_EXIST_ITEM);
        }
    }

    public void restQuantity(int storeItemCount, int orderItemCount) {
        if (storeItemCount < orderItemCount) {
            throw new IllegalArgumentException(OVER_QUANTITY);
        }
    }

}
