package Vehicle;
import java.util.ArrayList;

public class Workshop<X extends Vehicle> {      // Ensure type parameter X must be Vehicle
    private int maxCapacity;
    private String name;
    private ArrayList<X> currentVehicles;
    private X returnedVehicle;

    /**
     * Constructs instance of a workshop
     * @param maxCapacity - workshop max capacity
     * @param name - workshop name
     */
    public Workshop(int maxCapacity, String name){
        this.maxCapacity = maxCapacity;
        this.name = name;
        currentVehicles = new ArrayList<X>();
    }
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
        returnedVehicle = currentVehicles.remove(index);
        System.out.println("Returned vehicle: " + returnedVehicle.getColor());
        return returnedVehicle;
    }
}
