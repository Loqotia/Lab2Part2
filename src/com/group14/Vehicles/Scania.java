package com.group14.Vehicles;

/*
 *2019-02-11
 */

import java.awt.*;
import com.group14.Vehicles.Parts.Ramp;

/**
 * This is a representation of a com.group14.Vehicles.Scania truck that can move around in 2D-space and have a ramp.
 * This class inherits functionality from <tt>Vehicle</tt> which includes the implementation
 * of the <tt>Movable</tt> interface
 *
 * The com.group14.Vehicles.Scania truck has two doors, 730 engine power and has a red yellow chassis.
 *
 * @see Vehicle
 * @see com.group14.Misc.IMovable
 * @author Seif Bourogaa
 * @author Andreas Palmqvist
 * @author Simon Lindeberg Skoglund
 */
public class Scania extends Vehicle {

    /**
    * Is the variable for the flatbed of the truck.
    * */
    private Ramp flatBed;

    /**
     * Constructs a com.group14.Vehicles.Scania truck which has 2 doors, an engine power of 730, a black chassis,
     * a length of 8.53 meters and a width of 2.44 meters. We instantiate a ramp that goes from
     * 0 to 70 degrees.
     *
     * @see Vehicle
     */
    public Scania(double x, double y){
        super(2, 730, Color.yellow, "Scania", 8.53, 2.44, x, y);
        flatBed = new Ramp(0, 70, 0);
    }

    /**
     * Raise the ramp of the truck by a certain angle, but only if the current speed is 0, by calling the method in
     * flatbed.
     *
     * @param angle the angle by which to to raise the flatbed
     * @see Ramp
     */
    public void raiseFlatbed(double angle) {
        if (getCurrentSpeed() == 0) { flatBed.raise(angle); }
    }

    /**
     * Lower the ramp of the truck by a certain angle, but only if the current speed is 0, by calling the method in
     * flatbed.
     *
     * @param angle the angle by which to to raise the flatbed
     * @see Ramp
     */
    public void lowerFlatbed(double angle) {
        if (getCurrentSpeed() == 0) { flatBed.lower(angle); }
    }

    /**
     * Calculates a speed factor based on on the engine power. If the flatbed is not in a neutral position,
     * checked by the method in flatbed the speed factor always return 0.
     *
     * @return the speed factor calculated in this method
     * @see Ramp
     */
    public double speedFactor(){
        return flatBed.isInNeutralPos() ? getEnginePower() * 0.01 : 0;
    }

    /**
     * Calculates and increase the current speed of the truck with a specified amount. Only
     * increases the speed if the current speed is less than the engine power of the truck. If the speed becomes
     * greater than the engine power of the truck then the current speed will always equal the engine power.
     *
     * @param amount the amount to increase the speed by
     * @see Vehicle
     * @see #speedFactor()
     */
    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
    }

    /**
     * Calculates and decrease the current speed of the truck by specified amount. Only
     * decreases the speed if the current speed is greater than 0. If the speed decrease
     * becomes less than 0 then the current will always be 0.
     *
     * @param amount the amount to decrease the speed by
     * @see Vehicle
     * @see #speedFactor()
     */
    public void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

}
