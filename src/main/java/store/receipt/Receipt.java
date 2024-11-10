package store.receipt;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private int totalPrice;
    private int totalCount;
    private int eventDiscountPrice;
    private int membershipDiscountPrice;
    private int payMoney;
    private int discountTargetPrice;

    private final List<ReceiptDto> receiptDtos;

    public Receipt() {
        this.receiptDtos = new ArrayList<>();
    }

    public void addReceipt(ReceiptDto receiptDto) {
        this.receiptDtos.add(receiptDto);
    }

    public void calculatePrice() {
        for (ReceiptDto receiptDto : this.receiptDtos) {
            this.totalPrice += receiptDto.getPrice();
            this.totalCount += receiptDto.getCount();
            this.eventDiscountPrice += receiptDto.getPrice() / receiptDto.getCount() * receiptDto.getBonusCount();
            this.discountTargetPrice += receiptDto.getNotBonusCount() * receiptDto.getEachPrice();
            if (receiptDto.getBonusCount() == 0) {
                this.discountTargetPrice += receiptDto.getPrice();
            }
        }
    }

    public void membershipDiscounting() {
        this.membershipDiscountPrice = (int) (this.discountTargetPrice * 0.3);
    }

    public void calculateFinalPrice() {
        this.payMoney = this.totalPrice - this.eventDiscountPrice - this.membershipDiscountPrice;
    }

    public List<ReceiptDto> getReceipts() {
        return receiptDtos;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getEventDiscountPrice() {
        return eventDiscountPrice;
    }

    public int getMembershipDiscountPrice() {
        return membershipDiscountPrice;
    }

    public int getPayMoney() {
        return payMoney;
    }
}
