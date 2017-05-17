package jatek;

import jatek.res.anim.AnimJelolo;
import motor.AbsztraktJatek;
import motor.JatekMag;
import motor.Render;
import motor.grafika.BetoltottAnim;
import motor.grafika.BetoltottKep;

public class JatekIranyito extends AbsztraktJatek{

    BetoltottAnim anim;
    BetoltottKep kep;
    
    public JatekIranyito() {
        anim = new BetoltottAnim(AnimJelolo.class,"tesztanim.png",1,4,0.1);
        kep = new BetoltottKep(AnimJelolo.class,"tesztanim.png");
    }
    
    @Override
    public void frissit(JatekMag jm, double dt) {
        anim.update(dt);
    }

    @Override
    public void render(JatekMag jm, Render r) {
        r.drawKep(jm.getBevitel().getEgerPozX()-8, jm.getBevitel().getEgerPozY()-16, anim);
        r.drawKep(100, 100, kep);
    }
    
    public static void main(String args[]){
        JatekMag jm = new JatekMag(new JatekIranyito());
        jm.start();
    }

}
