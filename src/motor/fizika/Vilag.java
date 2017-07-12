package motor.fizika;

import jatek.blockok.Edge;
import jatek.blockok.Fold;
import jatek.blockok.Levego;
import java.util.Vector;
import motor.JatekMag;
import motor.Render;

public class Vilag {

    private int BLOCK_MERET;
    private Block[][] KOCKAK;
    private Vector<Karakter> KARAKTEREK = new Vector();
    public Vector<int[]> b = new Vector();
    public Vector<int[]> j = new Vector();
    public Vector<int[]> f = new Vector();
    public Vector<int[]> l = new Vector();
    private boolean REPEAT;
    private Block EDGE;
    private FenyForras KAR_FENY;
    private FenyForras TESZT_FENY;
    private FenyForras TESZT_FENY2;
    
    public Vilag(int blockMeret,int szel,int mag,boolean repeat) {
        BLOCK_MERET = blockMeret;
        KOCKAK = new Block[szel][mag];
        REPEAT = repeat;
        EDGE = new Edge();
        KAR_FENY = new FenyForras(200, 150, 0xaaaaff);
        TESZT_FENY = new FenyForras(100, 255, 0xffffaa);
        TESZT_FENY2 = new FenyForras(200, 255, 0xaaffff);
    }

    public void general() {
        for (int i = 0; i < KOCKAK.length; i++) {
            for (int j = 0; j < KOCKAK[0].length; j++) {
                if((i % 5 == 0 && (j > 7 && j < 12)) || (i == 10 && (j % 15 != 0 && j % 15 != 1)) || (i == j) || (i+1 == j))
                    KOCKAK[i][j] = new Fold();
                else
                    KOCKAK[i][j] = new Levego();
            }
        }
    }

    public void render(JatekMag jm, Render r) {
        for (int i = 0; i < KOCKAK.length; i++) {
            for (int j = 0; j < KOCKAK[0].length; j++) {
                r.drawRect((int)(BLOCK_MERET*i-KARAKTEREK.get(0).pozX),(int)(BLOCK_MERET*j-KARAKTEREK.get(0).pozY),BLOCK_MERET,BLOCK_MERET,KOCKAK[i][j].szin,KOCKAK[i][j].getAtl());
            }
        }
        
        for (int i = 0; i < b.size(); i++) {
            r.drawRect((int)(BLOCK_MERET*b.get(i)[0]-KARAKTEREK.get(0).pozX),(int)(BLOCK_MERET*b.get(i)[1]-KARAKTEREK.get(0).pozY),BLOCK_MERET,BLOCK_MERET,0x99ff0000,0);
        }
        for (int i = 0; i < j.size(); i++) {
            r.drawRect((int)(BLOCK_MERET*j.get(i)[0]-KARAKTEREK.get(0).pozX),(int)(BLOCK_MERET*j.get(i)[1]-KARAKTEREK.get(0).pozY),BLOCK_MERET,BLOCK_MERET,0x9900ff00,0);
        }
        for (int i = 0; i < l.size(); i++) {
            r.drawRect((int)(BLOCK_MERET*l.get(i)[0]-KARAKTEREK.get(0).pozX),(int)(BLOCK_MERET*l.get(i)[1]-KARAKTEREK.get(0).pozY),BLOCK_MERET,BLOCK_MERET,0x9900ffff,0);
        }
        for (int i = 0; i < f.size(); i++) {
            r.drawRect((int)(BLOCK_MERET*f.get(i)[0]-KARAKTEREK.get(0).pozX),(int)(BLOCK_MERET*f.get(i)[1]-KARAKTEREK.get(0).pozY),BLOCK_MERET,BLOCK_MERET,0x990000ff,0);
        }
        
        for (int i = 0; i < KARAKTEREK.size(); i++) {
            Karakter k = KARAKTEREK.get(i);
            r.drawRect((jm.getAblak().getSzel()-k.szel)/2, (jm.getAblak().getMag()-k.mag)/2, k.szel, k.mag, k.szin,0);
        }
        
        r.addFeny(this,KAR_FENY,jm.getAblak().getSzel()/2,jm.getAblak().getMag()/2-KARAKTEREK.get(0).mag/2-1);
        r.addFeny(this,TESZT_FENY,(int)(200-KARAKTEREK.get(0).pozX),(int)(50-KARAKTEREK.get(0).pozY));
        
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
    
    public double getCamX(){ return KARAKTEREK.get(0).pozX; }
    public double getCamY(){ return KARAKTEREK.get(0).pozY; }
    
}
