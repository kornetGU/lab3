import Vehicle.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollisionHandler {
    private CarController controller;
    private TimerListener timerListener;

    public CollisionHandler(CarController controller){
        this.controller = controller;
        timerListener = new TimerListener();
    }

    public TimerListener getTimer(){
        return timerListener;
    }

    //car should bounce off the walls, turning 180 degrees
    private void collideWithWalls(double x , double y , Vehicle car){
        double imageWidth = controller.drawPanel.getImageWidth(car.getModelName());
        if (x+imageWidth >= CarView.getFrameX() || x < 0 || y+imageWidth >= CarView.getFrameY() || y < 0) {
            car.turnLeft();
            car.turnLeft();
        }
    }

    private void collideWithWorkshop(double x, double y, Vehicle car) {
        for (Workshop workshop : controller.getWorkshops()) {
            boolean nearWorkshop = Math.abs(x - workshop.getX()) <= 50 && Math.abs(y - workshop.getY()) <= 50;

            if (nearWorkshop && workshop.acceptsVehicle(car) ) {
                car.stopEngine();
                car.brake(1);
                workshop.addCar(car);
                controller.getCars().remove(car);
            }
        }
    }

    //--- Timer ---//
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<Vehicle> carsCopy = new ArrayList<>(controller.getCars());
            for (Vehicle car : carsCopy) {

                if (!car.canMove()) continue;

                controller.model.moveCars();
                double x = car.getX();
                double y = car.getY();

                collideWithWalls(x, y, car);
                collideWithWorkshop(x, y, car);
            }
        }
    }
}
