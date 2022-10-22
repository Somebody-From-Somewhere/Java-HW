package hw_2.upgrader.impl;

import hw_2.vehicles.impl.Car;
import hw_2.vehicles.impl.SuperCar;
import hw_2.upgrader.VehicleUpgrader;

public class CarToSuperCarUpgrader implements VehicleUpgrader<Car, SuperCar> {
    @Override
    public SuperCar upgrade(Car car) {
        return new SuperCar(car);
    }
}
