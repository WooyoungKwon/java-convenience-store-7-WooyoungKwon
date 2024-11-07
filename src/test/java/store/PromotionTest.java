package store;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PromotionTest {
    @Test
    void 프로모션_정보를_생성한다() {
        // given
        String inputPromotion = "탄산2+1";

        // when
        Promotion promotion = new Promotion("탄산2+1", 1, 1, "2024-01-01", "2024-12-31");

        // then
        assertEquals(promotion.getName(), inputPromotion);
    }


}
