package hw_2.vehicles.impl;

import hw_2.vehicles.Vehicle;
import hw_2.vehicles.impl.Car;

public class Truck extends Car implements Vehicle, Comparable<Car>{

    public Truck(long carId, String brand, String modelName, int maxVelocity, int power, long ownerId) {
        super(carId, brand, modelName, maxVelocity, power, ownerId);
    }

    public Truck (Car car) {
        super(car.id(), car.getBrand(), car.getModelName(), car.getMaxVelocity(), car.getPower(), car.ownerId());
    }
}
