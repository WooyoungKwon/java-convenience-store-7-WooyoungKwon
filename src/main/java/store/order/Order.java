package store.order;

import java.util.ArrayList;
import java.util.List;
import store.item.promotion.Promotion;
import store.io.InputHandler;
import camp.nextstep.edu.missionutils.DateTimes;
import store.item.Item;

public class Order {
    private final String name;
    private int count;

    public Order(OrderDto orderDto) {
        this.name = orderDto.getName();
        this.count = orderDto.getCount();
    }

    public List<Integer> execute(Item item) {
        Promotion promotion = item.getPromotion();
        if (item.getPromotion() != null) {
            if (this.count != item.getCount()) {
                this.count += needForPromotion(promotion);
            }
        }
        int freeItemCount = getFreeItemCount(this, item);
        item.decreaseStock(count);
        return new ArrayList<>(List.of(count, item.getPrice() * count, freeItemCount));
    }

    // 무료로 더 받을 수 있는 수량 안내
    private int needForPromotion(Promotion promotion) {
        if (this.count % (promotion.buyNumber() + promotion.getNumber()) == promotion.buyNumber()) {
            if (InputHandler.wantToAdd(this.name, promotion.getNumber())) {
                return promotion.getNumber();
            }
        }
        return 0;
    }
    // 증정 삼품 개수 가져오기

    private int getFreeItemCount(Order order, Item item) {
        Promotion promotion = item.getPromotion();
        // 날짜가 유효한지 검증
        if (validateDate(promotion)) {
            return order.getCount() / (item.getPromotion().buyNumber() + item.getPromotion().getNumber());
        }
        return 0;
    }

    private boolean validateDate(Promotion promotion) {
        if (promotion == null) {
            return false;
        } else if (DateTimes.now().isBefore(promotion.startDate()) || DateTimes.now().isAfter(promotion.endDate())) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
