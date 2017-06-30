package jatek;

import jatek.res.anim.AnimJelolo;
import jatek.res.font.FontJelolo;
import jatek.res.img.ImgJelolo;
import motor.AbsztraktJatek;
import motor.JatekMag;
import motor.Render;
import motor.grafika.*;

public class JatekIranyito extends AbsztraktJatek{

    BetoltottAnim anim;
    BetoltottKep kep,kep2;
    Szoveg szoveg;
    
    public JatekIranyito() {
        kep = new BetoltottKep(ImgJelolo.class, "teszt.png");
        kep2 = new BetoltottKep(ImgJelolo.class, "teszt_1.png");
    }
    
    @Override
    public void frissit(JatekMag jm, double dt) {
        
    }

    @Override
    public void render(JatekMag jm, Render r) {
        r.drawKep(200, 200, kep);
        r.drawKep(jm.getBevitel().getEgerPozX(), jm.getBevitel().getEgerPozY(), kep2);
    }
    
    public static void main(String args[]){
        JatekMag jm = new JatekMag(new JatekIranyito());
        jm.start();
    }

}
