package hw_5.Order;

public class Order implements Comparable<Order>{
    private final long orderId;

    public Order(long orderId) {
        this.orderId = orderId;
    }

    public void finishOrder() {
        try {
            int workTime = (int) (Math.random() * 500) + 100;
            Thread.sleep(workTime);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        System.out.println(orderId + ": finished!");
    }

    @Override
    public int compareTo(Order o) {
        return 0;
    }

}
