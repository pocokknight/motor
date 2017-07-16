package motor.grafika;

import motor.Render;

public class Forgato {

    public static double f = 0;
    
    /*public int[] forgat(int[] p, int s, int m, int origoX, int origoY, double fok, Render r){
    
        int w = s - origoX;
        int ujS = w >= origoX ? w * 2 : origoX * 2;
        
        
        r.ujSzel = ujS;
        
        int h = m - origoY;
        int ujM = h >= origoY ? h * 2 : origoY * 2;
        
        int kX = origoX > s/2 ? 0 : ujS - s ;
        int kY = origoY > m/2 ? 0 : ujM - m ;
        
        int[] eredmeny = new int[ujS * ujM];
        
        for (int i = 0; i < eredmeny.length; i++) {
            eredmeny[i] = 0x00000000;
        }
        
        for (int i = kX; i < ujS; i++) {
            for (int j = kY; j < ujM; j++) {
                try{
                eredmeny[i + j * ujS] = p[(i - kX) + (j-kY) * s];
                }catch(Exception e){}
            }
        }
        
        eredmeny = forgat(eredmeny, ujS, ujM, fok);
        
        return eredmeny;
    }*/
    
    public int[] forgat(int[] p, int s, int m, double fok){
    
        int[] eredmeny = new int[s * m];
        
        double nx_x = forgX(fok,1.0,0.0);
        double nx_y = forgY(fok,1.0,0.0);
        double ny_x = forgX(fok,0.0,1.0);
        double ny_y = forgY(fok,0.0,1.0);
        
        double x0 = forgX(fok, -s / 2.0, -m / 2.0) + s / 2.0;
        double y0 = forgY(fok, -s / 2.0, -m / 2.0) + m / 2.0;
        
        for (int y = 0; y < m; y++) {
            double x1 = x0;
            double y1 = y0;
            for (int x = 0; x < s; x++) {
                int xx = (int) x1;
                int yy = (int) y1;
                int szin = 0;
                if(xx < 0 || xx >= s || yy < 0 || yy >= m) szin = 0x00000000;
                else szin = p[xx + yy * s];
                eredmeny[x + y * s] = szin;
                x1 += nx_x;
                y1 += nx_y;
            }
            x0 += ny_x;
            y0 += ny_y;
        }
        /*
        eredmeny[0] = 0xffff0000;
        eredmeny[s-1] = 0xffff0000;
        eredmeny[s*m-s] = 0xffff0000;
        eredmeny[s*m-1] = 0xffff0000;
        */
        return eredmeny;
    }
    
    private double forgX(double fok,double x, double y){
        double cos = Math.cos(fok);
        double sin = Math.sin(fok);
        return x * cos + y * -sin;
    }
    
    private double forgY(double fok,double x, double y){
        double cos = Math.cos(fok);
        double sin = Math.sin(fok);
        return x * sin + y * cos;
    }
    
}
