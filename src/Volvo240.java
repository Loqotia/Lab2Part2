/* Vehicle.java
 * 2019-02-11
 */
import java.awt.*;

/**
 *  This is a representation of a Volvo 240 car that can move around in 2D-space. This class
 *  inherits functionality from the superclass <tt>Car</tt> which includes the implementation of the <tt>Movable</tt> interface.
 *
 *  The Volvo 240 car has 4 doors, an engine power of 100 and a black chassis.
 *
 * @see Vehicle
 * @see IMovable
 *
 * @author Seif Bourogaa
 * @author Andreas Palmqvist
 * @author Simon Lindeberg Skoglund
 **/
public class Volvo240 extends Vehicle {

    /**
     * A constant value that represents the trim factor of the car.
     */
    public final static double trimFactor = 1.25;

    /**
     * Constructs a Volvo 240 car which has 4 doors, an engine power of 100, a black chassis,
     * a length of 4.9 meters and a width of 1.71 meters.
     *
     * @see Vehicle
     */
    public Volvo240(){
        super(4, 100, Color.BLACK, "Volvo240", 4.9, 1.71);
    }

    /**
     * Calculates the speed factor of the car after applying the trim factor to the engine power.
     *
     * @return a factor to increase the speed by
     */
    public double speedFactor(){
        return this.getEnginePower() * 0.01 * trimFactor;
    }

    /**
     * Calculates and increase the current speed of the car with a specified amount. Only
     * increases the speed if the current speed is less than the engine power of the car. If the speed becomes
     * greater than the engine power of the car then the current speed will always equal the engine power.
     *
     * @param amount the amount to increase the speed by
     * @see Vehicle
     * @see #speedFactor()
     */
    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, this.getEnginePower());
    }

    /**
     * Calculates and decrease the current speed of the car by specified amount. Only
     * decreases the speed if the current speed is greater than 0. If the speed decrease becomes less than 0 then the current
     * will always be 0.
     *
     * @param amount the amount to decrease the speed by
     * @see Vehicle
     * @see #speedFactor()
     */
    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

}
