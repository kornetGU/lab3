package Vehicle;

import java.awt.*;

abstract public class Car extends Vehicle {
    public Car(int nrDoors, int enginePower,Color color,String modelName){
        super(nrDoors,enginePower,color,modelName);
    }

    /**
     * Car is a loadable vehicle.
     * @return true
     */
    @Override
    public boolean isLoadable() {
        return true;
    }
}
