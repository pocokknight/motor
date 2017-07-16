package motor.fizika;

import jatek.Karakterek.Player;
import jatek.blockok.*;
import java.awt.event.MouseEvent;
import java.util.Vector;
import motor.*;

public class Vilag {

    private boolean UT;
    private int BLOCK_MERET;
    private Block[][] KOCKAK;
    private Vector<Karakter> KARAKTEREK = new Vector();
    private Player PLAYER;
    public Vector<int[]> b = new Vector();
    public Vector<int[]> j = new Vector();
    public Vector<int[]> f = new Vector();
    public Vector<int[]> l = new Vector();
    private boolean REPEAT;
    private Block EDGE;
    private FenyForras KAR_FENY;
    
    public Vilag(int blockMeret,int szel,int mag,boolean repeat) {
        BLOCK_MERET = blockMeret;
        KOCKAK = new Block[szel][mag];
        REPEAT = repeat;
        EDGE = new Edge();
        KAR_FENY = new FenyForras(200, 150, 0xffffff);
        UT = false;
    }

    public void general() {
        for (int i = 0; i < KOCKAK.length; i++) {
            for (int j = 0; j < KOCKAK[0].length; j++) {
                if( (j > KOCKAK[0].length-2) ||  (j == KOCKAK[0].length-7 && i % 20 > 2 && i % 20 < 18) || (j == KOCKAK[0].length-13 && i % 20 > 6 && i % 20 < 14) )
                    KOCKAK[i][j] = new Fold();
                else
                    KOCKAK[i][j] = new Levego();
            }
        }
    }

    public void frissit(JatekMag jm, double dt) {
        
        PLAYER.frissit(this, jm, dt, UT);
        
        for (int i = 0; i < KARAKTEREK.size(); i++) {
            Karakter k = KARAKTEREK.get(i);
            k.frissit(this,jm,dt);
        }
        
        if(PLAYER.getEszkoz().utallapot >= PLAYER.getEszkoz().utallapotmax){
            UT = false;
            PLAYER.getEszkoz().utallapot = PLAYER.getEszkoz().utallpotmin;
        }
        
        if(jm.getBevitel().isEgerTart(MouseEvent.BUTTON1)){
            UT = true;
        }
    }

    public void render(JatekMag jm, Render r) {
        for (int i = 0; i < KOCKAK.length; i++) {
            for (int j = 0; j < KOCKAK[0].length; j++) {
                r.drawRect((int)(BLOCK_MERET*i-PLAYER.pozX),(int)(BLOCK_MERET*j-PLAYER.pozY),BLOCK_MERET,BLOCK_MERET,KOCKAK[i][j].szin,KOCKAK[i][j].getAtl());
            }
        }
        
        if(jm.getRender().isColl()){
            for (int i = 0; i < b.size(); i++) {
                r.drawRect((int)(BLOCK_MERET*b.get(i)[0]-PLAYER.pozX),(int)(BLOCK_MERET*b.get(i)[1]-PLAYER.pozY),BLOCK_MERET,BLOCK_MERET,0x99ff0000,0);
            }
            for (int i = 0; i < j.size(); i++) {
                r.drawRect((int)(BLOCK_MERET*j.get(i)[0]-PLAYER.pozX),(int)(BLOCK_MERET*j.get(i)[1]-PLAYER.pozY),BLOCK_MERET,BLOCK_MERET,0x9900ff00,0);
            }
            for (int i = 0; i < l.size(); i++) {
                r.drawRect((int)(BLOCK_MERET*l.get(i)[0]-PLAYER.pozX),(int)(BLOCK_MERET*l.get(i)[1]-PLAYER.pozY),BLOCK_MERET,BLOCK_MERET,0x9900ffff,0);
            }
            for (int i = 0; i < f.size(); i++) {
                r.drawRect((int)(BLOCK_MERET*f.get(i)[0]-PLAYER.pozX),(int)(BLOCK_MERET*f.get(i)[1]-PLAYER.pozY),BLOCK_MERET,BLOCK_MERET,0x990000ff,0);
            }
        }
        
        PLAYER.render(jm,r,UT);
        
        for (int i = 0; i < KARAKTEREK.size(); i++) {
            Karakter k = KARAKTEREK.get(i);
        }
        
        r.addFeny(this,KAR_FENY,jm.getAblak().getSzel()/2,jm.getAblak().getMag()/2-PLAYER.mag/2-1);
        
    }

    public void addKarakter(Karakter k) {
        KARAKTEREK.add(k);
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
    
    public double getCamX(){ return PLAYER.pozX; }
    public double getCamY(){ return PLAYER.pozY; }
    public Player getPLAYER() { return PLAYER; }
    public void setPLAYER(Player PLAYER) { this.PLAYER = PLAYER; }
    
}
