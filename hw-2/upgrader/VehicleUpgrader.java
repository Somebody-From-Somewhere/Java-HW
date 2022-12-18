package hw_2.upgrader;

import hw_2.vehicles.impl.Car;

public interface VehicleUpgrader<I extends Car, O extends I> {
     O upgrade(I vehicle);
}
