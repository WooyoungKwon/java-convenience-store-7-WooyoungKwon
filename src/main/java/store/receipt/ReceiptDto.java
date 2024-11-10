package store.receipt;

import java.util.List;
import store.item.Item;

public class ReceiptDto {
    private final String itemName;
    private final int count;
    private final int price;
    private final int eachPrice;
    private final int bonusCount;
    private int notBonusCount;

    public ReceiptDto(Item item, List<Integer> result) {
        this.itemName = item.getName();
        this.count = result.get(0);
        this.price = result.get(1);
        this.eachPrice = item.getPrice();
        this.bonusCount = result.get(2);
        if (item.getPromotion() != null) {
            int b = item.getPromotion().getNumber() + item.getPromotion().buyNumber();
            int c = this.bonusCount / item.getPromotion().getNumber();
            this.notBonusCount = this.count - c * b;
        }
    }

    public String getItemName() {
        return itemName;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    public int getEachPrice() {
        return eachPrice;
    }

    public int getBonusCount() {
        return bonusCount;
    }

    public int getNotBonusCount() {
        return notBonusCount;
    }
}
