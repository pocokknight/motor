package motor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Bevitel implements KeyListener,MouseListener,MouseMotionListener,MouseWheelListener{
    
    private JatekMag jm;
    
    private int billSzam = 256;
    private boolean[] billentyuk = new boolean[billSzam];
    private boolean[] billentyukElozo = new boolean[billSzam];
    
    private int egerGombSzam = 10;
    private boolean[] egerGombok = new boolean[egerGombSzam];
    private boolean[] egerGombokElozo = new boolean[egerGombSzam];
    
    private int egerPozX,egerPozY;
    private int gorgo = 0;
    
    Bevitel(JatekMag jm){
        this.jm = jm;
        jm.getAblak().getVaszon().addKeyListener(this);
        jm.getAblak().getVaszon().addMouseListener(this);
        jm.getAblak().getVaszon().addMouseMotionListener(this);
        jm.getAblak().getVaszon().addMouseWheelListener(this);
    }

    public boolean isBillLenyomva(int bill){ return !billentyukElozo[bill] && billentyuk[bill]; }
    public boolean isBillFelengedve(int bill){ return billentyukElozo[bill] && !billentyuk[bill]; }
    public boolean isBillTart(int bill){ return billentyuk[bill]; }
    
    public boolean isEgerLenyomva(int eger){ return !egerGombokElozo[eger] && egerGombok[eger]; }
    public boolean isEgerFelengedve(int eger){ return egerGombokElozo[eger] && !egerGombok[eger]; }
    public boolean isEgerTart(int eger){ return egerGombok[eger]; }
    
    public void update(){
        
        gorgo = 0;
        
        System.arraycopy(egerGombok, 0, egerGombokElozo, 0, egerGombSzam);
        System.arraycopy(billentyuk, 0, billentyukElozo, 0, billSzam);
        
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        billentyuk[ke.getKeyCode()] = true;
        //ideiglenes leállító
        if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
        if(ke.getKeyCode() == KeyEvent.VK_F1) jm.getRender().changeHelp();
        if(ke.getKeyCode() == KeyEvent.VK_F2) jm.getRender().changeFeny();
        if(ke.getKeyCode() == KeyEvent.VK_F3) jm.getRender().changeColl();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        billentyuk[ke.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        egerGombok[me.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        egerGombok[me.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        egerPozX = me.getX();
        egerPozY = me.getY();
        //System.out.println(egerPozX+" "+egerPozY);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        egerPozX = me.getX();
        egerPozY = me.getY();
        //System.out.println(egerPozX+" "+egerPozY);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        gorgo = mwe.getWheelRotation();
    }

    public int getEgerPozX() {
        return egerPozX;
    }

    public int getEgerPozY() {
        return egerPozY;
    }

    public int getGorgo() {
        return gorgo;
    }
    
}
