package Vehicle;
import java.awt.*;

public class Scania extends Truck {
    private StepRamp stepRamp;

    public Scania() {
        super(2,  500, Color.gray, "Scania");
        this.stepRamp = new StepRamp(70,5);
        this.ramp = stepRamp;
    }

    /**
     * Lower connected ramp.
     * @throws IllegalStateException if moving
     * @throws IllegalArgumentException if already at minTilt
     */
    public void lowerRamp() {
        isStopped();
        stepRamp.lowerRamp();
    }

    /**
     * Raise connected ramp.
     * @throws IllegalStateException if moving.
     * @throws IllegalArgumentException if already at maxTilt.
     */
    public void raiseRamp() {
        isStopped();
        stepRamp.raiseRamp();
    }
}
