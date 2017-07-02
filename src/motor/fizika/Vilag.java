package motor.fizika;

import jatek.blockok.Fold;
import jatek.blockok.Levego;
import motor.JatekMag;
import motor.Render;

public class Vilag {

    private int camX = 0, camY = 0;
    private int BLOCK_MERET;
    private Block[][] KOCKAK;
    private boolean REPEAT;
    
    public Vilag(int blockMeret,int szel,int mag,boolean repeat) {
        BLOCK_MERET = blockMeret;
        KOCKAK = new Block[szel][mag];
        REPEAT = repeat;
    }

    public void general() {
        System.out.println("general");
        for (int i = 0; i < KOCKAK.length; i++) {
            for (int j = 0; j < KOCKAK[0].length; j++) {
                if(j == 3)
                    KOCKAK[i][j] = new Fold();
                else
                    KOCKAK[i][j] = new Levego();
            }
        }
    }

    public void render(JatekMag jm, Render r) {
        for (int i = 0; i < KOCKAK.length; i++) {
            for (int j = 0; j < KOCKAK[0].length; j++) {
                r.drawRect(BLOCK_MERET*i+camX,BLOCK_MERET*j+camY,BLOCK_MERET,BLOCK_MERET,KOCKAK[i][j].szin);
            }
        }
    }
    
}
