/* Vehicle.java
 * 2019-02-11
 */

import java.awt.Color;

/**
 *  This is an abstract representation of a functional vehicle that can move around in 2D-space.
 *  This abstract class implements the <tt>Movable</tt> interface in order to move and turn
 *  the car and <tt>IStorable</tt> to store other vehicle.
 *
 * @see Saab95
 * @see Volvo240
 * @see IMovable
 * @see IStorable
 *
 * @author Seif Bourogaa
 * @author Andreas Palmqvist
 * @author Simon Lindeberg Skoglund
 */
public abstract class Vehicle implements IMovable, IStorable {
    /**
     *  The x-coordinate of the vehicle.
     */
    private double x;
    /**
     *  The y-coordinate of the vehicle.
     */
    private double y;
    /**
     * The direction in which the vehicle is facing, measured using radians.
     */
    private double direction;
    /**
     *  Current speed of the which.
     */
    protected double currentSpeed;
    /**
     *  Where the vehicle is stored.
     */
    private IStorage storedBy;
    /**
     *  The number of doors on the vehicle.
     */
    private int nrDoors;
    /**
     *  The engine power of the vehicle.
     */
    private double enginePower;
    /**
     *  The color of the vehicle.
     */
    private Color color;
    /**
     *  The model name of the vehicle.
     */
    private String modelName;
    /**
     *  The length of the vehicle.
     */
    private double length;
    /**
     *  The width of the vehicle.
     */
    private double width;

    /**
     *  Constructs a car with a number of doors, engine power, color,
     *  model name, length and width specified. Sets the car default
     *  position to (0, 0) and its direction to 0.
     *
     * @param nrDoors is the number of doors on the car
     *
     * @param enginePower is the horsepower on the car
     *
     * @param color is the color of the car
     *
     * @param modelName is the model name of the car
     *
     * @param length is the length of the car
     *
     * @param width is the width of the car
     */
    public Vehicle(int nrDoors, double enginePower, Color color, String modelName, double length, double width){
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.length = length;
        this.width = width;
        x = 0;
        y = 0;
        direction = 0;
        storedBy = null;
        stopEngine();
    }

    /**
     *  Return the x-coordinates.
     *
     * @return x-coordinates
     */
    public double getX(){
        return x;
    }

    /**
     *  Return the y-coordinates.
     *
     * @return y-coordinates
     */
    public double getY(){
        return y;
    }

    /**
     *  Returns the direction the vehicle is facing, in radians.
     *
     * @return direction of the vehicle
     */
    public double getDirection(){
        return direction;
    }

    /**
     *  Return the current speed of the vehicle.
     *
     * @return current speed of the vehicle
     */
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    /**
     *  Return  the number of doors on the vehicle.
     *
     * @return number of doors on the vehicle
     */
    public int getNrDoors(){
        return nrDoors;
    }

    /**
     * Returns the engine power of the vehicle.
     *
     * @return engine power of the vehicle
     */
    public double getEnginePower(){
        return enginePower;
    }

    /**
     * Return the color of the vehicle.
     *
     * @return color of the vehicle
     */
    public Color getColor(){
        return color;
    }

    /**
     *  Return the model name of the vehicle.
     *
     * @return model name of the vehicle
     */
    public String getModelName(){
        return modelName;
    }

    /**
     *  Return the length of the vehicle.
     *
     * @return length of the vehicle
     */
    public double getLength() { return length; }

    /**
     *  Return the width of the vehicle.
     *
     * @return width of the vehicle
     */
    public double getWidth() { return width; }

    /**
     * Set the vehicle x-coordinates.
     *
     * @param x the new vehicle x-coordinate
     */
    public void setX(double x) { this.x = x; }

    /**
     * Set the vehicle y-coordinates.
     *
     * @param y the new vehicle y-coordinate
     */
    public void setY(double y){
        this.y = y;
    }

