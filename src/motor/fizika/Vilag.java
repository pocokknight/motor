package motor.fizika;

import jatek.blockok.Edge;
import jatek.blockok.Fold;
import jatek.blockok.Levego;
import java.util.Vector;
import motor.JatekMag;
import motor.Render;

public class Vilag {

    private double camX = 0, camY = 0;
    private int BLOCK_MERET;
    private Block[][] KOCKAK;
    private int[][] FENY;
    private Vector<FenyForras> FENY_FORRASOK = new Vector();
    private Vector<Karakter> KARAKTEREK = new Vector();
    public Vector<int[]> b = new Vector();
    public Vector<int[]> j = new Vector();
    public Vector<int[]> f = new Vector();
    public Vector<int[]> l = new Vector();
    private boolean REPEAT;
    private Block EDGE;
    private int HATTERSZIN = 0xffaaaaaa;
    
    public Vilag(int blockMeret,int szel,int mag,boolean repeat) {
        BLOCK_MERET = blockMeret;
        KOCKAK = new Block[szel][mag];
        FENY = new int[szel][mag];
        REPEAT = repeat;
        EDGE = new Edge();
    }

    public void general() {
        for (int i = 0; i < KOCKAK.length; i++) {
            for (int j = 0; j < KOCKAK[0].length; j++) {
                FENY[i][j] = 0xff000000;
                if((i % 5 == 0 && (j > 7 && j < 12)) || (i == 10 && (j % 15 != 0 && j % 15 != 1)) || (i == j))
                    KOCKAK[i][j] = new Fold();
                else
                    KOCKAK[i][j] = new Levego();
            }
        }
        FENY_FORRASOK.add(new FenyForras(0xffffffff, 15, 11, 11,this));
    }

    public void render(JatekMag jm, Render r) {
        r.drawRect(0, 0, KOCKAK.length*BLOCK_MERET, KOCKAK[0].length*BLOCK_MERET, HATTERSZIN);
        for (int i = 0; i < KOCKAK.length; i++) {
            for (int j = 0; j < KOCKAK[0].length; j++) {
                if(FENY[i][j] != 0xff000000)
                    r.drawRect((int)(BLOCK_MERET*i-KARAKTEREK.get(0).pozX),(int)(BLOCK_MERET*j-KARAKTEREK.get(0).pozY),BLOCK_MERET,BLOCK_MERET,KOCKAK[i][j].szin);
            }
        }
        
        for (int i = 0; i < b.size(); i++) {
            r.drawRect((int)(BLOCK_MERET*b.get(i)[0]-KARAKTEREK.get(0).pozX),(int)(BLOCK_MERET*b.get(i)[1]-KARAKTEREK.get(0).pozY),BLOCK_MERET,BLOCK_MERET,0x99ff0000);
        }
        for (int i = 0; i < j.size(); i++) {
            r.drawRect((int)(BLOCK_MERET*j.get(i)[0]-KARAKTEREK.get(0).pozX),(int)(BLOCK_MERET*j.get(i)[1]-KARAKTEREK.get(0).pozY),BLOCK_MERET,BLOCK_MERET,0x9900ff00);
        }
        for (int i = 0; i < l.size(); i++) {
            r.drawRect((int)(BLOCK_MERET*l.get(i)[0]-KARAKTEREK.get(0).pozX),(int)(BLOCK_MERET*l.get(i)[1]-KARAKTEREK.get(0).pozY),BLOCK_MERET,BLOCK_MERET,0x9900ffff);
        }
        for (int i = 0; i < f.size(); i++) {
            r.drawRect((int)(BLOCK_MERET*f.get(i)[0]-KARAKTEREK.get(0).pozX),(int)(BLOCK_MERET*f.get(i)[1]-KARAKTEREK.get(0).pozY),BLOCK_MERET,BLOCK_MERET,0x990000ff);
        }
        
        for (int i = 0; i < KARAKTEREK.size(); i++) {
            Karakter k = KARAKTEREK.get(i);
            r.drawRect((jm.getAblak().getSzel()-k.szel)/2, (jm.getAblak().getMag()-k.mag)/2, k.szel, k.mag, k.szin);
        }
        
        for (int i = 0; i < KOCKAK.length; i++) {
            for (int j = 0; j < KOCKAK[0].length; j++) {
                r.drawRect((int)(BLOCK_MERET*i-KARAKTEREK.get(0).pozX),(int)(BLOCK_MERET*j-KARAKTEREK.get(0).pozY),BLOCK_MERET,BLOCK_MERET,FENY[i][j]);
            }
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
    
    

    public void setCamX(double camX) {
        this.camX = camX;
    }

    public void setCamY(double camY) {
        this.camY = camY;
    }

    public void addCamX(double camX) {
        this.camX += camX;
    }

    public void addCamY(double camY) {
        this.camY += camY;
    }

    public double getCamX() { return camX; }

    public double getCamY() { return camY; }

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

    public int[][] getFENY() {
        return FENY;
    }

    public Vector<FenyForras> getFENY_FORRASOK() {
        return FENY_FORRASOK;
    }
    
    
    
}
