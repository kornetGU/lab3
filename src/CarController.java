import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Vehicle.*;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:
    Volvo240 volvo ;
    Scania scania ;
    Saab95 saab ;
    Workshop<Volvo240> workshop;

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars;

    //methods:

    public CarController() {
        volvo = new Volvo240();
        scania = new Scania();
        saab = new Saab95();
        cars = new ArrayList<>();

        workshop = new Workshop<Volvo240>(1,"VolvoWorkshop");

        cars.add(scania);
        cars.add(saab);
        cars.add(volvo);
    }

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
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

    void lowerRamp() {
        scania.lowerRamp();
    }

    void raiseRamp() {
        scania.raiseRamp();
    }

    void turboOn() {
        saab.setTurboOn();
    }

    void turboOff() {
        saab.setTurboOff();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < cars.size(); i++) {
                Vehicle car = cars.get(i);

                // om bilen är en scania och rampen är lyft, ska den inte kunna köra
                if (car instanceof Scania) {
                    Scania scan = (Scania) car;
                    if (scan.stepRamp.getCurrentTilt() != 0) continue;
                }

                car.move();

                //set carPoint to car's current coordinates
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                //car should bounce off the walls, turning 180 degrees
                if (x+100 >= 800 || x < 0 || y+100 >= 800 || y < 0) {
                    car.turnLeft();
                    car.turnLeft();
                }

                //load volvo
                if (car instanceof Volvo240) {
                    if (car.x >= 300) {
                        car.stopEngine();
                        car.brake(1);
                        workshop.addCar((Volvo240) car);
                        cars.remove(car);
                    }
                }



                frame.drawPanel.moveit(i, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }
}
