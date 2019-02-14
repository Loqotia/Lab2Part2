package com.group14.Storage;/* com.group14.Storage.Workshop.java
 * 2019-02-11
 */
import java.util.ArrayList;
import com.group14.Vehicles.Vehicle;

/**
 * This class represents a com.group14.Storage.Workshop that can store cars of any type. It can specialize in a specific type as well!
 * Its storage capabilities comes from com.group14.Storage.IStorage!
 *
 * @param <T> type of vehicle
 * @see IStorage
 * @see com.group14.Vehicles.Vehicle
 */
public class Workshop <T extends Vehicle> implements IStorage<T> {
    /**
     * The amount of vehicles that can be stored.
     */
    private int storageSpace;

    /**
     * An ArrayList that stores vehicles.
     */
    private ArrayList<T> storedCars;

    /**
     * At which index the highlighted Vehicle is stored. A car must be highlighted in order to get unloaded from
     * the workshop.
     */
    private int highlightedIndex;

    /**
     * Constructs a com.group14.Storage.Workshop with a specified storage space. Nothing is highlighted by default as there is no
     * Vehicle stored by default.
     *
     * @param storageSpace the amount of vehicles that can be stored
     */
    public Workshop(int storageSpace) {
        this.storageSpace = storageSpace;
        storedCars = new ArrayList(storageSpace);
        highlightNothing();
    }

    /**
     * Constructs a com.group14.Storage.Workshop with a specified storage space.
     *
     * @param storageSpace the amount of vehicles that can be stored
     */
    private void highlightNothing() { highlightedIndex = -1; }

    /**
     * Highlights a Vehicle by index. If the index does not exist the highlight does not change.
     *
     * @param index the index to highlight
     */
    public void highlightCar(int index) {
        if (index >= 0 && index < storedCars.size()) { highlightedIndex = index; }
    }

    /**
     * Highlights a Vehicle by reference. If the car is not in the workshop, the highlight does not change.
     *
     * @param car the Vehicle to highlight
     */
    public void highlightCar(T car) {
        if (storedCars.contains(car)) { highlightedIndex = storedCars.indexOf(car); }
    }

    /**
     * Store a Vehicle in the workshop. If the com.group14.Storage.Workshop is out of storage space, nothing will be stored. If the
     * user tries to store a Vehicle that is stored some place else, nothing is stored.
     *
     * @param car the Vehicle to store
     * @see IStorable
     */
    public void loadStorable(T car) {
        if (storedCars.size() + 1 < storageSpace && !car.isStored()) {
            car.setStorage(this);
            storedCars.add(car);
        }
    }

    /**
     * Tries to unload a Vehicle. If no Vehicle is stored, null will be returned. Nothing will be highlighted
     * after a successful unload operation.
     *
     * @return the unloaded Vehicle.
     * @see IStorable
     */
    public T unloadStorable() {
        T unloadedCar = null;
        if (highlightedIndex >= 0 && highlightedIndex < storedCars.size()) {
            unloadedCar = storedCars.remove(highlightedIndex);
            highlightNothing();
        }
        return unloadedCar;
    }
}
