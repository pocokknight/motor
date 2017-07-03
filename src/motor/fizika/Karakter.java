package motor.fizika;

import motor.JatekMag;

public class Karakter {

    protected int id,szel,mag,szin;
    protected double pozX,pozY,sebX,sebY,maxseb;
    protected boolean szilardLent,szilardFent,szilardJobb,szilardBall;

    public Karakter(int id, int szel, int mag, int szin, double pozX, double pozY, double maxseb) {
        this.id = id;
        this.szel = szel;
        this.mag = mag;
        this.szin = szin;
        this.maxseb = maxseb;
        this.pozX = pozX;
        this.pozY = pozY;
        this.sebX = 0;
        this.sebY = 0;
        szilardLent = true;
        szilardFent = true;
        szilardJobb = true;
        szilardBall = true;
    }
    
    public void frissit(Vilag v,JatekMag jm, double dt) {}
    
}
