package motor.grafika;

import jatek.res.anim.AnimJelolo;
import java.awt.image.BufferedImage;
import java.util.Vector;
import javax.imageio.ImageIO;

public class BetoltottAnim extends BetoltottKep{

    private int szeldb,magdb;
    private double valtIdo;
    private double elteltIdo;
    private Vector<int[]> kepek = new Vector();
    private int kivalasztottKep;
    
    public BetoltottAnim(Class c,String nev,int szeldb,int magdb,double valtIdo) {
        super(c,nev);
        this.szeldb = szeldb;
        this.magdb = magdb;
        this.valtIdo = valtIdo;
        elteltIdo = 0;
        kivalasztottKep = 0;
        animBetolt();
    }
    
    public void animBetolt(){
        mag /= magdb;
        szel /= szeldb;
        int xedik = 0;
        Vector<Integer> v = new Vector();
        for (int i = 0; i < pixelek.length; i++) {
            v.add(pixelek[i]);
            System.out.println(pixelek[i]+"   ");
            if(i%(mag*szel) == (mag*szel)-1){
                kepek.add(atir(v));
                v = new Vector();
                xedik++;
            }
        }
    }
    
    private int[] atir(Vector<Integer> v) {
        int[] t = new int[szel*mag];
        for (int i = 0; i < v.size(); i++) {
            t[i] = v.get(i);
        }
        return t;
    }

    @Override
    public void update(double dt){
        elteltIdo+=dt;
        while(elteltIdo > valtIdo){
            kivalasztottKep++;
            if(kivalasztottKep == kepek.size()) kivalasztottKep = 0;
            
            pixelek = kepek.get(kivalasztottKep);
            
            elteltIdo -= valtIdo;
        }
    }

    private void kiir(int[] t) {
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i]+" ");
            if(i%16==15) System.out.println("");
        }
    }

    
}
