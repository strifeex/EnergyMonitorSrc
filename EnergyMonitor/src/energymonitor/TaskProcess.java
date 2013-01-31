
package energymonitor;

import java.awt.Component;
import java.awt.TrayIcon;
import java.util.TimerTask;
import javax.swing.JOptionPane;

public class TaskProcess extends TimerTask {
    
    private int times = 0;// นับวินาที ที่เมาส์ไม่ขยับ
    private int second = 60; // วินาที ที่จะทำการเก็บข้อมูล  // ค่าเริ่มต้นที่ 1 นาที
    private int[] mousePos = {0,0};  // ตำแหน่งของเคอเซอบนหน้าจอ  แกน x ,y
    private ManageDB mdb = new ManageDB(); 
    private String status = "";  // status สำหรับ ส่งไปยัง server  // start = เริ่มไม่มีการใช้งานเคอเซอร์   end = เริ่มมีการใช้งาน เคอเซออีกครั้ง
    private String tmp_MAC = "";  // เก็บ MAC Address เผื่อ เพิ่มข้อมูล
    private int tmp_id ; // เก็บ id เพี่อ อัพเดท ข้อมูล  เมื่อ มีการขยับเคอเซออีกครั้ง
    private int tmp_id_ini;
    private boolean chk_ini = true;
    private int second_ini = 0;
    public void run() {
        // เชค ว่ามี Database 
        boolean chk_db = this.mdb.CheckDB(GetInfo.loadSeverIP(), "energymonitor");
        
        //JOptionPane.showConfirmDialog((Component) null, "testTask", "alert", JOptionPane.DEFAULT_OPTION);

        if (chk_db) {
            
            //query time from sever  for send data times
            // กำหนด ระยะเวลาที่ ไม่มีการขยับ เคอเซอร์  จาก  sever  กำหนดเป็นนาที
            // นำมาเปลี่ยนเป็นวินาที  
            this.second = this.mdb.getDelay() != 0 ? this.mdb.getDelay()*60 : this.second ;
            // systemtray icon เป็นสีเขียว  เพื่อแสดงว่าสามารถติดต่อ server ได้
            //EnergyMonitor.class.getResource("green-energy_icon.png");
            ImageDisplay.setTrayImage("/img/green-energy_icon.png");
            ImageDisplay.statusTrayImg = true;
            EnergyMonitor.trayIcon.setImage(ImageDisplay.getTrayImage());
            // เก็บตำแหน่ง x , y ของ cursor  
            int[] mousecurent = GetInfo.getMousePosition();
            this.tmp_MAC = GetInfo.getMAC();
            String tmp_watt = Double.parseDouble(GetInfo.watt) > 0 ? GetInfo.watt + "" : "0";

            if (tmp_watt.equals("0")) {

                tmp_watt = GetInfo.watt_type.equals("0") ? "300" : "150";

            }
            
            //เก็บระยะเวลาตั้งแต่เปิดเครื่อง ทุก 1 นาที 
         
            if(this.second_ini == 60){
                if (this.chk_ini) {
                    this.mdb.insertData("insert into client_online(MAC,name,detail,online_min,start_time)"
                            + " values('" + tmp_MAC + "',"
                            + "'" + GetInfo.name_txt + "',"
                            + "'" + GetInfo.detail_txt + "',"
                            + "'" + 1 + "',"
                            + "SUBDATE(NOW(),INTERVAL " + 60 + " SECOND) )");

                    this.tmp_id_ini = this.mdb.getCurrentid(tmp_MAC, "client_online");
                    this.chk_ini = false;
                } else {
                    System.out.println("up online");
                    this.mdb.updateData("update client_online set "
                            + "name = '" + GetInfo.name_txt + "' , "
                            + "detail = '" + GetInfo.detail_txt + "' , "
                            + "online_min = online_min +" + 1 + " ,"
                            + "start_time = SUBDATE(NOW(),INTERVAL (online_min*60)+60 SECOND) "
                            + "where Id = " + this.tmp_id_ini + "");
                }
                this.second_ini = 0;
            }
            this.second_ini++;
            // เชคตำแหน่ง cursor อยู่ที่เดิม
            if ((this.mousePos[0] == mousecurent[0]) && (this.mousePos[1] == mousecurent[1])) {
                //นับเวลาเมื่ออยู่ที่เดิม
                times++;
                // cursor อยู่ที่เดิม เป็นเวลา ตามที่กำหนด  จะส่งข้อมูล ถือว่าสิ้นเปลือง
                if (times == this.second) {
                    //
                    status = "START";
                    
                    this.mdb.insertData("insert into client_info(MAC,name,detail,status,lost_min,cost_unit,watt,start_time)"
                            + " values('" + tmp_MAC + "',"
                            + "'"+GetInfo.name_txt+"',"
                            + "'"+GetInfo.detail_txt+"',"
                            + "'" + status + "',"
                            + "'" + (times / 60) + "',"
                            + "'"+this.mdb.getCost_unit()+"',"
                            + "'"+tmp_watt+"',"
                            + "SUBDATE(NOW(),INTERVAL " + times + " SECOND) )");

                    this.tmp_id = this.mdb.getCurrentid(tmp_MAC,"client_info");

                    System.out.println("send data insert"+times + "S"+ "  tmp_id="+this.tmp_id );
                    
                }else if(times > this.second){
                    //update data
                    status = "END";
                    this.mdb.updateData("update client_info set status = '" + status + "' , "
                            + "name = '" + GetInfo.name_txt + "' , "
                            + "detail = '"+GetInfo.detail_txt+"' , "
                            + "lost_min = " + (times / 60) + " ,"
                            + "watt = " +tmp_watt+" ,"
                            + "start_time = SUBDATE(NOW(),INTERVAL " + times + " SECOND) ,"
                            + "cost_unit = " + this.mdb.getCost_unit() + " "
                            + "where Id = " + this.tmp_id + "");

                    this.tmp_MAC = "";
                    System.out.println("send data update"+times + "S" + "  tmp_id="+this.tmp_id );
                    
                } else {
                    System.out.println("processing time"+times + "S"+ "  tmp_id="+this.tmp_id );
                
                }
            } else {
                times = 0;
            }
            
            this.mousePos[0] = mousecurent[0];
            this.mousePos[1] = mousecurent[1];
            
        }else{

            ImageDisplay.setTrayImage("/img/green-energy-icon-discoon.png");
            ImageDisplay.statusTrayImg = false;
            //EnergyMonitor.trayIcon.displayMessage("connection", "not connect", TrayIcon.MessageType.ERROR);
            EnergyMonitor.trayIcon.setImage(ImageDisplay.getTrayImage());
            //Stop Timer.
            //this.cancel();
            
        }


    }
}
