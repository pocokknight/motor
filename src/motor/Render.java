package motor;

import java.awt.image.*;
import motor.grafika.*;

public class Render {

    private JatekMag jm;
    
    private int kepernyoX,kepernyoY;
    private int[] pixelek;
    private int[] fenyterkep;
    private int[] fenyblokk;
    
    Render(JatekMag jm){
        this.jm = jm;
        kepernyoX = jm.getSzel();
        kepernyoY = jm.getMag();
        pixelek = ((DataBufferInt)this.jm.getAblak().getKep().getRaster().getDataBuffer()).getData();
        fenyterkep = new int[pixelek.length];
        fenyblokk = new int[pixelek.length];
    }

    public void fenyellenorzes() {
        for (int i = 0; i < pixelek.length; i++) {
            
            float r = ((fenyterkep[i] >> 16) & 0xff) / 255f;
            float g = ((fenyterkep[i] >> 8) & 0xff) / 255f;
            float b = (fenyterkep[i] & 0xff) / 255f;
            
            pixelek[i] = ((int)(((pixelek[i] >> 16) & 0xff) *r) << 16 | (int)(((pixelek[i] >> 8) & 0xff) *g) << 8 | (int)((pixelek[i] & 0xff) *b));
        }
        fenyterkep = new int[pixelek.length];
    }
    
    public void setPixel(int x, int y, int ertek){
        
        int alpha = ((ertek >> 24) & 0xff);
        
        if(alpha == 0){
            return;
        }
        if(alpha == 255){
            pixelek[x+y*kepernyoX] = ertek;    
        }else{
            int pixelszin = pixelek[x+y*kepernyoX];
            
            int ujpiros = ((pixelszin >> 16) & 0xff) - (int)((((pixelszin >> 16) & 0xff) - ((ertek >> 16) & 0xff)) * (alpha / 255f));
            int ujzold  = ((pixelszin >>  8) & 0xff) - (int)((((pixelszin >>  8) & 0xff) - ((ertek >>  8) & 0xff)) * (alpha / 255f));
            int ujkek   = ( pixelszin & 0xff) - (int)(((pixelszin & 0xff) - (ertek & 0xff)) * (alpha / 255f));
            
            pixelek[x+y*kepernyoX] = (255 << 24 | ujpiros << 16 | ujzold << 8 | ujkek);
        }
    }
    
    public void setFenyTerkep(int x, int y, int szin){
        //nincs kirajzolás
        if(x < 0) return;
        if(y < 0) return;
        if(x >= kepernyoX) return;
        if(y >= kepernyoY) return;
        
        int alapszin = fenyterkep[x + y * kepernyoX];
        
        int piros = Math.max((alapszin >> 16) & 0xff, (szin >> 16) & 0xff);
        int zold = Math.max((alapszin >> 8) & 0xff, (szin >> 8) & 0xff);
        int kek = Math.max(alapszin & 0xff, szin & 0xff);
        
        fenyterkep[x + y * kepernyoX] = (piros << 16 | zold << 8 | kek);
        
    }
    
    public void drawRect(int x,int y,int w, int h, int szin){
        //nincs kirajzolás
        if(x < -w) return;
        if(y < -h) return;
        if(x >= kepernyoX) return;
        if(y >= kepernyoY) return;
        
        int ujx = 0,ujy = 0;
        int ujszel = w,ujmag = h;
        
        //elvágott kód
        if(x < 0) ujx -= x;
        if(y < 0) ujy -= y;
        if(ujszel + x >= kepernyoX) ujszel -= ujszel+x-kepernyoX;
        if(ujmag + y >= kepernyoY) ujmag -= ujmag+y-kepernyoY;
        
        for (int i = ujx; i < ujszel; i++) {
            for (int j = ujy; j < ujmag; j++) {
                setPixel(i+x,j+y,szin);
            }
        }
    }
    
    public void drawSzoveg(Szoveg sz,String s,int szin,int x,int y,double sx, double sy){
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int szel = sz.getBetuSzel(c);
            drawKep(x,y,sz.getBetu(c, szin),szel,sx,sy);
            x += (int)(szel*sx);
        }
    }
    
    public void drawKep(int x, int y, int[] pixelek, int szel, double sx,double sy){
        
        int mag = pixelek.length / szel;
        int valszel = (int)(szel*sx);
        int valmag = (int)(mag*sy);
        
        //nincs kirajzolás
        if(x < -valszel) return;
        if(y < -valmag) return;
        if(x >= kepernyoX) return;
        if(y >= kepernyoY) return;
        
        int ujx = 0,ujy = 0;
        int ujszel = valszel,ujmag = valmag;
        
        //elvágott kód
        if(x < 0) ujx -= x;
        if(y < 0) ujy -= y;
        if(ujszel + x >= kepernyoX) ujszel -= ujszel+x-kepernyoX;
        if(ujmag + y >= kepernyoY) ujmag -= ujmag+y-kepernyoY;
        
        for (int i = ujx; i < ujszel; i++) {
            for (int j = ujy; j < ujmag; j++) {
                try{
                setPixel(i+x,j+y,pixelek[(int)(i/sx+(int)(j/sy)*szel)]);
                }catch(Exception e){}
            }
        }
    }
    
    public void drawKep(int x, int y, BetoltottKep kep){
        
        //nincs kirajzolás
        if(x < -kep.getSzel()) return;
        if(y < -kep.getMag()) return;
        if(x >= kepernyoX) return;
        if(y >= kepernyoY) return;
        
        int ujx = 0,ujy = 0;
        int ujszel = kep.getSzel(),ujmag = kep.getMag();
        
        //elvágott kód
        if(x < 0) ujx -= x;
        if(y < 0) ujy -= y;
        if(ujszel + x >= kepernyoX) ujszel -= ujszel+x-kepernyoX;
        if(ujmag + y >= kepernyoY) ujmag -= ujmag+y-kepernyoY;
        
        for (int i = ujx; i < ujszel; i++) {
            for (int j = ujy; j < ujmag; j++) {
                setPixel(i+x,j+y,kep.getPixelek()[i+j*kep.getSzel()]);
            }
        }
    }
    
    public void clear(){
        for (int i = 0; i < pixelek.length; i++) {
            pixelek[i] = 0xff999999;
        }
    }
}
