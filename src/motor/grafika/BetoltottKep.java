package motor.grafika;

import jatek.res.img.IMGJelolo;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class BetoltottKep {
    
    private int szel,mag;
    private int[] pixelek;
    
    public BetoltottKep(String nev){
        
        BufferedImage img = null;
        try{
            img = ImageIO.read(IMGJelolo.class.getResource(nev));
        }catch(Exception e){System.out.println(e);}
        
        szel = img.getWidth();
        mag = img.getHeight();
        pixelek = img.getRGB(0, 0, szel, mag, null, 0, szel);
        
        img.flush();
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
