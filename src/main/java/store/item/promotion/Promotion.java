package store.item.promotion;

import java.time.LocalDateTime;

public record Promotion(String name, int buyNumber, int getNumber, LocalDateTime startDate, LocalDateTime endDate) {
}
