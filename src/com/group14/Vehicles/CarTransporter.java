package com.group14.Vehicles;

import com.group14.Storage.IStorage;
import com.group14.Storage.IStorable;
import com.group14.Vehicles.Parts.Ramp;

import java.awt.Color;
import java.util.Stack;
import static java.lang.Math.pow;

/**
 * A representation of a vehicle that can transport smaller vehicles. It implements the
 * <tt>IStorage</tt> interface to handle this. It can store vehicles with a width of up to 2 meters.
 * The combined length of all those stored vehicles can not exceed 17.03 meters.
 *
 * This class extends <tt>Vehicle</tt>.
 *
 * @author Seif Bourogaa
 * @author Andreas Palmqvist
 * @author Simon Lindeberg Skoglund
 * @see Vehicle
 * @see IStorage
 */
public class CarTransporter extends Vehicle implements IStorage<Vehicle> {
    /**
     * The maximum length a vehicle can be loaded to the car transporter from.
     */
    public final static double LOADABLE_RADIUS = 20;

    /**
     * The length of the storage area. The vehicles combined length can not exceed this constant.
     */
    public final static double STORAGE_LENGTH = 17.03;

    /**
     * The width of the storage area. A vehicle can not be loaded if it has a larger width than this.
     */
    public final static double STORAGE_WIDTH = 2;

    /**
     * The ramp of the car transporter. Vehicles can not be loaded unless this ramp is lowered.
     */
    private Ramp ramp;

    /**
     * The stack in which all vehicles is stored.
     */
    private Stack<Vehicle> storedCars;

    /**
     * The length of the storage space that can be used.
     */
    private double storageLeft;

    /**
     * Constructs a pink car transporter with two doors and an engine power of 730 suns. It has a length of 19.03 meters
     * and a width of 2.44 meters.
     *
     * By default no vehicles are loaded unto the car transporter.
     *
     * @see Vehicle
     */
    public CarTransporter(double x, double y) {
        super(2, 730, Color.pink, "Car Transporter", STORAGE_LENGTH + 2, STORAGE_WIDTH + 0.44, x, y);
        ramp = new Ramp(-30, 90,90);
        storedCars = new Stack<>();
        storageLeft = STORAGE_LENGTH;
    }

    /**
     * Calculates whether a specified vehicle is in range or not.
     *
     * This is a private helper method.
     *
     * @param vehicle the vehicle that is checked
     * @return true if the vehicle is in range
     */
    private boolean vehicleInRange(Vehicle vehicle) {
        double xDist = vehicle.getX() - getX();
        double yDist = vehicle.getY() - getY();
        return pow(xDist, 2) + pow(yDist, 2) < pow(LOADABLE_RADIUS, 2);
    }

    /**
     * Calculates whether a specified vehicle fits into storage or not.
     *
     * This is a private helper method.
     *
     * @param vehicle the vehicle that is checked
     * @return true if the vehicle fits
     */
    private boolean vehicleFits(Vehicle vehicle) {
        boolean lengthFit = storageLeft >= vehicle.getLength();
        boolean widthFit = vehicle.getWidth() <= STORAGE_WIDTH;
        return lengthFit && widthFit;
    }

    /**
     * This is an extension of the move function of the super class. As well as moving the car transporter all vehicles
     * stored is moved as well. They are moved to the same position as the car transporter.
     *
     * @see Vehicle
     */
    public void move(){
        super.move();
        for (Vehicle vehicle : storedCars) {
            vehicle.move();
        }
    }

    /**
     * Raises the ramp.
     *
     * The ramp can only be raised if the car transporter is not in motion.
     *
     * @see Ramp
     * @see Ramp#raise()
     */
    public void raiseRamp() {
        if (getCurrentSpeed() == 0) { ramp.raise(); }
    }

    /**
     * Lower the ramp.
     *
     * The ramp can only be lowered if the car transporter is not in motion.
     *
     * @see Ramp
     * @see Ramp#lower()
     */
    public void lowerRamp() {
        if (getCurrentSpeed() == 0) { ramp.lower(); }
    }

    /**
     * Calculates the speed factor.
     *
     * 0 will be returned if the ramp is lowered.
     *
     * @return the speedfactor
     * @see Ramp
     * @see Ramp#isInNeutralPos()
     */
    public double speedFactor(){
        return ramp.isInNeutralPos() ? getEnginePower() * 0.01 : 0;
    }

    /**
     * Implementation of the increment speed function in <tt>Vehicle</tt>.
     *
     * Increments the speed based on the speed factor and a specified amount. The gas function which calls this function
     * makes sure that the amount parameter is in the range 0 <= x <= 1.
     *
     * The current speed can not be larger than the engine power (730).
     *
     * @param amount the amount to increment the speed by.
     * @return the speedfactor
     * @see Vehicle
     * @see #speedFactor()
     * @see Vehicle#gas(double)
     */
    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
    }

    /**
     * Implementation of the decrement speed function in <tt>Vehicle</tt>.
     *
     * Decrements the speed based on the speed factor and a specified amount. The brake function which calls this
     * function makes sure that the amount parameter is in the range 0 <= x <= 1.
     *
     * The current speed can not be less than 0.
     *
     * @param amount the amount to decrement the speed by.
     * @return the speedfactor
     * @see Vehicle
     * @see #speedFactor()
     * @see Vehicle#brake(double)
     */
    public void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    /**
     * Implementation of the load storable function in <tt>IStore</tt>.
     *
     * Load a vehicle into storage. The vehicle will be pushed to the stored cars stack.
     *
     * The vehicle will not be stored if:
     *     - The ramp is not lowered.
     *     - The vehicle is too far away.
     *     - The vehicle is too wide.
     *     - There is not enough room left in the storage.
     *
     * @param storable the vehicle to store.
     * @see IStorage
     * @see IStorable
     * @see Vehicle
     * @see Ramp
     * @see Ramp#isInNeutralPos()
     * @see #vehicleInRange(Vehicle)
     * @see #vehicleFits(Vehicle)
     */
    public void loadStorable(Vehicle storable) {
        if (ramp.isInNeutralPos() && vehicleInRange(storable) && vehicleFits(storable)) {
            storedCars.push(storable);
            storable.setStorage(this);
            storageLeft -= storable.getLength();
        }
    }

    /**
     * Implementation of the unload storable function in <tt>IStore</tt>.
     *
     * Unload a vehicle from storage. The vehicle will be popped from the stored cars stack.
     *
     * null will be returned instead if:
     *     - The ramp is not lowered.
     *     - No vehicles is stored.
     *
     * @return the vehicle at the top of the stored cars stack
     * @see IStorage
     * @see IStorable
     * @see Vehicle
     * @see Ramp
     * @see Ramp#isInNeutralPos()
     */
    public Vehicle unloadStorable() {
        Vehicle vehicle = null;

        double dropOffX;
        double dropOffY;
        if (!storedCars.isEmpty() && ramp.isInNeutralPos()) {
            vehicle = storedCars.pop();
            storageLeft += vehicle.getLength();

            // Drop off point is behind car transport.
            dropOffX = getX() - Math.cos(getDirection()) * getLength();
            dropOffY = getY() - Math.sin(getDirection()) * getLength();
            vehicle.setX(dropOffX);
            vehicle.setY(dropOffY);
            vehicle.unloadFromStorage();
        }

        return vehicle;
    }
}
