
package energymonitor;

import java.util.TimerTask;

public class TaskProcess extends TimerTask {
    
    private int times = 0;// นับวินาที ที่เมาส์ไม่ขยับ
    private int second = 10; // วินาที ที่จะทำการเก็บข้อมูล
    private int[] mousePos = {0,0};  // ตำแหน่งของเคอเซอบนหน้าจอ  แกน x ,y
    private ManageDB mdb = new ManageDB(); 
    
    public void setSecond(int second){
        this.second = second;
    }
    
    public int getSecond(int second){
        return this.second ;
    }
    
    public void run() {
        
        boolean chk_db = this.mdb.CheckDB(GetInfo.loadSeverIP(), "energymonitor");

        if (chk_db) {

            ImageDisplay.setTrayImage("green-energy_icon.png");
            EnergyMonitor.trayIcon.setImage(ImageDisplay.getTrayImage());
            int[] mousecurent = GetInfo.getMousePosition();

            if ((this.mousePos[0] == mousecurent[0]) && (this.mousePos[1] == mousecurent[1])) {

                times++;

                if (times == this.second) {
                    System.out.println("send data");
                } else {

                    System.out.println("processing time");
                    this.mdb.insertData("insert into client_info(MAC,name,detail,status) values('mactest','nametest','detailtest','START') ");


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
