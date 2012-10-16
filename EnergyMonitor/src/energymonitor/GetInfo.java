package energymonitor;

import java.awt.Component;
import java.awt.MouseInfo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GetInfo {

    private String name_client;
    private String ip_client;
    private String MAC_client;
    private InetAddress ia;
    private String server_ip = "";
    
    public String loadSeverIP(){
        try{
            Scanner sc = new Scanner(new File("ipconnect.in"));
            this.server_ip = sc.nextLine();
        }
        catch(Exception ex){
            File file = new File("ipconnect.in");
        }
        return this.server_ip;
    }
    
    public void saveServerIP(String iptext){
        try{
            FileWriter fw = new FileWriter("ipconnect.in");
            BufferedWriter out = new BufferedWriter(fw);
            out.write(iptext);
            out.close(); 
        }
        catch(Exception ex){
             JOptionPane.showConfirmDialog((Component)
                null, ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
        }
    }
    
    public String getClientName() {

        this.name_client = ia.getHostName();
        return this.name_client;
    }

    public String getIP() {

        this.ip_client = ia.getHostAddress();
        return this.ip_client;
    }

    public String getMAC() {

        try {
            this.ia = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ia);
            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i],
                        (i < mac.length - 1) ? "-" : ""));
            }
            this.MAC_client = sb.toString();
            
        } catch (UnknownHostException | SocketException e) {

            this.MAC_client = e.toString();

        }

        return this.MAC_client;
    }
    
    public int[] getMousePosition(){
        int [] position = new int[2];
        position[0] =  Integer.parseInt(MouseInfo.getPointerInfo().getLocation().x+"");
        position[1] =  Integer.parseInt(MouseInfo.getPointerInfo().getLocation().y+"");
        
        return position;
    }
    
}
