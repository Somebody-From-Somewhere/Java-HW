package hw_2.garage.impl;

import hw_2.Owner;
import hw_2.comparators.CarVelocityComparator;
import hw_2.garage.Garage;
import hw_2.upgrader.VehicleUpgrader;
import hw_2.upgrader.impl.CarToTruckUpgrader;
import hw_2.vehicles.impl.Car;
import hw_2.vehicles.impl.Truck;

import static java.util.stream.Collectors.toList;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GarageImpl<E extends Car> implements Garage<E> {

    public GarageImpl() {
        idCarMap = new HashMap<>();
        idOwnerMap = new HashMap<>();
        brandCarMap = new HashMap<>();
        ownerCarMap = new HashMap<>();
        powerSortTree = new TreeMap<>();

        velocitySortTree = new TreeSet<>(new CarVelocityComparator());

    }

    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        return ownerCarMap.keySet();
    }

    @Override
    public Collection<E> topThreeCarsByMaxVelocity() {
        return velocitySortTree.stream().limit(3).collect(toList());
    }

    @Override
    public Collection<E> allCarsOfBrand(String brand) {
        return brandCarMap.get(brand);
    }

    @Override
    public Collection<E> carsWithPowerMoreThan(int power) {
        return powerSortTree.tailMap(power + 1).values();
    }

    @Override
    public Collection<E> allCarsOfOwner(Owner owner) {
        return ownerCarMap.get(owner);
    }

    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {

        long ageSum = 0;
        HashSet<E> carsOfBrand = brandCarMap.get(brand);
        long ownersOfBrand = 0;

        for (Car car : carsOfBrand) {
            ageSum += idOwnerMap.get(car.ownerId()).getAge();
            ownersOfBrand++;
        }

        return Math.round((float) ageSum / ownersOfBrand);
    }

    @Override
    public int meanCarNumberForEachOwner() {

        long counter = 0;
        double amount = 0;
        for (Owner owner : ownerCarMap.keySet()) {
            counter++;
            amount += ownerCarMap.get(owner).size();
        }
        return (int) Math.round(amount/counter);
    }


    @Override
    public E removeCar(long carId) {
        E car = idCarMap.get(carId);
        idCarMap.remove(carId);
        brandCarMap.get(car.getBrand()).remove(car);
        ownerCarMap.get(idOwnerMap.get(car.ownerId())).remove(car);
        powerSortTree.remove(car.getPower(), car);
        velocitySortTree.remove(car);

        return car;
    }


    @Override
    public void addCar(E car, Owner owner) {
        idCarMap.put(car.id(), car);
        idOwnerMap.put(car.ownerId(), owner);
        ownerCarMap.computeIfAbsent(owner, k -> new HashSet<>());
        brandCarMap.computeIfAbsent(car.getBrand(), k -> new HashSet<>());
        brandCarMap.get(car.getBrand()).add(car);
        ownerCarMap.get(owner).add(car);
        powerSortTree.put(car.getPower(), car);

        velocitySortTree.add(car);

    }

    @Override
    public void addCars(List<? extends E> cars) {
        for (E car : cars) {
            if (idOwnerMap.get(car.ownerId()) == null) {
                idOwnerMap.put(car.ownerId(), new Owner(car.ownerId(), "Unknown", "Unknown", 0));
            }
            addCar(car, idOwnerMap.get(car.ownerId()));
        }
    }

    @Override
    public E removeCar(E car) {
        return removeCar(car.id());
    }

    @Override
    public void removeAll(List<? extends E> cars) {
        for (E car : cars) {
            removeCar(car.id());
        }
    }

    @Override
    public <O extends E> List<O> upgradeAllVehiclesWith(VehicleUpgrader<E, O> upgrader) {
        List<E> current = List.copyOf(idCarMap.values());
        List<O> upgraded = current.stream().map(upgrader::upgrade)
                .collect(Collectors.toList());
        removeAll(current);
        addCars(upgraded);

        return upgraded;
    }

    @Override
    public List<E> filterCars(Predicate<E> predicate) {
        return idCarMap.values()
                .stream()
                .filter( predicate )
                .collect(Collectors.<E>toList());
    }

    private final HashMap<Long, E> idCarMap;
    private final HashMap<Long, Owner> idOwnerMap;
    private final HashMap<String, HashSet<E>> brandCarMap;
    private final HashMap<Owner, HashSet<E>> ownerCarMap;
    private final SortedMap<Integer, E> powerSortTree;

    private final TreeSet<E> velocitySortTree;

}