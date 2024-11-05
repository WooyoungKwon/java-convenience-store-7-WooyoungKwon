package store;

public class Order {
    private final String name;
    private final int count;

    public Order(String order) {
        String[] split = order.split("-");
        this.name = split[0].substring(1);
        this.count = Integer.parseInt(split[1].substring(0,1));
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
