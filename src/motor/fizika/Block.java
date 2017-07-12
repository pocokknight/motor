package motor.fizika;
public class Block {

    protected int id;
    protected int szin;
    protected boolean szilardLent,szilardFent,szilardJobb,szilardBall;
    protected int athatolhatosag;

    public Block(int id, int szin, boolean szilardLent, boolean szilardFent, boolean szilardJobb, boolean szilardBall) {
        this.id = id;
        this.szin = szin;
        this.szilardLent = szilardLent;
        this.szilardFent = szilardFent;
        this.szilardJobb = szilardJobb;
        this.szilardBall = szilardBall;
        this.athatolhatosag = FenyForras.TELI;
    }

    public int getId() {
        return id;
    }

    public int getSzin() {
        return szin;
    }

    public boolean isSzilardLent() {
        return szilardLent;
    }

    public boolean isSzilardFent() {
        return szilardFent;
    }

    public boolean isSzilardJobb() {
        return szilardJobb;
    }

    public boolean isSzilardBall() {
        return szilardBall;
    }
    
    public static double relativTavNegyzete(double X1, double Y1, double X2, double Y2){
        return (X1 - X2)*(X1 - X2) + (Y1 - Y2)*(Y1 - Y2);
    }

    int getAtl() {
        return athatolhatosag;
    }
    
    
    
}
