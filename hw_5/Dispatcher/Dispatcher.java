package hw_5.Dispatcher;

import hw_5.Order.Order;
import hw_5.Taxi.Taxi;

public interface Dispatcher {
    void notifyAvailable(Taxi taxi);

    void placeOrder(Taxi taxi, Order order);

    void run();
}
