package store.item.promotion;

import java.time.LocalDateTime;

public class Promotion {
    private final String name;
    private final int buyNumber;
    private final int getNumber;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Promotion(String name, int buyNumber, int getNumber, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.buyNumber = buyNumber;
        this.getNumber = getNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public int getBuyNumber() {
        return buyNumber;
    }

    public int getGetNumber() {
        return getNumber;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
