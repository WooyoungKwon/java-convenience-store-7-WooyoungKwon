package store.convenience;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import store.item.Item;
import store.order.Order;
import store.item.promotion.Promotion;
import store.receipt.ReceiptDto;
import store.io.InputHandler;
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
            OutputHandler.printItemCount(item);
            OutputHandler.printItemPromotion(item);
        }
        OutputHandler.printLinebreak();
    }

    public List<ReceiptDto> purchaseWithReceipt(List<Order> orders) {
        List<ReceiptDto> receiptDtos = new ArrayList<>();
        for (Order order : orders) {
            receiptDtos.add(purchaseItem(order));
        }
        return receiptDtos;
    }

    public ReceiptDto purchaseItem(Order order) {
        validator.validExist(convenience.getItems(), order.getName());
        validator.validQuantity(convenience.getItems(), order);
        Item item;
        int bonusItemCount = 0;
        try {
            item = getAvailableProduct(order);
        } catch (IllegalStateException e) {
            if (!InputHandler.yesOrNo(e.getMessage())) {
                throw new RuntimeException();
            }
            bonusItemCount = calculateBonusItem(order);
            item = continueBuy(order);
        }
        List<Integer> result = order.execute(item);
        result.set(2, result.get(2) + bonusItemCount);
        return new ReceiptDto(item, result);
    }

    private Item continueBuy(Order order) {
        Item promotionItem = convenience.findItem("프로모션 " + order.getName());
        Item nomalItem = convenience.findItem(order.getName());
        nomalItem.addStock(promotionItem.getCount());
        promotionItem.decreaseStock(promotionItem.getCount());
        return nomalItem;
    }

    private int calculateBonusItem(Order order) {
        Item promotionItem = convenience.findItem("프로모션 " + order.getName());
        return promotionItem.getCount() / (promotionItem.getPromotion().getGetNumber() + promotionItem.getPromotion().getBuyNumber());
    }

    private Item getAvailableProduct(Order order) {
        // 프로모션 제품이 존재하고, 프로모션 제품의 재고가 사려는 개수보다 많다면 프로모션 제품을 반환한다.
        if (convenience.getItems().containsKey("프로모션 " + order.getName())) {
            Item promotionItem = convenience.findItem("프로모션 " + order.getName());
            if (promotionItem.getCount() >= order.getCount()) {
                return promotionItem;
            }
            Promotion promotion = promotionItem.getPromotion();
            int overCount = promotionItem.getCount() % (promotion.getBuyNumber() + promotion.getGetNumber())
                    + (order.getCount() - promotionItem.getCount());
            throw new IllegalStateException(
                    "현재 " + order.getName() + " " + overCount + "개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
        }
        return convenience.findItem(order.getName());
    }
}