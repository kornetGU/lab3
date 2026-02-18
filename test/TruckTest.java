import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TruckTest {
    Scania s;
    VehicleCarrier vc;

    Saab95 saab;
    Volvo240 volvo;
    private Workshop<Saab95> workshop;

    @BeforeEach
    public void init(){
        saab = new Saab95();
        volvo = new Volvo240();

        s = new Scania();
        s.startEngine();

        vc = new VehicleCarrier();
        vc.lowerRamp();
        vc.loadVehicle(saab);
        vc.raiseRamp();
        vc.startEngine();

        workshop = new Workshop<Saab95>(1, "testShop");
    }

    // =============== VEHICLE CARRIER ==================
    // ==================================================

    void printCoordinates() {
        System.out.println("saab x " + saab.x + " y " + saab.y);
        System.out.println("vc x " + vc.x + " y " + vc.y);
    }

    @Test
    public void testSameCoordinatesVehicleCarrierCargo(){
        printCoordinates();
        vc.move();
        printCoordinates();
        assertTrue(vc.x == saab.x && vc.y == saab.y);
    }

    @Test
    public void scaniaIsNonLoadable() {
        assertThrows(IllegalArgumentException.class, () -> {
            vc.loadVehicle(s);
        });
    }

    // ================== SCANIA ========================
    // ==================================================

    @Test
    public void moveWithTiltedRamp() {
        s.stopEngine();
        s.raiseRamp();
        assertThrows(IllegalStateException.class, () -> {
            s.move();
        });
    }

    @Test
    public void testRaiseStopped() {
        s.stopEngine();
        s.raiseRamp();

    }

    @Test
    public void testLowerMoving() {
        assertThrows(IllegalStateException.class, () -> {
            s.lowerRamp();
        });
    }

    // =============== WORKSHOP TESTS ===================
    // ==================================================

    @Test
    public void addPermittedCarToEmptyWorkshop() {
        workshop.addCar(saab);
        assertTrue(workshop.nrOfCars() == 1);
    }

  /*  @Test
    public void addNonPermittedCarToEmptyWorkshop() {
        assertThrows(IllegalArgumentException.class, () -> {
            workshop.addCar(saab);
        });
    } */
    
    @Test
    public void addPermittedCarToFullWorkshop() {
        workshop.addCar(saab);
        assertThrows(IllegalArgumentException.class, () -> {
            workshop.addCar(saab);
        });
    }

    @Test
    public void removeCarFromWorkshop() {
        workshop.addCar(saab);
        workshop.removeVehicle(0);
        assertEquals(0, workshop.nrOfCars());
    }
}
