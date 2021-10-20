/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tresenraya;


public class TresEnRaya {

    public static void imprimir (int solucion[][]) {
        for (int i=0; i<solucion.length; i++) {
            for (int j=0; j<solucion.length; j++) {
                System.out.print (solucion[i][j]+" ");
            }
            System.out.println ("");
        }
    }
    
    public static boolean esFactible (int solucion[][], int x, int y) {
        return (solucion[x][y]==0);
    }
    
    public static int esSolucion (int solucion[][], int x, int y, int jugador) {
        //Buscamos si el jugador es ganador
        boolean ok=true;
        for (int i=0; i<=2; i++) {
            if (solucion[x][i]!=jugador) {
                ok=false;
            }
        }
        if (ok)
            return jugador;
        
        ok=true;
        for (int i=0; i<=2; i++) {
            if (solucion[i][y]!=jugador) {
                ok=false;
            }
        }
        if (ok)
            return jugador;
        
        if (((solucion[0][0]==jugador) && (solucion[1][1]==jugador) && (solucion[2][2]==jugador)) ||
           ((solucion[2][0]==jugador) && (solucion[1][1]==jugador) && (solucion[0][2]==jugador)))
            return jugador;
        
        int cont_diferentes_a_cero=0;
        
        for (int i=0; i<solucion.length; i++) {
            for (int j=0; j<solucion.length; j++) {
                if (solucion[i][j]!=0)
                    cont_diferentes_a_cero++;
            }
        }
        if (cont_diferentes_a_cero==9) {
            return 3;
        }     
        return 0;
    }
    
    public static int DamePosicionX (int intento) {
        switch (intento) {
            case 0:
            case 1:
            case 2: return 0;
            case 3:
            case 4:
            case 5: return 1;
            case 6:
            case 7:
            case 8: return 2;
            
        }
        return -1;
    }
    public static int DamePosicionY (int intento) {
        switch (intento) {
            case 0:
            case 3:
            case 6: return 0;
            case 1:
            case 4:
            case 7: return 1;
            case 2:
            case 5:
            case 8: return 2;
            
        }
        return -1;
    }
    public static void BT (int solucion[][], int etapa, int contadores[]) {
        for (int intento=0; intento<9; intento++) {
            int posicionx=DamePosicionX (intento);
            int posiciony=DamePosicionY (intento);
            if (esFactible (solucion, posicionx, posiciony)) {
                solucion[posicionx][posiciony]=etapa;
                int r = esSolucion(solucion, posicionx,posiciony, etapa);
                if (r>0) {
                    //imprimir (solucion);
                    //System.out.println ("*****************");
                    if (r==1) {
                        contadores[0]++;
                    }
                    if (r==2) {
                        contadores[1]++;
                    }
                    if (r==3) {
                        contadores[2]++;
                    }
                }else {
                    if (etapa==1) {
                        BT (solucion, 2, contadores);
                    }else {
                        BT (solucion, 1, contadores);
                    }
                }
                solucion[posicionx][posiciony]=0;
            }
            
        }
    }
    public static void main(String[] args) {
        int solucion[][] = new int[3][3];
        int contadores[] = new int [3];
        BT (solucion,1, contadores);
        System.out.println ("Ganador 1: "+contadores[0]);
        System.out.println ("Ganador 2: "+contadores[1]);
        System.out.println ("Empate: "+contadores[2]);
        
    }
    
}
