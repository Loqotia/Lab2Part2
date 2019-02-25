package refactor;

import javax.swing.*;

public class VehicleSim {
    private final int WINDOW_WIDTH  = 800;
    private final int WINDOW_HEIGHT = 800;
    private final int DELAY         = 50;

    private R_CarView       carView;
    private R_CarController carController;
    private Timer           timer;

    private VehicleSim() {
        carView       = new R_CarView(WINDOW_WIDTH, WINDOW_HEIGHT);
        carController = new R_CarController(carView);
        timer         = new Timer(DELAY, event -> carController.update());
        timer.start();
    }

    public static void main(String[] args) {
        new VehicleSim();
    }
}
