package Vehicle;

public class StepRamp implements Ramp {
    private int maxTilt;
    private int minTilt;
    private int tiltStep;
    public int currentTilt = minTilt;

    public StepRamp(int maxTilt, int tiltStep){
        this.maxTilt = maxTilt;
        this.tiltStep = tiltStep;
    }

    /**
     * Raise ramp.
     * @throws IllegalArgumentException if already at maxTilt.
     */
    @Override
    public void raiseRamp() {
        if (currentTilt >= maxTilt) throw new IllegalArgumentException("Already at max tilt.");
        currentTilt = Math.min(maxTilt,(currentTilt + tiltStep));
    }

    /**
     * Lower ramp.
     * @throws IllegalArgumentException if already at minTilt.
     */
    @Override
    public void lowerRamp() {
        if (currentTilt <= minTilt) throw new IllegalArgumentException("Already at min tilt.");
        currentTilt = Math.max(minTilt, (currentTilt - tiltStep));
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