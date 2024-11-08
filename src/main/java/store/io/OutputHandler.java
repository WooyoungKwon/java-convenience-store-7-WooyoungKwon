package store.io;

import store.Item;

public class OutputHandler {
    public void printNeverthelessStillPurchase(Item item) {
        System.out.println("현재 " + item.getName() + item.getCount() + "개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
    }

    public static void printPromotionItemInfo(Item item) {
        System.out.println(
                "- " + item.getName() + " " + item.getPrice() + "원 " + item.getCount() + "개 " + item.getPromotion().getName());
    }

    public static void printItemInfo(Item item) {
        System.out.println("- " + item.getName() + " " + item.getPrice() + "원 " + item.getCount() + "개");
    }
}
