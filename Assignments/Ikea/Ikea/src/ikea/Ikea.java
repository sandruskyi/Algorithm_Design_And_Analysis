/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ikea;

import java.util.Scanner;


public class Ikea {
    public static int [] mov_rel_x={-1,0,0,1};
    public static int [] mov_rel_y={0,1,-1,0};
    
    public static void imprimir (int tablero[][]) {
        
        System.out.println ();
        System.out.println ();
        System.out.println ();
        
        for (int i=0; i<tablero.length; i++) {
            for (int j=0; j<tablero[0].length; j++) {
                System.out.print (tablero[i][j]+"  ");
            }
            System.out.println ();
        }
    }
    
    public static void Copiar (int tablero[][], int mejorTablero[][]) {
        int contX=0;
        for (int i=0; i<mejorTablero.length; i++) {
            for (int j=0; j<mejorTablero[0].length; j++) {
                mejorTablero[i][j]=tablero[i][j];
            }
        }
    }
    public static boolean esFactible (int tablero[][],
                                        int nueva_x, int nueva_y, int salida_x, int salida_y,
                                        int mejorTablero[][], int mejorDistancia[], int numPasos) {
        
        if ((nueva_x<0) || (nueva_x>=tablero.length) || (nueva_y<0) || (nueva_y>=tablero[0].length) )
            return false;
        if ((tablero[nueva_x][nueva_y]==0) || (tablero[nueva_x][nueva_y]==2) || (tablero[nueva_x][nueva_y]==8) ) //8 es visitada
            return false;
        
        if (numPasos>=mejorDistancia[0])
            return false;
        
        return true;                            
    }
    public static boolean todasAlcanzadas (boolean seccionesVisitadas []) {
        boolean alcanzadas=true;
        for (int i=0; i<seccionesVisitadas.length;i++) {
            if (!seccionesVisitadas[i])
                alcanzadas= false;
        }
        return alcanzadas;
    }
    public static void BTMejor (int tablero[][], int etapax, int etapay, int salidaX, int salidaY, 
                                int mejorTablero[][],int mejorDistancia[], int numPasos, boolean seccionesVisitadas []) {
        for (int intento=0; intento<mov_rel_x.length; intento++) {
            int nueva_x = etapax+mov_rel_x[intento];
            int nueva_y = etapay+mov_rel_y[intento];
            if (esFactible (tablero, nueva_x, nueva_y, salidaX, salidaY, mejorTablero, mejorDistancia, numPasos)) {
                if ((nueva_x==salidaX) && (nueva_y==salidaY) && (todasAlcanzadas(seccionesVisitadas))) {
                    if (mejorDistancia[0]>numPasos+1) {
                        Copiar (tablero, mejorTablero);
                        mejorDistancia[0]=numPasos+1;
                    }
                }else {
                    
                    int old = tablero[nueva_x][nueva_y];
                    boolean alcanzamosSeccionYAntesNoEstaba=false;
                    if (seccionesVisitadas[old]==false) {
                        seccionesVisitadas[old]=true;
                        alcanzamosSeccionYAntesNoEstaba=true;
                    }
                    tablero[nueva_x][nueva_y]=8;
                    /*
                    imprimir (tablero);
                    System.out.println (nueva_x+","+nueva_y);
                    Scanner entrada = new Scanner (System.in);
                    entrada.nextLine();
                    */
                    BTMejor (tablero, nueva_x, nueva_y, salidaX, salidaY, mejorTablero,mejorDistancia,numPasos+1, seccionesVisitadas);
                    tablero[nueva_x][nueva_y]=old;
                    if (alcanzamosSeccionYAntesNoEstaba) {
                        seccionesVisitadas[old]=false;
                    }
                }
            }        
        }
    }  
    
    public static void main(String[] args) {
        Scanner entrada = new Scanner (System.in);
        String linea = entrada.nextLine();
        String trozos[] = linea.split(" ");
        int numFilas = Integer.parseInt(trozos[0]);
        int numColumnas = Integer.parseInt(trozos[1]);
        int numSecciones = Integer.parseInt(trozos[2]);
        
        int entradaX=-1;
        int entradaY=-1;
        int salidaX=-1;
        int salidaY=-1;
        int tablero[][]=new int[numFilas][numColumnas];
        for (int i=0; i<numFilas; i++) {
            linea = entrada.nextLine();
            trozos=linea.split(" ");
            for (int j=0; j<numColumnas; j++) {
                tablero[i][j]=Integer.parseInt(trozos[j]);
                if (tablero[i][j]==0) {
                    entradaX=i;
                    entradaY=j;
                }
                if (tablero[i][j]==1) {
                    salidaX=i;
                    salidaY=j;
                }
            }
        }
        
        int mejorDistancia[]= new int[1];
        mejorDistancia[0]=numFilas*numColumnas;
        int mejorTablero[][]=new int[numFilas][numColumnas];
        
        boolean seccionesVisitadas [] = new boolean[numSecciones+3];
        seccionesVisitadas[0]=true;
        seccionesVisitadas[1]=true;
        seccionesVisitadas[2]=true;
        
        
        BTMejor (tablero, entradaX,entradaY,salidaX,salidaY,mejorTablero, mejorDistancia,1, seccionesVisitadas);
        imprimir (mejorTablero);
        System.out.println ("NumPasos: "+mejorDistancia[0]);
    }
    
}
/*
6 7 4
2 2 2 2 2 2 2
2 2 1 2 2 0 2
2 6 6 2 3 3 2
2 6 6 5 3 3 2
2 2 5 5 4 2 2
2 2 2 2 4 2 2

*/