import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.group14.Vehicles.*;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        Vehicle volvo = new Volvo240();
        Vehicle saab = new Saab95();
        saab.setY(100);
        Vehicle scania = new Scania();
        scania.setY(200);

        cc.cars.add(volvo);
        cc.cars.add(saab);
        cc.cars.add(scania);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < cars.size(); i++) {
                Vehicle car = cars.get(i);
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                int worldWidth = frame.drawPanel.getWidth();
                int carWidth = frame.drawPanel.volvoImage.getWidth();
                if (x + carWidth > worldWidth || x < 0) {
                    car.turnLeft(Math.PI);

                }

                frame.drawPanel.moveit(x, y, frame.drawPanel.points.get(i));
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    void turboOn() {
        Saab95 saab = (Saab95) cars.get(1);
        saab.setTurboOn();
    }

    void turboOff() {
        Saab95 saab = (Saab95) cars.get(1);
        saab.setTurboOff();
    }

    void raiseRamp() {
        Scania scania = (Scania) cars.get(2);
        scania.raiseFlatbed(70);
    }

    void lowerRamp() {
        Scania scania = (Scania) cars.get(2);
        scania.lowerFlatbed(70);
    }

    void start() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }

    void stop() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }
}
