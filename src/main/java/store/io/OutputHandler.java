package store.io;

import java.util.List;
import store.receipt.Receipt;
import store.item.Item;
import store.receipt.ReceiptDto;

public class OutputHandler {

    public static void printGuideMessage(String message) {
        System.out.println(message);
    }

    public static void printWelcome() {
        System.out.println("안녕하세요. W편의점입니다.\n" + "현재 보유하고 있는 상품입니다.\n");
    }

    public static void printItemCount(Item item) {
        System.out.print("- " + item.getName() + " " + OutputHandler.parseDigits(item.getPrice()) + "원 ");
        if (item.getCount() == 0) {
            System.out.print("재고 없음");
            return;
        }
        System.out.print(item.getCount() + "개 ");
    }

    public static void printItemPromotion(Item item) {
        if (item.getPromotion() == null) {
            System.out.println();
            return;
        }
        System.out.println(item.getPromotion().name());
    }

    public static void printReceipt(Receipt receipt) {
        if (receipt == null) {
            return;
        }
        List<ReceiptDto> receiptDtos = receipt.getReceipts();
        System.out.println("==============W 편의점================");
        System.out.println("상품\t\t수량\t금액");
        for (ReceiptDto receiptDto : receiptDtos) {
            System.out.println(receiptDto.getItemName() + "\t\t" + receiptDto.getCount() + "\t" + parseDigits(
                    receiptDto.getPrice()));
        }
        printBonusReceipt(receiptDtos);
        printFinalReceipt(receipt);
    }

    public static void printBonusReceipt(List<ReceiptDto> receiptDtos) {
        System.out.println("=============증\t정===============");
        for (ReceiptDto receiptDto : receiptDtos) {
            if (receiptDto.getBonusCount() > 0) {
                System.out.println(receiptDto.getItemName() + "\t\t" + parseDigits(receiptDto.getBonusCount()));
            }
        }
    }

    private static void printFinalReceipt(Receipt receipt) {
        System.out.println("====================================");
        System.out.println("총구매액\t\t" + receipt.getTotalCount() + "\t" + parseDigits(receipt.getTotalPrice()));
        System.out.println("행사할인\t\t\t-" + parseDigits(receipt.getEventDiscountPrice()));
        System.out.println("멤버십할인\t\t\t-" + parseDigits(receipt.getMembershipDiscountPrice()));
        System.out.println("내실돈\t\t\t" + parseDigits(receipt.getPayMoney()));
        System.out.println();
    }

    private static String parseDigits(int price) {
        return String.format("%,d", price);
    }

    public static void printLinebreak() {
        System.out.println();
    }
}
