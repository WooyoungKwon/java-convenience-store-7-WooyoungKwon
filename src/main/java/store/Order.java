package store;

public class Order {
    private final String name;
    private final int count;

    public Order(OrderDto orderDto) {
        this.name = orderDto.getName();
        this.count = orderDto.getCount();
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
