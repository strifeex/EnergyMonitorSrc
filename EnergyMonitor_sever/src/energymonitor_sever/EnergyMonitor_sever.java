
package energymonitor_sever;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class EnergyMonitor_sever {

    public static void setNimbusUI(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog((Component) null, ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
        }
    }
    
    public static void main(String[] args) {
        setNimbusUI();
        reportForm rp_form = new reportForm();
        rp_form.setVisible(true);
    }
}
