package com.sudVacioBT;

public class Main {

    public static void main(String[] args) {
        int [][] tablero = new int [9][9];
        boolean exito= sudokuBT(tablero, 0,0);
        if(exito){
            imprimir(tablero);
        }else{
            System.out.println("No hay sol");
        }
    }
    public static boolean sudokuBT(int[][]tablero, int etapax, int etapay){
        boolean exito=false;
        for(int intento=1; intento<=9 && !exito; intento++){
            //tablero[etapax][etapay]=¿intento?
            if(esFactible(tablero,etapax,etapay, intento)){
                tablero[etapax][etapay]=intento;
                if((etapax==8)&&(etapay==8)){
                    exito=true;
                }else{
                    int nuevax=dameNuevaX(tablero, etapax, etapay);
                    int nuevay=dameNuevaY(tablero, etapax, etapay);
                    exito=sudokuBT(tablero, nuevax, nuevay);
                }
                if(!exito){
                    tablero[etapax][etapay]=0;
                }
            }
        }
        return exito;
    }
    public static boolean esFactible( int [][]tablero, int etapax, int etapay, int intento){
        //Miramos que intento no esté en las filas:
        for(int fila=0; fila<tablero.length; fila++){
            if(tablero[fila][etapay]==intento){
                return false;
            }
        }
        //Miramos que no esté en las columnas:
        for(int columna=0; columna<tablero.length; columna++){
            if(tablero[etapax][columna]==intento){
                return false;
            }
        }
        //Vamos a ver que no esté en un cuadrado 3x3
        int iniciox= dameInicio(etapax);
        int inicioy= dameInicio(etapay);
        for(int x=iniciox; x<(iniciox+3); x++) {
            for (int y = inicioy; y < (inicioy + 3); y++) {
                if (tablero[x][y] == intento) {
                    return false;
                }
            }
        }
        return true;
    }
    public static int dameInicio(int etapa){
        switch (etapa){
            case 0:
            case 1:
            case 2: return 0;
            case 3:
            case 4:
            case 5: return 3;
            case 6:
            case 7:
            case 8: return 6;
        }
        return -1;
    }

    public static int dameNuevaX (int [][]tablero, int etapax, int etapay){
        if(etapay<tablero.length-1){
            return etapax;
        }else{
            return etapax+1;
        }
    }
    public static int dameNuevaY (int [][]tablero, int etapax, int etapay){
        if(etapay<tablero.length-1){
            return etapay+1;
        }else{
            return 0;
        }
    }

    public static void imprimir(int [][] tablero){
        for(int filas=0; filas<tablero.length; filas++){
            for(int col=0; col<tablero.length; col++){
                System.out.print(tablero[filas][col]+" ");
            }
            System.out.println();
        }
    }
}
