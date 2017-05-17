package motor;

import java.awt.image.*;
import motor.grafika.BetoltottKep;

public class Render {

    private JatekMag jm;
    
    private int kepernyoX,kepernyoY;
    private int[] pixelek;
    
    private boolean racs = false;
    
    Render(JatekMag jm){
        this.jm = jm;
        kepernyoX = jm.getSzel();
        kepernyoY = jm.getMag();
        pixelek = ((DataBufferInt)this.jm.getAblak().getKep().getRaster().getDataBuffer()).getData();
        
    }
    
    public void setRacs(boolean b){
        racs = b;
    }
    
    public void setPixel(int x, int y, int ertek){
        if(x<0 || x >= kepernyoX || y<0 || y>kepernyoY || ertek == 0xffff00ff){
            return;
        }
        try{
            pixelek[x+y*kepernyoX] = ertek;
        }catch(Exception e){}
    }
    
    public void drawKep(int x, int y, BetoltottKep kep){
        for (int i = 0; i < kep.getSzel(); i++) {
            for (int j = 0; j < kep.getMag(); j++) {
                setPixel(i+x,j+y,kep.getPixelek()[i+j*kep.getSzel()]);
            }
        }
    }
    
    public void clear(){
        for (int i = 0; i < pixelek.length; i++) {
            pixelek[i] = 0;
        }
    }
}
