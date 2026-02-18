package Vehicle;
import java.awt.*;

public class Volvo240 extends Car {

    private final static double trimFactor = 1.25;    /** Factor used to adjust value of trim */

    /**
     * Constructor to initialize state of Volvo240.
     */
    public Volvo240(){
        super(4,100,Color.black,"Volvo240");
        stopEngine();
    }

    /**
     * Calculates the speedfactor based on trimfactor and enginepower.
     * @return The calculated speedfactor.
     */
    @Override
    double speedFactor(){
        return this.getEnginePower() * 0.01 * trimFactor;
    }

}
