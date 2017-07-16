package motor.fizika;

import motor.JatekMag;
import motor.Render;
import motor.grafika.ForgathatoKep;

public class Eszkoz {

    protected ForgathatoKep kep;
    protected double meretszorzo;
    protected double utido,lengetesiIdo;
    protected int utallapot;
    protected int utallpotmin;
    protected int utallapotmax;

    public Eszkoz(ForgathatoKep kep, double lengetesiIdo, int utallpotmin, int utallapotmax , double meretszorzo) {
        this.kep = kep;
        this.lengetesiIdo = lengetesiIdo;
        this.meretszorzo = meretszorzo;
        this.utido = 0;
        this.utallapot = utallpotmin;
        this.utallpotmin = utallpotmin;
        this.utallapotmax = utallapotmax;
    }

    
    
    public void setKep(ForgathatoKep kep) {
        this.kep = kep;
    }

    public void setutido(double utido) {
        this.utido = utido;
    }

    public void setutallpotmin(int utallpotmin) {
        this.utallpotmin = utallpotmin;
    }

    public void setutallapotmax(int utallapotmax) {
        this.utallapotmax = utallapotmax;
    }
    
    public ForgathatoKep getKep() {
        return kep;
    }

    public double getutido() {
        return utido;
    }

    public int getutallpotmin() {
        return utallpotmin;
    }

    public int getutallapotmax() {
        return utallapotmax;
    }

    public void frissit(Vilag v, JatekMag jm, double dt, boolean UT) {
        
        if(UT){
            utido += dt;
            while(utido > lengetesiIdo){
                utido -= lengetesiIdo;
                utallapot++;
            }
        }
        
    }

    public void render(JatekMag jm, Render r) {
        r.drawKep((int)(jm.getAblak().getSzel()/2 - kep.getSzel()*meretszorzo)
                , (int)(jm.getAblak().getMag()/2 - kep.getMag()*meretszorzo)
                , kep.getPixelek(utallapot,kep.getSzel()/2,kep.getMag())
                , kep.getSzel()*2, meretszorzo, meretszorzo);
    }
    
    
    
}
