package Vehicle;
import java.awt.*;
import java.util.LinkedList;

class VehicleCarrier extends Truck {

    private LinkedList<Vehicle> cargo;
    private Vehicle unloadedVehicle;
    private static final int maxCargo = 6;
    private BinaryRamp binaryRamp;

    public VehicleCarrier() {
        super(2,600,Color.black,"Car carrier");
        cargo = new LinkedList<Vehicle>();
        this.binaryRamp = new BinaryRamp();
        this.ramp = binaryRamp;
    }

    /**
     * Lower connected ramp.
     * @throws IllegalStateException if moving
     * @throws IllegalArgumentException if already at minTilt
     */
    public void lowerRamp() {
       isStopped();
       binaryRamp.lowerRamp();
    }

    /**
     * Raise connected ramp.
     * @throws IllegalStateException if moving
     * @throws IllegalArgumentException if already at maxTilt
     */
    public void raiseRamp() {
        isStopped();
        binaryRamp.raiseRamp();
    }

    /**
     * Load an appropriate vehicle to the cargo.
     * @param vehicle to load.
     * @throws IllegalArgumentException if vehicle is not loadable or cargo is full.
     * @throws IllegalStateException if ramp is up.
     * @throws IllegalArgumentException if car is too far away.
     */
    void loadVehicle(Vehicle vehicle) {
        if (!vehicle.isLoadable() || maxCargo <= cargo.size()) {
            throw new IllegalArgumentException("Cannot load vehicle");
        }
        if (ramp.getCurrentTilt() == 0) throw new IllegalStateException("Ramp is up.");
        if (Math.abs(this.x - vehicle.x) > 1 || Math.abs(this.y - vehicle.y) > 1) throw new IllegalArgumentException("Car is too far away.");
        cargo.addLast(vehicle);
    }

    /**
     * Unloads the last vehicle in from cargo.
     * @throws IndexOutOfBoundsException if cargo is already empty.
     */
    private void unloadVehicle() {
        if (cargo.isEmpty()) throw new IndexOutOfBoundsException("The cargo is already empty.");
        unloadedVehicle = cargo.removeLast();
        unloadedVehicle.x = this.x + 1;
        unloadedVehicle.y = this.y;
    }

    /**
     * Moves and updates the coordinates of VehicleCarrier and all cars in cargo.
     * @throws IllegalStateException if ramp is up.
     */
    @Override
    public void move() {
        super.move();
        for(Vehicle vehicle : cargo) {
            vehicle.x = this.x;
            vehicle.y = this.y;
        }
    }
}
