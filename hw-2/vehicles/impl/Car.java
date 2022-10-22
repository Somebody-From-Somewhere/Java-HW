package hw_2.vehicles.impl;

import hw_2.vehicles.Vehicle;

import java.util.Objects;

public class Car implements Comparable<Car>, Vehicle {

    public Car (long carId,
                String brand,
                String modelName,
                int maxVelocity,
                int power,
                long ownerId) {
        this.carId = carId;
        this.brand = brand;
        this.modelName = modelName;
        this.maxVelocity = maxVelocity;
        this.power = power;
        this.ownerId = ownerId;
    }

    public int getPower() {
        return power;
    }

    public int getMaxVelocity() {
        return maxVelocity;
    }

    public String getModelName() {
        return modelName;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public long ownerId() {
        return this.ownerId;
    }

    @Override
    public long id() {
        return this.carId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Car car = (Car) object;
        return carId == car.carId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId);
    }

    @Override
    public int compareTo(Car car) {
        int res = Integer.compare(power, car.power);
//        if (res == 0) {
//            return Long.compare(carId, car.id());
//        }
        return res;
    }

    private final long carId;
    private final String brand;
    private final String modelName;
    private final int maxVelocity;
    private final int power;
    private final long ownerId;

}
