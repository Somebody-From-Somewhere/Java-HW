package hw_5.Dispatcher.Impl;

import hw_5.Dispatcher.Dispatcher;
import hw_5.Order.Order;
import hw_5.Taxi.Taxi;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class DispatcherImpl implements Dispatcher {

    public DispatcherImpl() {
        this.taxisQueue = new PriorityBlockingQueue<>();
        this.isThreadActive = new AtomicBoolean(false);
        this.lastOrderId = 0;

        this.thread = new Thread(() -> {
            while (true) {
                synchronized (this) {
                    while(taxisQueue.isEmpty()){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        Order newOrder = new Order(lastOrderId++);
                        placeOrder(taxisQueue.take(), newOrder);
                    } catch (InterruptedException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        });
    }

    @Override
    public void notifyAvailable(Taxi taxi) {
        taxisQueue.add(taxi);
    }

    @Override
    public void placeOrder(Taxi taxi, Order order) {
        taxi.placeOrder(order);
        notifyAll();
    }

    @Override
    public void run() {
        thread.start();
    }

    public void joinThread() throws InterruptedException {
        if (isThreadActive.get()) {
            throw new RuntimeException("Already joined!");
        }
        isThreadActive.set(true);
        thread.join();
    }


    private final BlockingQueue<Taxi> taxisQueue;
    private final AtomicBoolean isThreadActive;
    private final Thread thread;
    private long lastOrderId;
}
