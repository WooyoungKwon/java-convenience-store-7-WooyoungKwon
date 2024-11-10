package store.convenience;

import static store.io.ConstErrorMessage.NOT_EXIST_ITEM;
import static store.io.ConstErrorMessage.OVER_QUANTITY;

import java.util.Map;
import store.item.Item;
import store.order.Order;

public class ConvenienceValidation {
    public void validExist(Map<String, Item> items, String itemName) {
        if (!items.containsKey(itemName) && !items.containsKey("프로모션 " + itemName)) {
            throw new IllegalArgumentException(NOT_EXIST_ITEM);
        }
    }

    public void validQuantity(Map<String, Item> items, Order order) {
        int totalQuantity = 0;
        for (String name : items.keySet()) {
            if (name.contains(order.getName())) {
                totalQuantity += items.get(name).getCount();
            }
        }
        if (totalQuantity < order.getCount()) {
            throw new IllegalArgumentException(OVER_QUANTITY);
        }
    }
}
