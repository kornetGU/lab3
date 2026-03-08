package Vehicle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Workshop<X extends Vehicle> {      // Ensure type parameter X must be Vehicle
    private int maxCapacity;
    private String name;
    private ArrayList<X> currentVehicles;
    private double x;
    private double y;
    private BufferedImage image;
    private Class<X> vehicleType;

    /**
     * Constructs instance of a workshop
     * @param maxCapacity - workshop max capacity
     * @param name - workshop name
     */
    public Workshop(int maxCapacity, String name, double x, double y,Class<X> vehicleType){
        this.maxCapacity = maxCapacity;
        this.name = name;
        currentVehicles = new ArrayList<X>();
        this.x = x;
        this.y = y;
        this.vehicleType = vehicleType;
    }


    /**
     * @return
     */
    int nrOfCars() {
        return currentVehicles.size();
    }

    /**
     * Adds car to workshop if there is space for it
     * @param vehicle to add to workshop
     */
    public void addCar(X vehicle){
        if(currentVehicles.size() >= maxCapacity) {
            throw new IllegalArgumentException("No more space");
        }
        currentVehicles.add(vehicle);
    }

    /**
     * Method to return car from workshop
     * @param index - return car from specified index
     * @return - returned car from workshop
     */
    X removeVehicle(int index) {
        X returnedVehicle = currentVehicles.remove(index);
        System.out.println("Returned vehicle: " + returnedVehicle.getModelName());
        return returnedVehicle;
    }

    public String getName() {return this.name;}

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public BufferedImage getImage(){return image;}

    public Class getAcceptedVehicleType(){return vehicleType;}

    public boolean acceptsVehicle(Vehicle vehicle){return vehicleType.isInstance(vehicle);}
}
