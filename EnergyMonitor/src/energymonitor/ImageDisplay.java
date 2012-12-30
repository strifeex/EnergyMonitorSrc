
package energymonitor;

import java.awt.Image;
import java.awt.Toolkit;

public class ImageDisplay {
    
    static java.net.URL imgUrl = EnergyMonitor.class.getResource("/img/green-energy_icon.png");
    static Image TrayImage = Toolkit.getDefaultToolkit().getImage(imgUrl);
    static boolean statusTrayImg = true;
    static Image getTrayImage(){
        return ImageDisplay.TrayImage;
    }
    
    static void setTrayImage(String imgpath){
        ImageDisplay.imgUrl = EnergyMonitor.class.getResource(imgpath);
        ImageDisplay.TrayImage = Toolkit.getDefaultToolkit().getImage(imgUrl);
    }
}
