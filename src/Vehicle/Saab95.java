package Vehicle;
import java.awt.*;

public class Saab95 extends Car implements Turbo {

    private boolean turboOn;    /** Indicates whether turbo is active */

    /**
     * Constructor to initialize the state of the Saab95.
     */
    public Saab95(){
        super(2,125,Color.red,"Saab95");
        turboOn = false;
        stopEngine();
    }

    /**
     * Sets turboOn to 'true'.
     */
    @Override
    public void setTurboOn(){
	    turboOn = true;
    }

    /**
     * Sets turboOn to 'false'.
     */
    @Override
    public void setTurboOff(){
	    turboOn = false;
    }

    /**
     * Calculates the speedfactor based on turbo and enginepower.
     * @return The calculated speedfactor.
     */
    @Override
    double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return this.getEnginePower() * 0.01 * turbo;
    }
}
