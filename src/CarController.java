import java.util.ArrayList;

import com.group14.Vehicles.*;

import javax.swing.*;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    private CarModel carModel;
    private CarView carView;
    int spinnerValue;

    public CarController(CarModel carModel, CarView carView){
        this.carModel = carModel;
        this.carView = carView;
        spinnerValue = 0;
        carView.mapBehaviorToButton(CarView.Button.GAS.ordinal(), e -> carModel.gas(spinnerValue));
        carView.mapBehaviorToButton(CarView.Button.BRAKE.ordinal(), e -> carModel.brake(spinnerValue));
        carView.mapBehaviorToButton(CarView.Button.TURBO_ON.ordinal(), e -> carModel.turboOn());
        carView.mapBehaviorToButton(CarView.Button.TURBO_OFF.ordinal(), e -> carModel.turboOff());
        carView.mapBehaviorToButton(CarView.Button.LIFT_BED.ordinal(), e -> carModel.raiseRamp());
        carView.mapBehaviorToButton(CarView.Button.LOWER_BED.ordinal(), e -> carModel.lowerRamp());
        carView.mapBehaviorToButton(CarView.Button.START.ordinal(), e -> carModel.start());
        carView.mapBehaviorToButton(CarView.Button.STOP.ordinal(), e -> carModel.stop());
        carView.mapBehaviorToSpinner(e -> this.spinnerValue = (int) ((JSpinner)e.getSource()).getValue());
    }




}
