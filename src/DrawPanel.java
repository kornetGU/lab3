import Vehicle.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.
public class DrawPanel extends JPanel implements ModelObserver{

    private Map<String, BufferedImage> imageRegistry = new HashMap<>();

    private Map<String,Point> carInfo = new HashMap<>();
    private Map<String,Point> workshopInfo = new HashMap<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        addToImageRegistry();
    }

    /**
     * Populate the map of modelnames with corresponding image.
     */
    private void addToImageRegistry(){
        try {
            imageRegistry.put("Saab95",         ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            imageRegistry.put("Volvo240",        ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            imageRegistry.put("Scania",       ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
            imageRegistry.put("VolvoWorkshop",ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getImageWidth(String vehicleType) {
        return(imageRegistry.get(vehicleType).getWidth());
    }

    @Override
    public void updateCars(Map<String, Point> carInfo) {
        System.out.println("updateCars called with: " + carInfo); // debug
        this.carInfo = carInfo;
        repaint();
    }

    @Override
    public void updateWorkshops(Map<String, Point> workshopInfo) {
        this.workshopInfo = workshopInfo;
        repaint();
    }

    private void paintThings(Graphics g, Map<String, Point> things) {
        for (Map.Entry<String, Point> entry : things.entrySet()) {
            String key = entry.getKey();
            int separatorIndex = key.indexOf("_");
            String imageName = separatorIndex >= 0 ? key.substring(separatorIndex + 1) : key;
            BufferedImage image = imageRegistry.get(imageName);
            if (image != null)
                g.drawImage(image, entry.getValue().x, entry.getValue().y, null);
        }
    }


    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintThings(g,carInfo);
        paintThings(g,workshopInfo);
    }
}
