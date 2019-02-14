import com.group14.Vehicles.Saab95;
import com.group14.Vehicles.Volvo240;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import static org.junit.Assert.assertTrue;

public class MoveTest {
    Saab95 saab;
    Volvo240 volvo;

    @Before
    public void init() {
        saab = new Saab95();
        volvo = new Volvo240();
    }

    @Test
    public void moveWithEngineOff() {
        saab.stopEngine();
        volvo.stopEngine();
        saab.move();
        volvo.move();
        assertTrue(saab.getX() == 0 && saab.getY() == 0 && volvo.getX() == 0 && volvo.getY() == 0);
    }

    @Test
    public void saabWithTurboOnVsVolvo() {
        saab.startEngine();
        saab.setTurboOn();
        volvo.startEngine();
        saab.gas(1);
        volvo.gas(1);
        saab.brake(0.5);
        volvo.brake(0.5);
        saab.move();
        volvo.move();

        assertTrue(saab.getCurrentSpeed() > volvo.getCurrentSpeed());
    }

    @Test
    public void saabWithTurboOffVsVolvo() {
        saab.startEngine();
        saab.setTurboOff();
        volvo.startEngine();
        saab.gas(1);
        volvo.gas(1);
        saab.brake(0.5);
        volvo.brake(0.5);
        saab.move();
        volvo.move();

        assertTrue(saab.getCurrentSpeed() == volvo.getCurrentSpeed());
    }

    @Test
    public void turnLeftAndBack() {
        saab.turnLeft(Math.PI);
        volvo.turnLeft(Math.PI);
        saab.turnRight(Math.PI);
        volvo.turnRight(Math.PI);

        assertTrue(saab.getDirection() == 0 && volvo.getDirection() == 0);
    }

    @Test
    public void saab95Attributes() {
        assertTrue(saab.getNrDoors() == 2
                && saab.getEnginePower() == 125
                && saab.getColor() == Color.red
                && saab.getModelName().equals("com.group14.Vehicles.Saab95"));
    }

    @Test
    public void volvo240Attributes() {
        assertTrue(volvo.getNrDoors() == 4
                && volvo.getEnginePower() == 100
                && volvo.getColor() == Color.black
                && volvo.getModelName().equals("com.group14.Vehicles.Volvo240"));
    }

    @Test
    public void switchCarColor() {
        saab.setColor(Color.black);
        volvo.setColor(Color.red);
        assertTrue(saab.getColor() == Color.black && volvo.getColor() == Color.red);
    }
}