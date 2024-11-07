package store;

import java.util.ArrayList;
import java.util.List;

public class Promotion {
    private final String name;
    private final int buyNumber;
    private final int getNumber;
    private final String startDate;
    private final String endDate;

    public Promotion(String name, int buyNumber, int getNumber, String startDate, String endDate) {
        this.name = name;
        this.buyNumber = buyNumber;
        this.getNumber = getNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }
}
