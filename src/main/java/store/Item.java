package store;

public class Item {
    private final String name;
    private int price;
    private int count;
    private Promotion promotion;

    public Item(ItemDto itemDto) {
        this.name = itemDto.getName();
        this.price = itemDto.getPrice();
        this.count = itemDto.getCount();
        this.promotion = itemDto.getPromotion();
    }

    public void addPromotion(Promotion promotion) {
        if (this.promotion != null) {
            throw new IllegalArgumentException("[ERROR] 이미 진행 중인 프로모션이 존재합니다..");
        }
        this.promotion = promotion;
    }

    public void decreaseStock(int count) {
        this.count -= count;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
