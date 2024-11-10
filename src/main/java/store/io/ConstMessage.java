package store.io;

public class ConstMessage {
    public static String WANT_CONTINUE_BUY = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";
    public static String WANT_MEMBERSHIP_DISCOUNT = "멤버십 할인을 받으시겠습니까? (Y/N)";

    public static String CAN_MORE_FREE_ITEM(String name, int number) {
        return "현재 " + name + "은(는) " + number + "개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    }
}
