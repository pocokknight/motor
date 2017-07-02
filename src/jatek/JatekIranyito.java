package jatek;

import jatek.res.anim.AnimJelolo;
import jatek.res.font.FontJelolo;
import jatek.res.img.ImgJelolo;
import motor.AbsztraktJatek;
import motor.JatekMag;
import motor.Render;
import motor.fizika.Vilag;
import motor.grafika.*;

public class JatekIranyito extends AbsztraktJatek{

    BetoltottAnim anim;
    BetoltottKep kep,cursor;
    Szoveg szoveg;
    Vilag alap;
    
    public JatekIranyito() {
        kep = new BetoltottKep(ImgJelolo.class, "teszt.png");
        cursor = new BetoltottKep(ImgJelolo.class, "cursor.png");
        szoveg = new Szoveg("char_table.png");
        alap = new Vilag(25,100,50,false);
        alap.general();
    }
    
    @Override
    public void frissit(JatekMag jm, double dt) {
        
    }

    @Override
    public void render(JatekMag jm, Render r) {
        
        alap.render(jm,r);
        //r.drawRect(0,0,25,25,0xff663300);
        r.drawSzoveg(szoveg,"fps: "+jm.getFps(),0xffffffff,10,10,2,2);
        //r.drawSzoveg(szoveg,"qwertzuiopasdfghjklyxcvbnm-QWERTZUIOPASDFGHJKLYXCVBNM",0xff00ff00,20,420,2,2);
        r.drawKep(jm.getBevitel().getEgerPozX(), jm.getBevitel().getEgerPozY(), cursor.getPixelek(),cursor.getSzel(),1,1);
    }
    
    public static void main(String args[]){
        JatekMag jm = new JatekMag(new JatekIranyito());
        jm.start();
    }

}
