import java.awt.Color;
import java.util.Stack;
import static java.lang.Math.pow;

public class CarTransporter extends Vehicle implements IStorage<Vehicle> {
    public final static double LOADABLE_RADIUS = 10;
    public final static double STORAGE_LENGTH = 17.03;
    public final static double STORAGE_WIDTH = 2;

    private Ramp ramp;
    private Stack<Vehicle> storedCars;
    private double storageLengthUsed;



    public CarTransporter() {
        super(2, 730, Color.pink, "Car Transporter", STORAGE_LENGTH + 2, STORAGE_WIDTH + 0.44);
        ramp = new Ramp(-30, 90,90);
        storedCars = new Stack<>();
        storageLengthUsed = STORAGE_LENGTH;
    }

    public boolean getNeutralPos(){return this.ramp.isInNeutralPos();} //Only used in test!!!

    public double getStorageLength(){return storageLengthUsed;} //used only i test!!!


    private boolean vehicleInRange(Vehicle vehicle) {
        double xDist = vehicle.getX() - getX();
        double yDist = vehicle.getY() - getY();
        return pow(xDist, 2) + pow(yDist, 2) < pow(LOADABLE_RADIUS, 2);
    }

    private boolean vehicleFits(Vehicle vehicle) {
        boolean lengthFit = storageLengthUsed - vehicle.getLength() >= 0;
        boolean widthFit = vehicle.getWidth() <= STORAGE_WIDTH;
        return lengthFit && widthFit;
    }

    public void move(){
        super.move();
        for (Vehicle vehicle : storedCars) {
            vehicle.move();
        }
    }

    public void raiseRamp() {
        if (getCurrentSpeed() == 0) { ramp.raise(); }
    }

    public void lowerRamp() {
        if (getCurrentSpeed() == 0) { ramp.lower(); }
    }

    public double speedFactor(){
        return ramp.isInNeutralPos() ? getEnginePower() * 0.01 : 0;
    }

    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
    }

    public void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    // From ITransporter

    public void loadStorable(Vehicle storable) {
        if (ramp.isInNeutralPos() && vehicleInRange(storable) && vehicleFits(storable)) {
            storedCars.push(storable);
            storable.setStorage(this);
            storageLengthUsed -= storable.getLength();
        }//
    }

    public Vehicle unloadStorable() {
        Vehicle vehicle = null;

        double dropOffX;
        double dropOffY;
        if (!storedCars.isEmpty() && ramp.isInNeutralPos()) {
            vehicle = storedCars.pop();
            storageLengthUsed += vehicle.getLength();

            dropOffX = getX() - Math.cos(getDirection()) * getLength();
            dropOffY = getY() - Math.sin(getDirection()) * getLength();
            vehicle.setX(dropOffX);
            vehicle.setY(dropOffY);
            vehicle.unloadFromStorage();
        }

        return vehicle;
    }
}
