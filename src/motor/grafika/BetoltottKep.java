package motor.grafika;

import jatek.res.img.ImgJelolo;
import jatek.res.anim.AnimJelolo;
import jatek.res.font.FontJelolo;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class BetoltottKep {
    
    protected int szel,mag;
    protected int[] pixelek;
    
    public BetoltottKep(Class c,String nev){
        kepBetoltes(c,nev);
    }

    public void kepBetoltes(Class c,String nev){
        BufferedImage img = null;
        try{
            img = ImageIO.read(c.getResource(nev));
        }catch(Exception e){System.out.println(e);}
        
        szel = img.getWidth();
        mag = img.getHeight();
        pixelek = img.getRGB(0, 0, szel, mag, null, 0, szel);
        
        img.flush();
    }
    
    public void update(double dt){
        //kepnel nem szamit
    }
    
    public void setSzel(int szel) {
        this.szel = szel;
    }

    public void setMag(int mag) {
        this.mag = mag;
    }

    public void setPixelek(int[] pixelek) {
        this.pixelek = pixelek;
    }

    public int getSzel() {
        return szel;
    }

    public int getMag() {
        return mag;
    }

    public int[] getPixelek() {
        return pixelek;
    }
}
