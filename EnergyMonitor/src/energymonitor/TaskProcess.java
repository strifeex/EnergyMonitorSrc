
package energymonitor;

//import java.awt.TrayIcon;
import java.util.TimerTask;

public class TaskProcess extends TimerTask {
    
    private int times = 0;// นับวินาที ที่เมาส์ไม่ขยับ
    private int second = 60; // วินาที ที่จะทำการเก็บข้อมูล  // ค่าเริ่มต้นที่ 1 นาที
    private int[] mousePos = {0,0};  // ตำแหน่งของเคอเซอบนหน้าจอ  แกน x ,y
    private ManageDB mdb = new ManageDB(); 
    private String status = "";  // status สำหรับ ส่งไปยัง server  // start = เริ่มไม่มีการใช้งานเคอเซอร์   end = เริ่มมีการใช้งาน เคอเซออีกครั้ง
    private String tmp_MAC = "";  // เก็บ MAC Address เผื่อ เพิ่มข้อมูล
    private int tmp_id ; // เก็บ id เพี่อ อัพเดท ข้อมูล  เมื่อ มีการขยับเคอเซออีกครั้ง
    
    public void run() {
        // เชค ว่ามี Database 
        boolean chk_db = this.mdb.CheckDB(GetInfo.loadSeverIP(), "energymonitor");

        if (chk_db) {
            
            //query time from sever  for send data times
            // กำหนด ระยะเวลาที่ ไม่มีการขยับ เคอเซอร์  จาก  sever  กำหนดเป็นนาที
            // นำมาเปลี่ยนเป็นวินาที  
            //this.second = this.mdb.getDelay() != 0 ? this.mdb.getDelay()*60 : this.second ;
            // systemtray icon เป็นสีเขียว  เพื่อแสดงว่าสามารถติดต่อ server ได้
            ImageDisplay.setTrayImage("green-energy_icon.png");
            ImageDisplay.statusTrayImg = true;
            EnergyMonitor.trayIcon.setImage(ImageDisplay.getTrayImage());
            // เก็บตำแหน่ง x , y ของ cursor   เป็น array
            int[] mousecurent = GetInfo.getMousePosition();
            // เชคตำแหน่ง cursor อยู่ที่เดิม
            if ((this.mousePos[0] == mousecurent[0]) && (this.mousePos[1] == mousecurent[1])) {
                //นับเวลาเมื่ออยู่ที่เดิม
                times++;
                // cursor อยู่ที่เดิม เป็นเวลา ตามที่กำหนด  จะส่งข้อมูล ถือว่าสิ้นเปลือง
                if (times == this.second) {
                    //
                    status = "START";
                    this.tmp_MAC = GetInfo.getMAC();
                    this.mdb.insertData("insert into client_info(MAC,name,detail,status,lost_min,start_time)"
                            + " values('" + tmp_MAC + "',"
                            + "'"+GetInfo.name_txt+"',"
                            + "'"+GetInfo.detail_txt+"',"
                            + "'" + status + "',"
                            + "'" + (times / 60) + "',"
                            + "SUBDATE(NOW(),INTERVAL " + times + " SECOND)) ");

                    this.tmp_id = this.mdb.getCurrentid(tmp_MAC);

                    System.out.println("send data insert"+times + "S"+ "  tmp_id="+this.tmp_id );
                    
                }else if(times > this.second){
                    //update data
                    status = "END";
                    this.mdb.updateData("update client_info set status = '" + status + "' , "
                            + "name = '" + GetInfo.name_txt + "' , "
                            + "detail = '"+GetInfo.detail_txt+"' , "
                            + "lost_min = " + (times / 60) + " , "
                            + "start_time = SUBDATE(NOW(),INTERVAL " + times + " SECOND) "
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
            ImageDisplay.setTrayImage("green-energy-icon-discoon.png");
            ImageDisplay.statusTrayImg = false;
            //EnergyMonitor.trayIcon.displayMessage("connection", "not connect", TrayIcon.MessageType.ERROR);
            EnergyMonitor.trayIcon.setImage(ImageDisplay.getTrayImage());
            //Stop Timer.
            //this.cancel();
            
        }


    }
}
