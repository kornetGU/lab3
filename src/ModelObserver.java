import java.awt.*;
import java.util.Map;

public interface ModelObserver {
    void updateCars(Map<String,Point> carInfo);
    void updateWorkshops(Map<String,Point> workshopInfo);
}
