package motor.grafika;

import jatek.res.font.FontJelolo;
import java.util.Vector;

public class Szoveg {
    
    private static Vector<Vector<Integer>> betuk = null;
    private int mag = 0;
    
    public Szoveg(String nev){
        BetoltottKep keszlet = new BetoltottKep(FontJelolo.class, nev);
        
        int pixelSzamlalo = 0;
        int vectorSzamlalo = 0;
        boolean ujKor = false;
        
        betuk = new Vector();
        betuk.add(new Vector());
        
        while(pixelSzamlalo < keszlet.pixelek.length){
            
            int p = keszlet.pixelek[pixelSzamlalo];
            
            if(p == 0xff00ffff){
                pixelSzamlalo++;
                if(!ujKor){
                    betuk.add(new Vector());
                    vectorSzamlalo++;
                }else{
                    vectorSzamlalo++;
                }
                
            }
            
            if(p == 0xff00ff00){
                pixelSzamlalo++;
                vectorSzamlalo = 0;
                mag++;
                ujKor = true;
            }
            
            if(p == 0xffff0000){
                break;
            }
            
            betuk.get(vectorSzamlalo).add(keszlet.pixelek[pixelSzamlalo]);
            
            pixelSzamlalo++;
        }
    }
    
    public int[] getBetu(char betu,int color){
        int poz = (int)betu - 32;
        System.out.println(betu+" - "+(int)betu);
        if(poz<0 || poz>betuk.size()) poz=0;
        int[] t = new int[betuk.size()];
        for (int i = 0; i < betuk.get(poz).size(); i++) {
            t[i] = betuk.get(poz).get(i);
            if(t[i] == 0xffffffff);
        }
        return t;
    }
    
    public int getBetuSzel(char betu){
        int poz = (int)betu - 32;
        if(poz<0 || poz>betuk.size()) poz=0;
        int szel = betuk.get(poz).size();
        szel /= mag;
        return szel;
    }
    
    public int getBetuMag(){
        return mag;
    }
}
