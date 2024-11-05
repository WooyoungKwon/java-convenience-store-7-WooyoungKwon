package store;

import java.util.Arrays;
import java.util.List;

public class OrderDto {
    private final String name;
    private final int count;

    public OrderDto(String inputOrder) {
        List<String> parseOrder = parseInput(inputOrder);
        this.name = parseOrder.get(0).substring(1);
        this.count = Integer.parseInt(parseOrder.get(1).substring(0,1));
    }

    private List<String> parseInput(String input) {
        return Arrays.asList(input.split("-"));
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
