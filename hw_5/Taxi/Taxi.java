package hw_5.Taxi;

import hw_5.Order.Order;
import java.util.List;

public interface Taxi {
    void joinThread() throws InterruptedException;

    void run();

    void placeOrder(Order order);

    List<Order> getExecutedOrders();
}
