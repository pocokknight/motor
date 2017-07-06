package motor.fizika;
public class FenyForras {

    private int szin;
    private int ero,pozX,pozY;

    public FenyForras(int szin, int ero, int pozX, int pozY, Vilag v) {
        this.szin = szin;
        this.ero = ero;
        
        setFeny(pozX,pozY,v);
    }

    private void setFeny(int pozX, int pozY, Vilag v) {
        this.pozX = pozX;
        this.pozY = pozY;
        
        for (int i = pozX-ero; i < pozX+ero+1; i++) {
            for (int j = pozY-ero; j < pozY+ero+1; j++) {
                double tav = ero*ero - Block.relativTavNegyzete(i, j, pozX, pozY);
                if(tav > 0){
                    int color = 0x00000000;
                    int alpha = 255 - (int)((255/(ero*ero))*tav);
                    color = color | (alpha << 24);
                    try{
                        v.getFENY()[i][j] = color;
                    }catch(Exception e){}
                }
            }
        }
    }
    
    
}
