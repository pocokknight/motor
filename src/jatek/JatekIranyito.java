package jatek;

import jatek.Karakterek.Player;
import jatek.res.anim.AnimJelolo;
import jatek.res.font.FontJelolo;
import jatek.res.img.ImgJelolo;
import motor.AbsztraktJatek;
import motor.JatekMag;
import motor.Render;
import motor.fizika.Karakter;
import motor.fizika.Vilag;
import motor.grafika.*;

public class JatekIranyito extends AbsztraktJatek{

    BetoltottAnim anim;
    BetoltottKep kep,cursor;
    Szoveg szoveg;
    Vilag alap;
    Player jatekos;
    
    public JatekIranyito() {
        kep = new BetoltottKep(ImgJelolo.class, "teszt.png");
        cursor = new BetoltottKep(ImgJelolo.class, "cursor.png");
        szoveg = new Szoveg("char_table.png");
        alap = new Vilag(15,100,50,false);
        alap.general();
        jatekos = new Player(0, 10, 29, 0x99123456, 200, 50);
        alap.addKarakter(jatekos);
    }
    
    @Override
    public void frissit(JatekMag jm, double dt) {
        alap.frissit(jm,dt);
    }

    @Override
    public void render(JatekMag jm, Render r) {
        
        alap.render(jm,r);
        //r.drawRect(0,0,25,25,0xff663300);
        r.drawSzoveg(szoveg,"FPS: "+jm.getFps(),0xffffffff,10,10,2,2);
        r.drawSzoveg(szoveg,"Alpha build 0.1 (Press ESC to escape)",0xffffffff,600,10,2,2);
        //r.drawSzoveg(szoveg,"qwertzuiopasdfghjklyxcvbnm-QWERTZUIOPASDFGHJKLYXCVBNM",0xff00ff00,20,420,2,2);
        r.drawKep(jm.getBevitel().getEgerPozX(), jm.getBevitel().getEgerPozY(), cursor.getPixelek(),cursor.getSzel(),1,1);
    }
    
    public static void main(String args[]){
        JatekMag jm = new JatekMag(new JatekIranyito());
        jm.start();
    }

}
