package jatek.Karakterek;

import com.sun.glass.events.KeyEvent;
import motor.JatekMag;
import motor.fizika.Karakter;
import motor.fizika.Vilag;

public class Player extends Karakter{
    
    static double ALAP_MAX_SEB = 150; 
    
    public Player(int id, int szel, int mag, int szin, double pozX, double pozY) {
        super(id, szel, mag, szin, pozX, pozY,ALAP_MAX_SEB);
    }
    
    @Override
    public void frissit(Vilag v,JatekMag jm, double dt) {
        
        if(jm.getBevitel().isBillTart(KeyEvent.VK_W)){
            sebY-=5;
            if(sebY < -ALAP_MAX_SEB) sebY = -ALAP_MAX_SEB;
        }else if(jm.getBevitel().isBillTart(KeyEvent.VK_S)){
            sebY+=5;
            if(sebY > ALAP_MAX_SEB) sebY = ALAP_MAX_SEB;
        }else{
            if(sebY > 0) sebY*=0.5;
            else if(sebY < 0) sebY*=0.5;
            if(sebY < 1 && sebY > -1) sebY = 0;
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
        
        if(sebX != 0 || sebY != 0){
            utkozes(v,jm,dt);
        }
        
        pozX += sebX*dt;
        pozY += sebY*dt;
        
    }
    
    private void utkozes(Vilag v,JatekMag jm, double dt) {
        //vizszintes ütközes
        if(sebX > 0){//jobbra halad
            for (int i = (int)Math.floor(pozY/v.getBLOCK_MERET()); i < (int)Math.ceil((pozY+mag)/v.getBLOCK_MERET()); i++) {
                if(v.getKOCKAK()[(int)Math.ceil((pozX+szel)/v.getBLOCK_MERET())+1][i].isSzilardBall()){
                    System.out.println("jobboldalt ütközik");
                }
            }
        }else{//ballra halad
            for (int i = (int)Math.floor(pozY/v.getBLOCK_MERET()); i < (int)Math.ceil((pozY+mag)/v.getBLOCK_MERET()); i++) {
                if(v.getKOCKAK()[(int)Math.floor(pozX/v.getBLOCK_MERET())-1][i].isSzilardJobb()){
                    System.out.println("balloldalt ütközik");
                }
            }
        }
        
        //függöleges ütközés
        if(sebY > 0){//lefele halad
            for (int i = (int)Math.floor(pozX/v.getBLOCK_MERET()); i < (int)Math.ceil((pozX+szel)/v.getBLOCK_MERET()); i++) {
                if(v.getKOCKAK()[i][(int)Math.ceil((pozY+mag)/v.getBLOCK_MERET())+1].isSzilardBall()){
                    System.out.println("lent ütközik");
                }
            }
        }else{//felfele halad
            for (int i = (int)Math.floor(pozX/v.getBLOCK_MERET()); i < (int)Math.ceil((pozX+szel)/v.getBLOCK_MERET()); i++) {
                if(v.getKOCKAK()[i][(int)Math.floor(pozY/v.getBLOCK_MERET())-1].isSzilardJobb()){
                    System.out.println("fent ütközik");
                }
            }
        }
        /*
        (int)Math.floor(pozX/v.getBLOCK_MERET())
        (int)Math.ceil((pozX+szel)/v.getBLOCK_MERET())
        (int)Math.floor(pozY/v.getBLOCK_MERET())
        (int)Math.ceil((pozY+mag)/v.getBLOCK_MERET())
        */
    }

}
