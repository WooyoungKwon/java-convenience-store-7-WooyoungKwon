package store;

import java.time.LocalDate;

public class ConvenienceService {

    private final Convenience store;
    private final ConvenienceValidation validator;

    public ConvenienceService(Convenience convenience, ConvenienceValidation validator) {
        this.store = convenience;
        this.validator = validator;
    }

    public int buyItem(Order order) {
        validator.isItemExist(store.getItems(), order.getName());
        Item item = getAvailableProduct(order);
        validator.restQuantity(item.getCount(), order.getCount());
        int freeItemCount = getFreeItemCount(order, item, LocalDate.now());
        item.decreaseStock(order.getCount() + freeItemCount);
        return freeItemCount;
    }

    private int getFreeItemCount(Order order, Item item, LocalDate date) {
        Promotion promotion = item.getPromotion();
        // 날짜가 유효한지 검증
        if (date.isBefore(promotion.getStartDate()) || date.isAfter(promotion.getEndDate())) {
            return 0;
        }
        return getFreeItemCount(order.getCount(), item.getPromotion());
    }

    private int getFreeItemCount(int orderCount, Promotion promotion) {
        return (orderCount / promotion.getBuyNumber()) * promotion.getGetNumber();
    }

    private Item getAvailableProduct(Order order) {
        // 프로모션 제품이 존재하고, 프로모션 제품의 재고가 사려는 개수보다 많다면 프로모션 제품을 반환한다.
        if (store.getItems().containsKey("프로모션 " + order.getName())) {
            if (store.findItem("프로모션 " + order.getName()).getCount() >= order.getCount()){
                return store.findItem("프로모션 " + order.getName());
            }
            // 추가예정: 사용자 입력 받고 (프로모션 없는데 살지 말지) 처리
        }
        return store.findItem(order.getName());
    }
}
