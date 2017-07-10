package jatek;

import jatek.Karakterek.Player;
import jatek.res.anim.AnimJelolo;
import jatek.res.font.FontJelolo;
import jatek.res.img.ImgJelolo;
import motor.AbsztraktJatek;
import motor.JatekMag;
import motor.Render;
import motor.fizika.Block;
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
        jatekos = new Player(0, 10, 28, 0x99123456, 0, 0);
        alap.addKarakter(jatekos);
    }
    
    @Override
    public void frissit(JatekMag jm, double dt) {
        alap.frissit(jm,dt);
    }

    @Override
    public void render(JatekMag jm, Render r) {
        alap.render(jm,r);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                r.setFenyTerkep(i+jm.getBevitel().getEgerPozX(), j+jm.getBevitel().getEgerPozY(), 0x00ffffff);
            }
        }
        for (int i = 150; i < 250; i++) {
            for (int j = 100; j < 200; j++) {
                r.setFenyTerkep(i, j, 0x00ff0000);
            }
        }
        for (int i = 100; i < 200; i++) {
            for (int j = 100; j < 200; j++) {
                r.setFenyTerkep(i, j, 0x0000ff00);
            }
        }
        for (int i = 125; i < 225; i++) {
            for (int j = 150; j < 250; j++) {
                r.setFenyTerkep(i, j, 0x000000ff);
            }
        }
        
        //erre a felére hatnak a fényefektek ^^^^
        r.fenyellenorzes();
        //erre a felére nem VVVV
        
        //r.drawRect(0,0,25,25,0xff663300);
        r.drawSzoveg(szoveg,"FPS: "+jm.getFps(),0xffffffff,10,10,2,2);
        r.drawSzoveg(szoveg,"Alpha build 0.1.2 (Press ESC to escape)",0xffffffff,590,10,2,2);
        //r.drawSzoveg(szoveg,"qwertzuiopasdfghjklyxcvbnm-QWERTZUIOPASDFGHJKLYXCVBNM",0xff00ff00,20,420,2,2);
        r.drawKep(jm.getBevitel().getEgerPozX(), jm.getBevitel().getEgerPozY(), cursor.getPixelek(),cursor.getSzel(),1,1);
    }
    
    public static void main(String args[]){
        JatekMag jm = new JatekMag(new JatekIranyito());
        jm.start();
    }

}
