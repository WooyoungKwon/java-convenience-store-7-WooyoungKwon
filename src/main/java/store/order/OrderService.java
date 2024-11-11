package store.order;

import static store.io.ConstErrorMessage.INVALID_ORDER_INPUT;

import java.util.ArrayList;
import java.util.List;
import store.io.InputHandler;
import store.io.OutputHandler;

public class OrderService {
    public List<Order> createOrders() {
        while (true) {
            try {
                List<String> orderInfo = InputHandler.inputOrder();
                List<Order> orders = new ArrayList<>();
                for (String order : orderInfo) {
                    orders.add(new Order(new OrderDto(order)));
                }
                return orders;
            } catch (IllegalArgumentException e) {
                OutputHandler.printGuideMessage(e.getMessage());
                OutputHandler.printLinebreak();
            }
        }
    }
}
