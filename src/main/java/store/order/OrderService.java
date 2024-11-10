package store.order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    public List<Order> createOrders(List<String> orderInfo) {
        List<Order> orders = new ArrayList<>();
        for (String order : orderInfo) {
            orders.add(new Order(new OrderDto(order)));
        }
        return orders;
    }
}
