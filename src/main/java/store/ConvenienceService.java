package store;

public class ConvenienceService {

    private final Convenience store;
    private final ConvenienceValidation validator;

    public ConvenienceService(Convenience convenience, ConvenienceValidation validator) {
        this.store = convenience;
        this.validator = validator;
    }

    public void buyItem(Order order) {
        validator.isItemExist(store.getItems(), order.getName());
        Item item = getAvailableProduct(order);
        validator.isRestQuantity(item.getCount(), order.getCount());
        item.decreaseStock(order.getCount());
    }

    public Item getAvailableProduct(Order order) {
        // 프로모션 제품이 존재하고, 프로모션 제품의 재고가 사려는 개수보다 많다면 프로모션 제품을 반환한다.
        if (store.getItems().containsKey("프로모션 " + order.getName())
                && store.findItem("프로모션 " + order.getName()).getCount() >= order.getCount()) {
            return store.findItem("프로모션 " + order.getName());
        }
        return store.findItem(order.getName());
    }


}
