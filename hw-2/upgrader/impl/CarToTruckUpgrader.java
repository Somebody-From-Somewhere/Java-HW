package hw_2.upgrader.impl;

import hw_2.upgrader.VehicleUpgrader;
import hw_2.vehicles.impl.Car;
import hw_2.vehicles.impl.Truck;

public class CarToTruckUpgrader implements VehicleUpgrader<Car, Truck> {
    @Override
    public Truck upgrade(Car car) {
        return new Truck(car);
    }
}
