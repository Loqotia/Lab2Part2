/* Scania.java
 * 2019-02-11
 */

/**
 * This is a representation of a ramp that can move up and down.
 *
 * @author Seif Bourogaa
 * @author Andreas Palmqvist
 * @author Simon Lindeberg Skoglund
 */
public class Ramp {
    private double minAngle;
    private double maxAngle;
    private double neutralAngle;
    private double angle;

    /**
     * Constructs a ramp that has a minimum angle, a max angle and a neutral angle
     */
    public Ramp(double minAngle, double maxAngle, double neutralAngle) {
        this.maxAngle = maxAngle;
        this.minAngle = minAngle;
        this.neutralAngle = neutralAngle;
        angle = neutralAngle;
    }

    /**
     * Raise the ramp of the truck, set it to max angle.
     * Used by vehicles with a ramp with only two modes.
     *
     */
    public void raise() {
        angle = maxAngle;
    }

    /**
     * Lower the ramp of the truck, set it to min angle.
     * Used by vehicles with a ramp with only two modes.
     *
     */
    public void lower() {
        angle = minAngle;
    }

    /**
     * Raise the ramp by a certain angle. If the current angle + the new angle is greater than the max angle,
     * set the angle to max angle.
     *
     * @param angle the angle by which to to raise the ramp
     */
    public void raise(double angle) {
        if (this.angle + angle > maxAngle) { angle = maxAngle; }
        else { this.angle += angle; }
    }

    /**
     * Lower the ramp by a certain angle. If the current angle - the new angle is smaller than the min angle,
     * set the angle to min angle.
     *
     * @param angle the angle by which to to lower the ramp
     */
    public void lower(double angle) {
        if (this.angle - angle < maxAngle) { angle = minAngle; }
        else {this.angle -= angle;}
    }

    /**
     * Check if the ramp is in it's neutral position.
     */
    public boolean isInNeutralPos() {
        return angle == neutralAngle;
    }
}