    /**
     * Set the vehicle to a specified color.
     *
     * @param color the new vehicle color
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * Starts the engine by setting the current vehicle speed to 0.1.
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     *  Stops the engine by setting the current vehicle speed to 0.0.
     */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * Increase the current speed with a specified amount. This method
     * is implemented in the subclasses of this class.
     *
     * @param amount the amount to increase the speed by
     * @see Volvo240
     * @see Saab95
     * @see Scania
     * @see CarTransporter
     */
    public abstract void incrementSpeed(double amount);

    /**
     * Decrease the current speed with a specified amount. This method
     * is implemented in the subclasses of this class.
     *
     * @param amount the amount to decrease the speed by
     * @see Volvo240
     * @see Saab95
     * @see Scania
     * @see CarTransporter
     */
    public abstract void decrementSpeed(double amount);

    /**
     * Use <tt>incrementSpeed</tt> to increase the current speed by a specified amount, as long
     * as the amount is in the interval [0, 1],
     *
     * @param amount the amount to increase the speed by
     * @see #incrementSpeed(double)
     */
    public void gas(double amount){
        incrementSpeed(Math.min(Math.max(0,amount), 1));
    }

    /**
     * Use <tt>decrementSpeed</tt> to decrease the current speed by a specified amount, as long
     * as the amount is in the interval [0, 1].
     *
     * @param amount the amount to decrease the speed by
     * @see #decrementSpeed(double)
     */
    public void brake(double amount){
        decrementSpeed(Math.min(Math.max(0, amount), 1));
    }


    /**
     * Calculate the x- and y-coordinates of the directions vector multiplied by the current speed. The
     * resulting coordinates are added to position of the car. If the vehicle is stored the x and y-coordinates
     * become the coordinates where the vehicle is stored.
     *
     * @see #isStored()
     *
     */
    public void move(){
        if (!isStored()) {
            x += Math.cos(direction) * currentSpeed;
            y += Math.sin(direction) * currentSpeed;
        } else if (storedBy instanceof Vehicle) {
            Vehicle vehicleStorage = (Vehicle) storedBy;
            x = vehicleStorage.getX();
            y = vehicleStorage.getY();
            direction = vehicleStorage.getDirection();
        }
    }

    /**
     * Increases the direction of the car by a specified angle, simulating a left turn in 2D-space. It can only
     * turn if it is not stored and it's current speed is greater than 0.
     *
     * @param angle the angle by which to turn the car left, the unit for the angle is radians.
     * @see #isStored()
     */
    public void turnLeft(double angle) {
        if(currentSpeed > 0 && !isStored()){
            direction += angle;
        }
    }


    /**
     * Increases the direction of the car by a specified angle, simulating a right turn in 2D-space. It can only
     * turn if the it not stored and it's current speed is greater than 0.
     *
     * @param angle the angle by which to turn the car right, the unit for the angle is radians.
     * @see #isStored()
     * @see IStorage
     */
    public void turnRight(double angle){
        if(currentSpeed > 0 && !isStored()){
            direction -= angle;
        }
    }

    /**
     * Define whether a vehicle is stored or not. If the vehicle is not stored, and this method is called,
     * the vehicle will be stored and marked as such.
     *
     * @param storage parameter we send in to store the vehicle in.
     * @see #isStored()
     * @see IStorage
     */
    public void setStorage(IStorage storage) {
        if (storage instanceof Vehicle && !isStored()) { storedBy = storage; }
    }

    /**
     * Unload a stored vehicle. When this method is called, it will check if a vehicle is stored, if it is stored
     * it will then remove it from storage by removing the vehicle from the storage variable.
     *
     * @see #isStored()
     */
    public void unloadFromStorage() {
        if (isStored()) { storedBy = null; }
    }

    /**
     * A toggle that checks if a vehicle is stored or not.
     */
    public boolean isStored() {
        return storedBy != null;
    }
}