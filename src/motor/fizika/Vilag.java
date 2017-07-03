package motor.fizika;

import jatek.blockok.Fold;
import jatek.blockok.Levego;
import java.util.Vector;
import motor.JatekMag;
import motor.Render;

public class Vilag {

    private int camX = 0, camY = 0;
    private int BLOCK_MERET;
    private Block[][] KOCKAK;
    private Vector<Karakter> KARAKTEREK = new Vector();
    public Vector<int[]> b = new Vector();
    private boolean REPEAT;
    
    public Vilag(int blockMeret,int szel,int mag,boolean repeat) {
        BLOCK_MERET = blockMeret;
        KOCKAK = new Block[szel][mag];
        REPEAT = repeat;
    }

    public void general() {
        for (int i = 0; i < KOCKAK.length; i++) {
            for (int j = 0; j < KOCKAK[0].length; j++) {
                if(j == 10 || i == 0 || (i % 5 == 0 && j == 9))
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
        
        for (int i = 0; i < b.size(); i++) {
            r.drawRect(BLOCK_MERET*b.get(i)[0]+camX,BLOCK_MERET*b.get(i)[1]+camY,BLOCK_MERET,BLOCK_MERET,0xffff0000);
        }
        
        for (int i = 0; i < KARAKTEREK.size(); i++) {
            Karakter k = KARAKTEREK.get(i);
            r.drawRect(Math.round((float)k.pozX), Math.round((float)k.pozY), k.szel, k.mag, k.szin);
        }
    }

    public void addKarakter(Karakter k) {
        KARAKTEREK.add(k);
    }

    public void frissit(JatekMag jm, double dt) {
        for (int i = 0; i < KARAKTEREK.size(); i++) {
            Karakter k = KARAKTEREK.get(i);
            k.frissit(this,jm,dt);
        }
    }

    public int getCamX() { return camX; }

    public int getCamY() { return camY; }

    public int getBLOCK_MERET() { return BLOCK_MERET; }

    public Block[][] getKOCKAK() { return KOCKAK; }

    public Vector<Karakter> getKARAKTEREK() { return KARAKTEREK; }

    public boolean isREPEAT() { return REPEAT; }
    
    
    
}
