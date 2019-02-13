import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import static org.junit.Assert.assertTrue;

public class RampTest {
    Scania scania = new Scania();
    CarTransporter car_t = new CarTransporter();

    @Before
    public void init() {
        Vehicle scania = new Scania(); //0,70 0netural
        Vehicle car_t = new CarTransporter(); //-30, 90, 90neutral
    }

    @Test
    public void NotNeutral() {
        car_t.raiseRamp();
        car_t.lowerRamp();
        scania.raiseFlatbed(30);

        assertTrue(car_t.getNeutralPos() == false && scania.getNeutralPos() == false);
    }

    @Test
    public void Neutral() {
        car_t.raiseRamp();
        scania.lowerFlatbed(30);

        assertTrue(car_t.getNeutralPos() == true && scania.getNeutralPos() == true);
    }


    @Test
    public void StartEngine() {
        car_t.raiseRamp();
        car_t.lowerRamp();
        scania.raiseFlatbed(30);
        scania.startEngine();
        car_t.startEngine();


        assertTrue(scania.speedFactor() == 0 && scania.getX() == 0 && scania.getY() == 0 && car_t.speedFactor() == 0 && car_t.getX() == 0 && car_t.getY() == 0);
    }
}