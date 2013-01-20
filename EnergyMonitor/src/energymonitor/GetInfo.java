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

    static String name_client ;
    static String ip_client;
    static String MAC_client ;
    static InetAddress ia;
    static String server_ip = "Please input ServerIP";
    static String name_txt = "";
    static String detail_txt = "";
    static String watt_type = "";
    static String watt = "";
    static String filesave_name = "ipconnect.in";
    
    static String loadSeverIP(){
        try{
            Scanner sc = new Scanner(new File(filesave_name));
            GetInfo.server_ip = sc.nextLine();
            GetInfo.name_txt = sc.nextLine();
            GetInfo.detail_txt = sc.nextLine();
            GetInfo.watt_type = sc.nextLine();
            GetInfo.watt = sc.nextLine();
        }
        catch(Exception ex){
            //JOptionPane.showConfirmDialog((Component) null, "loadSeverIP = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
            File file = new File(filesave_name);
            
        }
        return GetInfo.server_ip;
    }
    
    static void saveServerIP(String iptext,String name ,String details,String watttype,String watt){
        try{
            FileWriter fw = new FileWriter(filesave_name);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(iptext);
            out.write("\n"+name);
            out.write("\n"+details);
            out.write("\n"+watttype);
            out.write("\n"+watt);
            out.close(); 
        }
        catch(Exception ex){
            JOptionPane.showConfirmDialog((Component) null, "saveServerIP = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
        }
    }
    
    static String getClientName() {

        GetInfo.name_client = ia.getHostName();
        return GetInfo.name_client;
    }

    static String getIP() {

        GetInfo.ip_client = ia.getHostAddress();
        return GetInfo.ip_client;
    }

    static String getMAC() {

        try {
            GetInfo.ia = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ia);
            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i],
                        (i < mac.length - 1) ? "-" : ""));
            }
            GetInfo.MAC_client = sb.toString();
            
        } catch (UnknownHostException | SocketException e) {

            JOptionPane.showConfirmDialog((Component) null, "getMAC = "+e.toString(), "alert", JOptionPane.DEFAULT_OPTION);
            GetInfo.MAC_client = e.toString();

        }

        GetInfo.MAC_client = GetInfo.MAC_client.equals("") ? " not connect network " : GetInfo.MAC_client ;
        
        return GetInfo.MAC_client;
    }
    
    static int[] getMousePosition(){
        int [] position = new int[2];
        position[0] =  Integer.parseInt(MouseInfo.getPointerInfo().getLocation().x+"");
        position[1] =  Integer.parseInt(MouseInfo.getPointerInfo().getLocation().y+"");
        
        return position;
    }
    
}
