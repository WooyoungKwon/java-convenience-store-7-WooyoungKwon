package store;

import java.util.List;

public class ReceiptDto {
    private String itemName;
    private int count;
    private int price;
    private int eachPrice;
    private int bonusCount;
    private int notBonusCount;

    public ReceiptDto(Item item, List<Integer> result) {
        this.itemName = item.getName();
        this.count = result.get(0);
        this.price = result.get(1);
        this.eachPrice = item.getPrice();
        this.bonusCount = result.get(2);
        if (item.getPromotion() != null) {
            int b = item.getPromotion().getGetNumber() + item.getPromotion().getBuyNumber();
            int c = this.bonusCount / item.getPromotion().getGetNumber();
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
