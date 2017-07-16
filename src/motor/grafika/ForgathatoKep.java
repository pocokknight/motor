package motor.grafika;

import java.util.Vector;

public class ForgathatoKep extends BetoltottKep{

    private double valtIdo;
    private double elteltIdo;
    private Vector<int[]> kepek = new Vector();
    private int kivalasztottKep;
    private Forgato forgato;
    
    public ForgathatoKep(Class c,String nev) {
        super(c,nev);
        forgato = new Forgato();
        elteltIdo = 0;
        kivalasztottKep = 0;
        animBetolt();
    }
    
    public void animBetolt(){
        for (int i = 0; i < 36; i++) {
            kepek.add(forgato.forgat(pixelek, szel, mag, Math.toRadians(i*10)));
        }
    }

    public int[] getPixelek(int sorszam) {
        while(sorszam < 0) sorszam += kepek.size();
        while(sorszam >= kepek.size()) sorszam -= kepek.size();
        return kepek.get(sorszam);
    }

    public int[] getPixelek(int sorszam, int x, int y) {
        
        while(sorszam < 0) sorszam += kepek.size();
        while(sorszam >= kepek.size()) sorszam -= kepek.size();
        
        int[] t = new int[super.mag * super.szel * 4];
        
        int ujX = (int) (super.szel/8*Math.cos(Math.toRadians(sorszam*10+45))-super.mag/2*Math.sin(Math.toRadians(sorszam*10+45)));
        int ujY = (int) (super.szel/8*Math.sin(Math.toRadians(sorszam*10+45))+super.mag/2*Math.cos(Math.toRadians(sorszam*10+45)));
        
        for (int i = 0; i < super.szel; i++) {
            for (int j = 0; j < super.mag; j++) {
                try{
                t[(ujX + i + super.szel/2) + (-ujY + j + super.mag/2) * super.szel*2] = kepek.get(sorszam)[i + j * super.szel];
                }catch(Exception e){}
            }
        }
        
        return t;
    }
    
    
    
}
