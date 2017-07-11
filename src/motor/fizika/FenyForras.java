package motor.fizika;
public class FenyForras {

    private int r,d;
    private int szin;
    private int ero;
    private int[] pixelek;

    public FenyForras(int r, int ero, int szin) {
        this.r = r;
        this.d = 2*r;
        this.szin = szin;
        this.ero = ero > 255 ? 255 : ero < 0 ? 0 : ero;
        setPixel();
    }

    private void setPixel() {
        pixelek = new int[d*d];
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                if(i+j*d < pixelek.length){
                    double tav = Block.relativTavNegyzete(i, j, r, r);
                    if(tav > r*r){
                        pixelek[i+j*d] = 0xff000000;
                    }else{
                        double e = (1 - (tav / (r*r))) * (ero/255.0);
                        pixelek[i+j*d] = (
                                (int)(((szin >> 16) & 0xff ) * e) << 16 |//r
                                (int)(((szin >> 8) & 0xff ) * e) << 8 |//g
                                (int)((szin & 0xff ) * e) //b
                                );
                    }
                }
            }
        }
    }

    public int getR() {
        return r;
    }

    public int getD() {
        return d;
    }

    public int getSzin() {
        return szin;
    }

    public int getEro() {
        return ero;
    }

    public int[] getPixelek() {
        return pixelek;
    }
 
    
    
}
