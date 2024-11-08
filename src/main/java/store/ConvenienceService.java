package store;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.io.OutputHandler;

public class ConvenienceService {

    private final Convenience convenience;
    private final ConvenienceValidation validator;

    public ConvenienceService(Convenience convenience, ConvenienceValidation validator) {
        this.convenience = convenience;
        this.validator = validator;
    }

    public void printAllStock() {
        Map<String, Item> items = convenience.getItems();
        Collection<Item> values = items.values();
        for (Item item : values) {
            if (item.getPromotion() == null) {
                OutputHandler.printItemInfo(item);
                continue;
            }
            OutputHandler.printPromotionItemInfo(item);
        }
    }

    public Map<String, String> buyItem(Order order) {
        validator.isItemExist(convenience.getItems(), order.getName());
        Item item = getAvailableProduct(order);
        validator.restQuantity(item.getCount(), order.getCount());
        List<Integer> result = order.execute(item);

        return createReceipt(item, result);
    }

    private Map<String, String> createReceipt(Item item, List<Integer> result) {
        Map<String, String> receipt = new HashMap<>();
        receipt.put("상품명", item.getName());
        receipt.put("수량", String.valueOf(result.get(0)));
        receipt.put("금액", String.valueOf(result.get(1)));
        receipt.put("증정", String.valueOf(result.get(2)));
        return receipt;
    }

    private Item getAvailableProduct(Order order) {
        // 프로모션 제품이 존재하고, 프로모션 제품의 재고가 사려는 개수보다 많다면 프로모션 제품을 반환한다.
        if (convenience.getItems().containsKey("프로모션 " + order.getName())) {
            if (convenience.findItem("프로모션 " + order.getName()).getCount() >= order.getCount()) {
                return convenience.findItem("프로모션 " + order.getName());
            }
            // 추가예정: 사용자 입력 받고 (프로모션 없는데 살지 말지) 처리
        }
        return convenience.findItem(order.getName());
    }
}
