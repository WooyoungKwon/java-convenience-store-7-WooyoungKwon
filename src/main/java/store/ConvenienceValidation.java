package store;

import java.util.Map;

public class ConvenienceValidation {
    public void isExcessQuantity(Order order, Item item) {
        int count = item.getCount();
        if (count - order.getCount() <= 0) {
            throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    public void isItemExist(Map<String, Item> items, Order order) {
        if (!items.containsKey(order.getName()) && !items.containsKey("프로모션 " + order.getName())) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
        }
    }
}
