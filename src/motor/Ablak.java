package motor;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Ablak{
    
    private JatekMag jm;
    
    private int kepernyoX,kepernyoY;
    private int egerPozX,egerPozY;
    
    private String nev = "Pocok Engine 0.1 - Still better than Rocket League";
    private JFrame ablak;
    private BufferedImage kep;
    private Canvas vaszon;
    private BufferStrategy bs;
    private Graphics g;
    
    Ablak(JatekMag jm){
        egerPozX = 0;
        egerPozY = 0;
        this.jm = jm;
        kepernyoX = jm.getSzel();
        kepernyoY = jm.getMag();
        
        kep = new BufferedImage(kepernyoX, kepernyoY, BufferedImage.TYPE_INT_RGB);
        vaszon = new Canvas();
        
        ablak = new JFrame(nev);
        ablak.setLayout(new BorderLayout());
        ablak.add(vaszon, BorderLayout.CENTER);
        
        vaszon.setSize(kepernyoX,kepernyoY);
        ablak.pack();
        ablak.setLocationRelativeTo(null);
        ablak.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ablak.setResizable(false);
        //ablak.setUndecorated(true);
        ablak.setVisible(true);
        
        vaszon.createBufferStrategy(2);
        bs = vaszon.getBufferStrategy();
        g = bs.getDrawGraphics();
    }
    
    public void frissit(){
        //ide kell beépítenem ha több réteget akarok egyszerűen több "kep" kell!
        g.drawImage(kep, 0, 0, vaszon.getWidth(), vaszon.getHeight(), null);
        bs.show();
    }
    
    BufferedImage getKep(){
        return kep;
    }

    int getSzel() {
        return kepernyoX;
    }

    int getMag() {
        return kepernyoY;
    }

    Canvas getVaszon() {
        return vaszon;
    }

    boolean vanKeret() {
        return ablak.isUndecorated();
    }
}
