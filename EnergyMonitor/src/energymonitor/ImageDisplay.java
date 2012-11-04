
package energymonitor;

import java.awt.Image;
import java.awt.Toolkit;

public class ImageDisplay {
    static Image TrayImage = Toolkit.getDefaultToolkit().getImage("green-energy_icon.png");
    
    static Image getTrayImage(){
        return ImageDisplay.TrayImage;
    }
    
    static void setTrayImage(String imgpath){
        ImageDisplay.TrayImage = Toolkit.getDefaultToolkit().getImage(imgpath);
    }
}
