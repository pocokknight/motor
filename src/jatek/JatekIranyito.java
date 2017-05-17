package jatek;

import motor.AbsztraktJatek;
import motor.JatekMag;
import motor.Render;
import motor.grafika.BetoltottKep;

public class JatekIranyito extends AbsztraktJatek{

    BetoltottKep  kep;
    
    public JatekIranyito() {
        kep = new BetoltottKep("teszt.png");
    }
    
    @Override
    public void frissit(JatekMag jm, float dt) {
    }

    @Override
    public void render(JatekMag jm, Render r) {
        r.drawKep(100,100, kep);
        r.drawKep(jm.getBevitel().getEgerPozX(), jm.getBevitel().getEgerPozY(), kep);
        r.setRacs(true);
    }
    
    public static void main(String args[]){
        JatekMag jm = new JatekMag(new JatekIranyito());
        jm.start();
    }

}
