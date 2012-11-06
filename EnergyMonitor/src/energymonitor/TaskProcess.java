
package energymonitor;

import java.util.TimerTask;

public class TaskProcess extends TimerTask {
    
    private int times = 0;// นับวินาที ที่เมาส์ไม่ขยับ
    private int second = 60; // วินาที ที่จะทำการเก็บข้อมูล
    private int[] mousePos = {0,0};  // ตำแหน่งของเคอเซอบนหน้าจอ  แกน x ,y
    private ManageDB mdb = new ManageDB(); 
    private String status = "END";  // status สำหรับ ส่งไปยัง server
    private String tmp_MAC = "";
    private int tmp_id = 0;
    public void setSecond(int second){
        this.second = second;
    }
    
    public int getSecond(int second){
        return this.second ;
    }
    
    public void run() {
        
        boolean chk_db = this.mdb.CheckDB(GetInfo.loadSeverIP(), "energymonitor");

        if (chk_db) {
            
            //query time from sever  for send data times
            this.second = this.mdb.getDelay() != 0 ? this.mdb.getDelay()*60 : this.second ;
            
            ImageDisplay.setTrayImage("green-energy_icon.png");
            EnergyMonitor.trayIcon.setImage(ImageDisplay.getTrayImage());
            int[] mousecurent = GetInfo.getMousePosition();

            if ((this.mousePos[0] == mousecurent[0]) && (this.mousePos[1] == mousecurent[1])) {

                times++;

                if (times == this.second) {
                    System.out.println("send data");
                    status = status.equals("END") ? "START" : "END" ;
                    
                    if(tmp_MAC.isEmpty()){
                        tmp_MAC = GetInfo.getMAC();
                        this.mdb.insertData("insert into client_info(MAC,name,detail,status,lost_min,start_time)"
                                + " values('"+tmp_MAC+"',"
                                + "'nametest',"
                                + "'detailtest',"
                                + "'"+status+"',"
                                + "'"+(times/60)+"',"
                                + "SUBDATE(NOW(),INTERVAL "+times+" SECOND)) ");
                        
                        this.tmp_id = this.mdb.getCurrentid(tmp_MAC);
                    
                    }else{
                        //todo
                        //update data
                        this.mdb.insertData("update client_info set status = '"+status+"' , ");
                    }
                    
                    times = 0 ;
                    
                } else {
                    System.out.println("processing time");
                
                }
            } else {

                times = 0;
            }
            this.mousePos[0] = mousecurent[0];
            this.mousePos[1] = mousecurent[1];
            
        }else{
            ImageDisplay.setTrayImage("green-energy-icon-discoon.png");
            EnergyMonitor.trayIcon.setImage(ImageDisplay.getTrayImage());
            //Stop Timer.
            //this.cancel();
            
        }


    }
}
