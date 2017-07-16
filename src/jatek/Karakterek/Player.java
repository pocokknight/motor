package jatek.Karakterek;

import com.sun.glass.events.KeyEvent;
import motor.JatekMag;
import motor.Render;
import motor.fizika.Eszkoz;
import motor.fizika.Karakter;
import motor.fizika.Vilag;
import motor.grafika.ForgathatoKep;

public class Player extends Karakter{
    
    static double ALAP_MAX_SEB = 150; 
    
    private Eszkoz eszkoz;
    private int ugrasok = 0;
    private int ugrasokMax = 1;
    
    public Player(int id, int szel, int mag, int szin, double pozX, double pozY) {
        super(id, szel, mag, szin, pozX, pozY,ALAP_MAX_SEB);
    }
    
    public void frissit(Vilag v,JatekMag jm, double dt,boolean UT) {
        
        eszkoz.frissit(v,jm,dt,UT);
        
        sebY+=15;
        if(sebY < 1 && sebY > -1) sebY = 0;
        
        if(jm.getBevitel().isBillLenyomva(KeyEvent.VK_SPACE)){
            if(ugrasok < ugrasokMax){
                sebY = -v.getBLOCK_MERET()*40;
                ugrasok++;
            }
        }
        
        if(jm.getBevitel().isBillTart(KeyEvent.VK_A)){
            sebX-=5;
            if(sebX < -ALAP_MAX_SEB) sebX = -ALAP_MAX_SEB;
        }else if(jm.getBevitel().isBillTart(KeyEvent.VK_D)){
            sebX+=5;
            if(sebX > ALAP_MAX_SEB) sebX = ALAP_MAX_SEB;
        }else{
            if(sebX > 0) sebX*=0.5;
            else if(sebX < 0) sebX*=0.5;
            if(sebX < 1 && sebX > -1) sebX = 0;
        }
        
        utkozes(v,jm,dt);
        if(jm.getRender().isColl())utkozesTeszt(v,jm,dt);
        
    }
    private void utkozes(Vilag v,JatekMag jm, double dt) {
        
        double pX = (jm.getAblak().getSzel()-szel)/2;
        double pY = (jm.getAblak().getMag()-mag)/2;
        
        if(sebX < 0){ //ballra
            boolean b_utk = false;
            int i = (int)Math.floor((pozX+pX)/v.getBLOCK_MERET()-1);
            for (int j = (int)Math.floor((pozY+pY)/v.getBLOCK_MERET()); j < (int)Math.ceil((pozY+mag+pY)/v.getBLOCK_MERET()); j++) {
                if(v.getKOCKA(i, j).isSzilardJobb()){ b_utk = true; }
            }
            if(b_utk){
                double tav = (pozX+pX) - ((i+1)*v.getBLOCK_MERET());
                if(-sebX*dt < tav){
                    pozX += sebX*dt;
                }else{
                    pozX -= tav;
                    sebX = 0;
                }
            }else{
                pozX += sebX*dt;
            }
        }else if(sebX > 0){ //jobbra
            boolean j_utk = false;
            int i = (int)Math.ceil((pozX+szel+pX)/v.getBLOCK_MERET());
            for (int j = (int)Math.floor((pozY+pY)/v.getBLOCK_MERET()); j < (int)Math.ceil((pozY+mag+pY)/v.getBLOCK_MERET()); j++) {
                if(v.getKOCKA(i, j).isSzilardBall()){ j_utk = true; }
            }
            if(j_utk){
                double tav = ((i)*v.getBLOCK_MERET()) - (pozX+szel+pX);
                if(sebX*dt < tav){
                    pozX += sebX*dt;
                }else{
                    pozX += tav;
                    sebX = 0;
                }
            }else{
                pozX += sebX*dt;
            }
        }

        if(sebY > 0){ //lefele
            boolean l_utk = false;
            int j = (int)Math.ceil((pozY+mag+pY)/v.getBLOCK_MERET());
            for (int i = (int)Math.floor((pozX+pX)/v.getBLOCK_MERET()); i < (int)Math.ceil((pozX+szel+pX)/v.getBLOCK_MERET()); i++) {
                if(v.getKOCKA(i, j).isSzilardFent()){ l_utk = true; }
            }
            if(l_utk){
                double tav = ((j)*v.getBLOCK_MERET()) - (pozY+mag+pY);
                if(sebY*dt < tav){
                    pozY += sebY*dt;
                }else{
                    pozY += tav;
                    sebY = 0;
                    ugrasok = 0;
                }
            }else{
                pozY += sebY*dt;
            }
        }else if(sebY < 0){//felfele
            boolean f_utk = false;
            int j = (int)Math.floor((pozY+pY)/v.getBLOCK_MERET())-1;
            for (int i = (int)Math.floor((pozX+pX)/v.getBLOCK_MERET()); i < (int)Math.ceil((pozX+szel+pX)/v.getBLOCK_MERET()); i++) {
                if(v.getKOCKA(i, j).isSzilardLent()){ f_utk = true; }
            }
            if(f_utk){
                double tav = (pozY+pY) - ((j+1)*v.getBLOCK_MERET());
                if(-sebY*dt < tav){
                    pozY += sebY*dt;
                }else{
                    pozY -= tav;
                    sebY = 0;
                }
            }else{
                pozY += sebY*dt;
            }
        }
        /*
        (int)Math.floor(pozX/v.getBLOCK_MERET())
        (int)Math.ceil((pozX+szel)/v.getBLOCK_MERET())
        (int)Math.floor(pozY/v.getBLOCK_MERET())
        (int)Math.ceil((pozY+mag)/v.getBLOCK_MERET())
        */
    }
    private void utkozesTeszt(Vilag v,JatekMag jm, double dt) {
        v.b.clear();
        v.j.clear();
        v.l.clear();
        v.f.clear();
        
        double pX = (jm.getAblak().getSzel()-szel)/2;
        double pY = (jm.getAblak().getMag()-mag)/2;
        
        for (int i = (int)Math.floor((pozY+pY)/v.getBLOCK_MERET()); i < (int)Math.ceil((pozY+mag+pY)/v.getBLOCK_MERET()); i++) {
            int[] t = {(int)Math.floor((pozX+pX)/v.getBLOCK_MERET())-1,i};
            v.b.add(t);
        }
        for (int i = (int)Math.floor((pozY+pY)/v.getBLOCK_MERET()); i < (int)Math.ceil((pozY+mag+pY)/v.getBLOCK_MERET()); i++) {
            int[] t = {(int)Math.ceil((pozX+szel+pX)/v.getBLOCK_MERET()),i};
            v.j.add(t);
        }
        for (int i = (int)Math.floor((pozX+pX)/v.getBLOCK_MERET()); i < (int)Math.ceil((pozX+szel+pX)/v.getBLOCK_MERET()); i++) {
            int[] t = {i,(int)Math.ceil((pozY+mag+pY)/v.getBLOCK_MERET())};
            v.l.add(t);
        }
        for (int i = (int)Math.floor((pozX+pX)/v.getBLOCK_MERET()); i < (int)Math.ceil((pozX+szel+pX)/v.getBLOCK_MERET()); i++) {
            int[] t = {i,(int)Math.floor((pozY+pY)/v.getBLOCK_MERET())-1};
            v.f.add(t);
        }
        
        //pozX += sebX*dt;
        //pozY += sebY*dt;
        
        /*
        (int)Math.floor(pozX/v.getBLOCK_MERET())
        (int)Math.ceil((pozX+szel)/v.getBLOCK_MERET())
        (int)Math.floor(pozY/v.getBLOCK_MERET())
        (int)Math.ceil((pozY+mag)/v.getBLOCK_MERET())
        */
    }
    public void setEszkoz(Eszkoz f){
        eszkoz = f;
    }
    public Eszkoz getEszkoz() {
        return eszkoz;
    }

    public void render(JatekMag jm, Render r,boolean ut) {
        r.drawRect(
                (jm.getAblak().getSzel()-szel)/2, 
                (jm.getAblak().getMag()-mag)/2, 
                szel, mag, szin,0);
        if(ut) eszkoz.render(jm,r);
    }

}
