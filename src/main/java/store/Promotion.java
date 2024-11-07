package store;

import java.time.LocalDate;

public class Promotion {
    private final String name;
    private final int buyNumber;
    private final int getNumber;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, int buyNumber, int getNumber, LocalDate startDate, LocalDate endDate) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
