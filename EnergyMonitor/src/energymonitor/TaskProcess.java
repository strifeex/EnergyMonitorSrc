
package energymonitor;

import java.util.TimerTask;

public class TaskProcess extends TimerTask {
    //times member represent calling times.
    private int times = 0;
    private int second = 10;
    private GetInfo in = new GetInfo();
    private int[] mousePos = {0,0};
    
    public void setSecond(int second){
        this.second = second;
    }
    
    public int getSecond(int second){
        return this.second ;
    }
    
    public void run() {
       
        int[] mousecurent = in.getMousePosition();

        if ((this.mousePos[0] == mousecurent[0]) && (this.mousePos[1] == mousecurent[1])) {
            
            times++;
            
            if (times == this.second) {
                System.out.println("send data");
            } else {
                
                System.out.println("processing time");
                //Stop Timer.
                //this.cancel();
            }
        }else{
            
            times = 0;
        }
        this.mousePos[0] = mousecurent[0];
        this.mousePos[1] = mousecurent[1];


    }
}
