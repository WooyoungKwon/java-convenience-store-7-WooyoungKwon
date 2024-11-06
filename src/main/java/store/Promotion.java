package store;

public enum Promotion {
    SODA_TWO_PLUS_ONE("탄산2+1"),
    SODA_ONE_PLUS_ONE("탄산1+1"),
    RECOMMENDED_MD("MD추천상품"),
    FLASH_DISCOUNT("반짝할인"),
    NOTHING("null");

    private final String display;

    Promotion(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public static Promotion parseStringToPromotion(String promotion) {
        for (Promotion p : Promotion.values()) {
            if (p.getDisplay().equals(promotion)) {
                return p;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 프로모션입니다.");
    }
}
