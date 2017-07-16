package jatek;

import jatek.Karakterek.Player;
import jatek.res.anim.AnimJelolo;
import jatek.res.font.FontJelolo;
import jatek.res.img.ImgJelolo;
import motor.AbsztraktJatek;
import motor.JatekMag;
import motor.Render;
import motor.fizika.Block;
import motor.fizika.Eszkoz;
import motor.fizika.FenyForras;
import motor.fizika.Karakter;
import motor.fizika.Vilag;
import motor.grafika.*;

public class JatekIranyito extends AbsztraktJatek{

    BetoltottAnim anim;
    BetoltottKep kep,cursor;
    Eszkoz kard;
    Szoveg szoveg;
    Vilag alap;
    Player jatekos;
    
    public JatekIranyito() {
        kep = new BetoltottKep(ImgJelolo.class, "teszt.png");
        cursor = new BetoltottKep(ImgJelolo.class, "cursor.png");
        kard = new Eszkoz(new ForgathatoKep(ImgJelolo.class, "kard.png"),0.02, -5, 15, 4);
        szoveg = new Szoveg("char_table.png");
        alap = new Vilag(15,100,50,false);
        alap.general();
        jatekos = new Player(0, 25, 55, 0x99123456, 0, 0);
        jatekos.setEszkoz(kard);
        alap.setPLAYER(jatekos);
    }
    
    @Override
    public void frissit(JatekMag jm, double dt) {
        alap.frissit(jm,dt);
    }

    @Override
    public void render(JatekMag jm, Render r) {
        alap.render(jm,r);
        
        //erre a felére hatnak a fényefektek ^^^^
        r.fenyellenorzes();
        //erre a felére nem VVVV
        
        //r.drawRect(0,0,25,25,0xff663300);
        r.drawSzoveg(szoveg,"FPS: "+jm.getFps(),0xffffffff,10,15,2,2);
        r.drawSzoveg(szoveg,"Alpha build 0.1.2 (Press ESC to escape)",0xffffffff,590,15,2,2);
        r.drawSzoveg(szoveg,"Press F1 for help",0xffffffff,700,30,2,2);
        if(r.isHelp()){
            r.drawSzoveg(szoveg,"Press F2 to show lights",0xffffffff,700,45,2,2);
            r.drawSzoveg(szoveg,"Press F3 to show collosion",0xffffffff,700,60,2,2);
        }
        //r.drawSzoveg(szoveg,"qwertzuiopasdfghjklyxcvbnm-QWERTZUIOPASDFGHJKLYXCVBNM",0xff00ff00,20,420,2,2);
        r.drawKep(jm.getBevitel().getEgerPozX(), jm.getBevitel().getEgerPozY(), cursor.getPixelek(),cursor.getSzel(),1,1);   
    }
    
    public static void main(String args[]){
        JatekMag jm = new JatekMag(new JatekIranyito());
        jm.start();
    }

}
