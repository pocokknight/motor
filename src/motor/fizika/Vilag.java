package motor.fizika;

import jatek.blockok.Edge;
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
    public Vector<int[]> j = new Vector();
    public Vector<int[]> f = new Vector();
    public Vector<int[]> l = new Vector();
    private boolean REPEAT;
    private Block EDGE;
    
    public Vilag(int blockMeret,int szel,int mag,boolean repeat) {
        BLOCK_MERET = blockMeret;
        KOCKAK = new Block[szel][mag];
        REPEAT = repeat;
        EDGE = new Edge();
    }

    public void general() {
        for (int i = 0; i < KOCKAK.length; i++) {
            for (int j = 0; j < KOCKAK[0].length; j++) {
                if((i % 5 == 0 && (j == 9 || j == 10)) || (i == 10 && (j % 15 != 0 && j % 15 != 1)))
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
            r.drawRect(BLOCK_MERET*b.get(i)[0]+camX,BLOCK_MERET*b.get(i)[1]+camY,BLOCK_MERET,BLOCK_MERET,0x99ff0000);
        }
        for (int i = 0; i < j.size(); i++) {
            r.drawRect(BLOCK_MERET*j.get(i)[0]+camX,BLOCK_MERET*j.get(i)[1]+camY,BLOCK_MERET,BLOCK_MERET,0x9900ff00);
        }
        for (int i = 0; i < l.size(); i++) {
            r.drawRect(BLOCK_MERET*l.get(i)[0]+camX,BLOCK_MERET*l.get(i)[1]+camY,BLOCK_MERET,BLOCK_MERET,0x9900ffff);
        }
        for (int i = 0; i < f.size(); i++) {
            r.drawRect(BLOCK_MERET*f.get(i)[0]+camX,BLOCK_MERET*f.get(i)[1]+camY,BLOCK_MERET,BLOCK_MERET,0x990000ff);
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

    public Block getKOCKA(int x, int y) {
        
        if(!REPEAT){
            if(x < 0 || x >= KOCKAK.length || y < 0 || y >= KOCKAK[0].length) return EDGE;
        }else{
            if(x < 0){
                x += KOCKAK.length;
            }else if(x >= KOCKAK.length){
                x -= KOCKAK.length;
            }
            if(y < 0 || y >= KOCKAK[0].length) return EDGE;
        }
        
        return KOCKAK[x][y];
    }

    public Vector<Karakter> getKARAKTEREK() { return KARAKTEREK; }

    public boolean isREPEAT() { return REPEAT; }
    
    
    
}
