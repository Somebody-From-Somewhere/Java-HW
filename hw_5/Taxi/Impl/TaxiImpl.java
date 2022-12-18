package hw_5.Taxi.Impl;

import hw_5.Dispatcher.Dispatcher;
import hw_5.Order.Order;
import hw_5.Taxi.Taxi;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaxiImpl implements Taxi, Comparable<TaxiImpl> {

    public TaxiImpl(Dispatcher dispatcher) {
        this.isActive = new AtomicBoolean(false);
        this.executedOrders = new ArrayList<>();
        this.dispatcher = dispatcher;
        this.taxiThread = new Thread(() -> {
            this.dispatcher.notifyAvailable(this);
            while (true) {
                synchronized (dispatcher) {
                    while (myOrder == null) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    myOrder.finishOrder();
                    executedOrders.add(myOrder);
                    myOrder = null;
                    this.dispatcher.notifyAvailable(this);
                    notifyAll();

                }
            }
        });
    }

    @Override
    public void joinThread() throws InterruptedException {
        if (isActive.get()) {
            throw new RuntimeException();
        }
        isActive.set(true);
        taxiThread.join();
    }

    @Override
    public void run() {
        taxiThread.start();
    }

    @Override
    public void placeOrder(Order order) {
        if (!isActive.get()) {
            myOrder = order;
        }
    }

    @Override
    public synchronized List<Order> getExecutedOrders() {
        return new ArrayList<>(executedOrders);
    }

    @Override
    public int compareTo(TaxiImpl o) {
        return 0;
    }

    private final Dispatcher dispatcher;
    private final List<Order> executedOrders;
    private final AtomicBoolean isActive;
    private final Thread taxiThread;
    Order myOrder;
}
