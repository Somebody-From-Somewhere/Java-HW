package hw_2.comparators;

import hw_2.vehicles.impl.Car;

import java.util.Comparator;

public class CarVelocityComparator implements Comparator<Car> {

    @Override
    public int compare(Car first, Car second) {
        int res = Long.compare(first.getMaxVelocity(), second.getMaxVelocity());
        if (res == 0) {
            return (-1) * Long.compare(first.id(), second.id());
        }
        return (-1) * res;
    }
}