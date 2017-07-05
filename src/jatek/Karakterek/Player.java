package jatek.Karakterek;

import com.sun.glass.events.KeyEvent;
import motor.JatekMag;
import motor.fizika.Block;
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
        
        utkozes(v,jm,dt);
        utkozesTeszt(v,jm,dt);
        
    }
    private void utkozes(Vilag v,JatekMag jm, double dt) {
        
        if(sebX < 0){ //ballra
            boolean b_utk = false;
            int i = (int)Math.floor(pozX/v.getBLOCK_MERET()-1);
            for (int j = (int)Math.floor(pozY/v.getBLOCK_MERET()); j < (int)Math.ceil((pozY+mag)/v.getBLOCK_MERET()); j++) {
                if(v.getKOCKA(i, j).isSzilardJobb()){ b_utk = true; }
            }
            if(b_utk){
                double tav = (pozX) - ((i+1)*v.getBLOCK_MERET());
                if(-sebX*dt < tav){
                    pozX += sebX*dt;
                }else{
                    pozX -= tav;
                }
            }else{
                pozX += sebX*dt;
            }
        }else if(sebX > 0){ //jobbra
            boolean j_utk = false;
            int i = (int)Math.ceil((pozX+szel)/v.getBLOCK_MERET());
            for (int j = (int)Math.floor(pozY/v.getBLOCK_MERET()); j < (int)Math.ceil((pozY+mag)/v.getBLOCK_MERET()); j++) {
                if(v.getKOCKA(i, j).isSzilardBall()){ j_utk = true; }
            }
            if(j_utk){
                double tav = ((i)*v.getBLOCK_MERET()) - (pozX+szel);
                if(sebX*dt < tav){
                    pozX += sebX*dt;
                }else{
                    pozX += tav;
                }
            }else{
                pozX += sebX*dt;
            }
        }

        if(sebY > 0){ //lefele
            boolean l_utk = false;
            int j = (int)Math.ceil((pozY+mag)/v.getBLOCK_MERET());
            for (int i = (int)Math.floor(pozX/v.getBLOCK_MERET()); i < (int)Math.ceil((pozX+szel)/v.getBLOCK_MERET()); i++) {
                if(v.getKOCKA(i, j).isSzilardFent()){ l_utk = true; }
            }
            if(l_utk){
                double tav = ((j)*v.getBLOCK_MERET()) - (pozY+mag);
                if(sebY*dt < tav){
                    pozY += sebY*dt;
                }else{
                    pozY += tav;
                }
            }else{
                pozY += sebY*dt;
            }
        }else if(sebY < 0){//felfele
            boolean f_utk = false;
            int j = (int)Math.floor(pozY/v.getBLOCK_MERET())-1;
            for (int i = (int)Math.floor(pozX/v.getBLOCK_MERET()); i < (int)Math.ceil((pozX+szel)/v.getBLOCK_MERET()); i++) {
                if(v.getKOCKA(i, j).isSzilardLent()){ f_utk = true; }
            }
            if(f_utk){
                double tav = (pozY) - ((j+1)*v.getBLOCK_MERET());
                if(-sebY*dt < tav){
                    pozY += sebY*dt;
                }else{
                    pozY -= tav;
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
        
        for (int i = (int)Math.floor(pozY/v.getBLOCK_MERET()); i < (int)Math.ceil((pozY+mag)/v.getBLOCK_MERET()); i++) {
            int[] t = {(int)Math.floor(pozX/v.getBLOCK_MERET())-1,i};
            v.b.add(t);
        }
        for (int i = (int)Math.floor(pozY/v.getBLOCK_MERET()); i < (int)Math.ceil((pozY+mag)/v.getBLOCK_MERET()); i++) {
            int[] t = {(int)Math.ceil((pozX+szel)/v.getBLOCK_MERET()),i};
            v.j.add(t);
        }
        for (int i = (int)Math.floor(pozX/v.getBLOCK_MERET()); i < (int)Math.ceil((pozX+szel)/v.getBLOCK_MERET()); i++) {
            int[] t = {i,(int)Math.ceil((pozY+mag)/v.getBLOCK_MERET())};
            v.l.add(t);
        }
        for (int i = (int)Math.floor(pozX/v.getBLOCK_MERET()); i < (int)Math.ceil((pozX+szel)/v.getBLOCK_MERET()); i++) {
            int[] t = {i,(int)Math.floor(pozY/v.getBLOCK_MERET())-1};
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
    
    /*
        //vizszintes ütközes
        if(sebX != 0){
            if(sebX > 0){//jobbra halad
                for (int i = (int)Math.floor(pozY/v.getBLOCK_MERET()); i < (int)Math.ceil((pozY+mag)/v.getBLOCK_MERET()); i++) {
                    Block b = v.getKOCKA((int)Math.ceil((pozX+szel)/v.getBLOCK_MERET()),i);
                    if(b.isSzilardBall()){
                        int[] t = {(int)Math.ceil((pozX+szel)/v.getBLOCK_MERET()),i};
                        v.b.add(t);
                        double tav = (Math.ceil((pozX+szel)/v.getBLOCK_MERET())*v.getBLOCK_MERET()) - (pozX+szel);
                    }else{
                        pozX += sebX*dt;
                    }
                }
            }else if(sebX < 0){//ballra halad
                for (int i = (int)Math.floor(pozY/v.getBLOCK_MERET()); i < (int)Math.ceil((pozY+mag)/v.getBLOCK_MERET()); i++) {
                    Block b = v.getKOCKA((int)Math.floor(pozX/v.getBLOCK_MERET()),i);
                    if(b.isSzilardJobb()){
                        int[] t = {(int)Math.floor(pozX/v.getBLOCK_MERET()),i};
                        v.b.add(t);
                        double tav = (pozX) - (Math.floor(pozX/v.getBLOCK_MERET())*v.getBLOCK_MERET()+v.getBLOCK_MERET());
                        if(sebX*dt > tav){
                            pozX += sebX*dt;
                            System.out.println(pozX+" "+tav+" "+sebX*dt);
                        }else{
                            System.out.println("MOST "+pozX+" "+tav+" "+sebX*dt);
                            pozX += tav;
                        }
                    }else{
                        pozX += sebX*dt;
                    }
                }
            }
        }
            //függöleges ütközés
        if(sebY != 0){
            if(sebY > 0){//lefele halad
                for (int i = (int)Math.floor(pozX/v.getBLOCK_MERET()); i < (int)Math.ceil((pozX+szel)/v.getBLOCK_MERET()); i++) {
                    Block b = v.getKOCKA(i,(int)Math.ceil((pozY+mag)/v.getBLOCK_MERET()));
                    if(b.isSzilardFent()){
                        int[] t = {i,(int)Math.ceil((pozY+mag)/v.getBLOCK_MERET())};
                        v.b.add(t);
                        double tav = (Math.ceil((pozY+mag)/v.getBLOCK_MERET())*v.getBLOCK_MERET()) - (pozY+mag);
                        if(sebY*dt < tav){
                            pozY += sebY*dt;
                        }else{
                            pozY += tav;
                        }
                    }else{
                        pozY += sebY*dt;
                    }

                }
            }else if(sebY < 0){//felfele halad
                for (int i = (int)Math.floor(pozX/v.getBLOCK_MERET()); i < (int)Math.ceil((pozX+szel)/v.getBLOCK_MERET()); i++) {
                    Block b = v.getKOCKA(i,(int)Math.floor(pozY/v.getBLOCK_MERET()));
                    if(b.isSzilardLent()){
                        int[] t = {i,(int)Math.floor(pozY/v.getBLOCK_MERET())};
                        v.b.add(t);
                        double tav = (pozY) - (Math.floor(pozY/v.getBLOCK_MERET())*v.getBLOCK_MERET()+v.getBLOCK_MERET());
                        if(sebY*dt < tav){
                            pozY += sebY*dt;
                        }else{
                            pozY -= tav;
                        }
                    }else{
                        pozY += sebY*dt;
                    }
                }
            }
        }*/

}
