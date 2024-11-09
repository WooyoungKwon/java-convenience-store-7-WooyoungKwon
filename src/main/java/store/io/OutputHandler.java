package store.io;

import store.Item;

public class OutputHandler {
    public static void printWelcome() {
        System.out.println("안녕하세요. W편의점입니다.\n"
                + "현재 보유하고 있는 상품입니다.\n");
    }

    public static void printPromotionItemInfo(Item item) {
        System.out.println(
                "- " + item.getName() + " " + item.getPrice() + "원 " + item.getCount() + "개 " + item.getPromotion().getName());
    }

    public static void printItemInfo(Item item) {
        System.out.println("- " + item.getName() + " " + item.getPrice() + "원 " + item.getCount() + "개");
    }

    public static void printLinebreak() {
        System.out.println();
    }
}
