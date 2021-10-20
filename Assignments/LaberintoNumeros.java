/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintonumeros;

import java.util.Scanner;

public class LaberintoNumeros {
    public static int [] mov_rel_x={-1,0,0,1};
    public static int [] mov_rel_y={0,1,-1,0};
    
    public static void Copiar (int tablero[][], int mejorTablero[][]) {
        int contX=0;
        for (int i=0; i<mejorTablero.length; i++) {
            for (int j=0; j<mejorTablero.length; j++) {
                mejorTablero[i][j]=tablero[i][j];
                if (mejorTablero[i][j]>0) {
                    contX++;
                }
            }
        }
        
        System.out.println ("Nueva Solucion con "+contX+" pasos");
    }
     public static boolean esMejor (int tablero[][],int etapaXactual, int etapaYactual,  int mejorTablero[][]) {
         /*
        int contX1=0; 
        for (int i=0; i<tablero.length; i++) {
            for (int j=0; j<tablero.length; j++) {
                if (tablero[i][j]>0) {
                    contX1++;
                }
            }
        }
         */
        int contX1=tablero[etapaXactual][etapaYactual];
        
        /*
        int contX2=0; 
        for (int i=0; i<mejorTablero.length; i++) {
            for (int j=0; j<mejorTablero.length; j++) {
                if (mejorTablero[i][j]>0) {
                    contX2++;
                }
            }
        }
        */
        int contX2=mejorTablero[mejorTablero.length-1][mejorTablero.length-1];
        
        return (contX1<contX2);
    }
    public static void imprimir(int tablero[][]) {

        System.out.println();
        System.out.println();
        System.out.println();

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static boolean esFactibleMejor (int tablero[][], int etapax, int etapay, int numerodepaso,
                                        int nueva_x, int nueva_y, int intento, int mejorTablero[][]) {
        
        if ((nueva_x<0) || (nueva_x>=tablero.length) || (nueva_y<0) || (nueva_y>=tablero.length) )
            return false;
        if ((tablero[nueva_x][nueva_y]==-1) || (tablero[nueva_x][nueva_y]>0))
            return false;
        
        if (esMejor (mejorTablero,etapax,etapay,tablero)) {
            return false;
        }
        
        
        return true;                            
    }
    public static void BTMejor (int tablero[][], int etapax, int etapay, int numerodepaso, int mejorTablero[][]) {
        for (int intento=0; intento<mov_rel_x.length; intento++) {
            int nueva_x = etapax+mov_rel_x[intento];
            int nueva_y = etapay+mov_rel_y[intento];
            //tablero[nueva_x][nueva_y]='X'; ??
            if (esFactibleMejor (tablero, etapax, etapay, numerodepaso, nueva_x, nueva_y, intento, mejorTablero)) {
                tablero[nueva_x][nueva_y]=numerodepaso;
                if ((nueva_x==tablero.length-1) && (nueva_y==tablero.length-1)) {
                    //System.out.println ("Solucion encontrada:");
                    if (esMejor (tablero,tablero.length-1, tablero.length-1,  mejorTablero)) {
                        Copiar (tablero, mejorTablero);
                    }
                }else {
                    BTMejor (tablero, nueva_x, nueva_y, numerodepaso+1, mejorTablero);
                }
                tablero[nueva_x][nueva_y]=0;
            }        
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());

        int tablero[][] = new int[n][n];
        int mejorTablero[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            String linea = in.nextLine();
            String trozos[] = linea.split(" ");
            for (int j = 0; j < n; j++) {
                tablero[i][j] = Integer.parseInt(trozos[j]);
            }
        }
        
        for (int i=0; i<mejorTablero.length; i++) {
            for (int j=0; j<mejorTablero.length; j++) {
                mejorTablero[i][j]=mejorTablero.length*mejorTablero.length;
            }
        }
        
        tablero[0][0]=1;
        BTMejor (tablero, 0,0,2,mejorTablero);
        imprimir (mejorTablero);

    }

}
