package motor.fizika;
public class Block {

    protected int id;
    protected int szin;
    protected boolean szilardLent,szilardFent,szilardJobb,szilardBall;

    public Block(int id, int szin, boolean szilardLent, boolean szilardFent, boolean szilardJobb, boolean szilardBall) {
        this.id = id;
        this.szin = szin;
        this.szilardLent = szilardLent;
        this.szilardFent = szilardFent;
        this.szilardJobb = szilardJobb;
        this.szilardBall = szilardBall;
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
    
    
    
    
    
}
