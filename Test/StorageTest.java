import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StorageTest {
    Scania scania;
    CarTransporter car_t;
    Volvo240 bil1;
    Saab95 bil2;
    Saab95 bil3;
    Volvo240[] bilarray;
    Workshop<Volvo240> workshop;

    @Before
    public void init() {
        scania = new Scania();
        car_t =  new CarTransporter();
        bil1 = new Volvo240();
        bil2 = new Saab95();
        bil3 = new Saab95();
        bilarray = new Volvo240[10];
        workshop = new Workshop<>(10);
    }

    //@Test
    public void TestLoad() {
        car_t.loadStorable(bil1);
        System.out.println(car_t.getStorageLength());
        assertTrue(car_t.getStorageLength() == CarTransporter.STORAGE_LENGTH - bil1.getLength());
    }


    @Test
    public void TestunLoad() {
        car_t.unloadStorable();
        System.out.println(car_t.getStorageLength());
        assertTrue(car_t.getStorageLength() == CarTransporter.STORAGE_LENGTH);
    }


    @Test
    public void TestWorkshop() {
        workshop.loadStorable(bil1);
        workshop.highlightCar(bil1);
        Volvo240 temp = workshop.unloadStorable();
        assertTrue(bil1 == temp);
    }
}