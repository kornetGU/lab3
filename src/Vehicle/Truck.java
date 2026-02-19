package Vehicle;
import java.awt.*;

public abstract class Truck extends Vehicle {
    public Ramp ramp;

    public Truck(int nrDoors, double enginePower, Color color, String modelName){
        super(nrDoors,enginePower,color,modelName);
    }

    /**
     * Moves the truck.
     * @throws IllegalStateException if ramp is up.
     */
    @Override
    public void move() {
        if (ramp.getCurrentTilt() != 0 && ramp != null) throw new IllegalStateException("Cannot move while ramp is down.");
        super.move();
    }

    /**
     * Sets 'Truck object' as not permitted to be loaded in a VehicleCarrier.
     * @return false
     */
    @Override
    public boolean isLoadable() {
        return false;
    }

    /**
     *
     * @return speedFactor
     */
    @Override
    double speedFactor() {
        return this.getEnginePower() * 0.01;
    }

    /**
     * Returns true if car is not moving
     */
    void isStopped() {
        if (!(this.getCurrentSpeed() == 0)) throw new IllegalStateException("Vehicle is moving.");
    }
}

class BinaryRamp implements Ramp{
    private final int maxTilt = 1; // ramp is down, loadable
    private final int minTilt = 0; // ramp is up, not loadable
    private int currentTilt = minTilt;

    /**
     * Raise ramp.
     * @throws IllegalArgumentException if already at maxTilt.
     */
    @Override
    public void raiseRamp() {
        if (currentTilt <= minTilt) throw new IllegalArgumentException("Already at min tilt.");
        currentTilt = 0;
    }

    /**
     * Lower ramp.
     * @throws IllegalArgumentException if already at minTilt.
     */
    @Override
    public void lowerRamp() {
        if (currentTilt >= maxTilt) throw new IllegalArgumentException("Already at max tilt.");
        currentTilt = 1;
    }

    /**
     * Getter method for current tilt.
     * @return current tilt.
     */
    @Override
    public int getCurrentTilt() {
        return currentTilt;
    }
}




