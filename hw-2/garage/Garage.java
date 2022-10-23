package hw_2.garage;
import hw_2.Owner;
import hw_2.upgrader.VehicleUpgrader;
import hw_2.vehicles.impl.Car;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface Garage<E extends Car> {

    Collection<Owner> allCarsUniqueOwners();

    /**
     * Complexity should be less than O(n)
     */
    Collection<E> topThreeCarsByMaxVelocity();

    /**
     * Complexity should be O(1)
     */
    Collection<E> allCarsOfBrand(String brand);

    /**
     * Complexity should be less than O(n)
     */
    Collection<E> carsWithPowerMoreThan(int power);

    /**
     * Complexity should be O(1)
     */
    Collection<E> allCarsOfOwner(Owner owner);

    /**
     * @return mean value of owner age that has cars with given brand
     */
    int meanOwnersAgeOfCarBrand(String brand);

    /**
     * @return mean value of cars for all owners
     */
    int meanCarNumberForEachOwner();

    /**
     * Complexity should be less than O(n)
     * @return removed car
     */
    E removeCar(long carId);

    /**
     * Complexity should be less than O(n)
     */
    void  addCar(E car, Owner owner);

    void addCars(List<? extends E> cars);

    E removeCar(E car);

    void removeAll(List<? extends E> cars);

    <O extends E> List<O> upgradeAllVehiclesWith(VehicleUpgrader<E, O> upgrader);

    List<E> filterCars(Predicate<E> predicate);
}