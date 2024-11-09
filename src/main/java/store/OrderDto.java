package store;

import static store.io.ConstErrorMessage.INVALID_ORDER_INPUT;

import java.util.Arrays;
import java.util.List;

public class OrderDto {
    private final String name;
    private final int count;

    public OrderDto(String inputOrder) {
        try {
            List<String> parseOrder = parseInput(inputOrder);
            this.name = parseOrder.get(0).substring(1);
            this.count = Integer.parseInt(parseOrder.get(1).substring(0, parseOrder.get(1).length() - 1));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(INVALID_ORDER_INPUT);
        }
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
