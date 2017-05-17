package motor;
public class JatekMag implements Runnable{
    
    private Thread thread;
    private Ablak ablak;
    private Render render;
    private Bevitel bevitel;
    private AbsztraktJatek jatek;
    
    private final int FPS = 120;
    private int kepernyoX,kepernyoY;
    private boolean fut = false;
    private boolean kellRender = false;
    
    private double most = 0;
    private double elozoIdo = 0;
    private double feldolgozatlanIdo = 0;
    private double elteltIdo = 0;
    
    private double egyKepIdeje = 0;
    
    private double fpsido = 0;
    private int fps = 0;
    private int framek = 0;
    
    public JatekMag(AbsztraktJatek jatek){
        this.jatek = jatek;
    }

    public void start() {
        fps = FPS;
        egyKepIdeje = 1.0f / fps;
        kepernyoX = 1000;
        kepernyoY = 600;
        ablak = new Ablak(this);
        render = new Render(this);
        bevitel = new Bevitel(this);
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        fut = false;
    }

    @Override
    public void run() {
        fut = true;
        elozoIdo = System.nanoTime()/1000000000.0;
        
        while (fut) {
            kellRender = false;
            
            most = System.nanoTime()/1000000000.0;
            elteltIdo = most - elozoIdo;
            
            feldolgozatlanIdo += elteltIdo;
            fpsido += elteltIdo;
            
            while(feldolgozatlanIdo >= egyKepIdeje){
                feldolgozatlanIdo -= egyKepIdeje;
                
                //fizika
                jatek.frissit(this, (float)egyKepIdeje);
                
                //bevitel
                bevitel.update();
                
                kellRender = true;
                
                if(fpsido > 1){
                    fpsido--;
                    fps = framek;
                    framek = 0;
                    
                    System.out.println(fps);
                }
            }
            
            if(kellRender){
                render.clear();
                jatek.render(this, render);
                ablak.frissit();
                framek++;
            }else{
                try{
                    Thread.sleep(1);
                }catch(Exception e){System.out.println("Hiba a magban.");}
            }
            
            elozoIdo = most;
        }
    }


    public Render getRender() { return render; } 
    public Ablak getAblak() { return ablak; }
    public Bevitel getBevitel() { return bevitel; } 
    public AbsztraktJatek getJatek() { return jatek; } 
    public int getFps() { return fps; }
    int getSzel() { return kepernyoX; }
    int getMag() { return kepernyoY; }
    
}
