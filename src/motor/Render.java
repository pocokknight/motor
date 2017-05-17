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
        if(x<0 || x >= kepernyoX || y<0 || y>=kepernyoY || ertek == 0xffff00ff){
            return;
        }
        pixelek[x+y*kepernyoX] = ertek;
    }
    
    public void drawKep(int x, int y, BetoltottKep kep){
        
        int ujx = 0,ujy = 0;
        int ujszel = kep.getSzel(),ujmag = kep.getMag();
        
        //nincs kirajzolás
        if(x < -ujszel) return;
        if(y < -ujmag) return;
        if(x >= kepernyoX) return;
        if(y >= kepernyoY) return;
        
        //elvágott kód
        if(x < 0) ujx -= x;
        if(y < 0) ujy -= y;
        if(ujszel + x >= kepernyoX) ujszel -= ujszel+x-kepernyoX;
        if(ujmag + y >= kepernyoY) ujmag -= ujmag+y-kepernyoY;
        
        for (int i = ujx; i < ujszel; i++) {
            for (int j = ujy; j < ujmag; j++) {
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
