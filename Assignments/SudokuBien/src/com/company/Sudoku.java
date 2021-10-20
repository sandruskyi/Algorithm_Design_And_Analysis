package com.company;

public class Sudoku {

    public static void main(String[] args) {
        int[][]tablero=new int [9][9];
        //tablero[0][0]=7;
        //tablero[3][3]=3;
        boolean exito= BTUno(tablero, 0,0);
        System.out.println("Backtracking varios");
        //backtrackingVarios(tablero,0,0);

        System.out.println("Backtracking una solución");
        if(exito){
            imprimir(tablero);
        }else{
            System.out.println("No existe solución");
        }
    }

    public static boolean BTUno ( int [][]tablero, int etapax, int etapay) {
        boolean exito = false; //Si me llamas es que no hay exito
        if(tablero[etapax][etapay]!=0){
            if((etapax==tablero.length-1)&&(etapay==tablero.length-1)){
                exito= true;
            }else{
                int nuevax= dameNuevaX(tablero, etapax, etapay);
                int nuevay= dameNuevaY(tablero, etapax, etapay);
                exito= BTUno(tablero,nuevax, nuevay );
            }

        }else {
            for (int intento = 1; intento <= 9 && !exito; intento++) {
                //Tablero[etapax][etapay]=¿intento?
                if (esFactible(tablero, etapax, etapay, intento)) {
                    tablero[etapax][etapay] = intento;
                    if ((etapax == tablero.length - 1) && (etapay == tablero.length - 1)) {
                        exito = true;
                    } else {
                        int nuevax = dameNuevaX(tablero, etapax, etapay);
                        int nuevay = dameNuevaY(tablero, etapax, etapay);
                        exito = BTUno(tablero, nuevax, nuevay);
                    }
                    if (!exito) {
                        tablero[etapax][etapay] = 0;
                    }
                }
            }
        }
        return exito;
    }

    public static void imprimir(int[][]tablero){
        for(int filas=0; filas<tablero.length; filas++) {
            for (int columnas = 0; columnas < tablero.length; columnas++) {
                System.out.print(tablero[filas][columnas]+" ");
            }
            System.out.println();
        }
    }
    public static int dameNuevaX(int[][]tablero, int etapax, int etapay){
        if(etapay<tablero.length-1){
            return etapax;
        }else {
            return etapax + 1;
        }
    }
    public static int dameNuevaY(int [][]tablero, int etapax, int etapay){
        if(etapay<tablero.length-1){
            return etapay + 1 ;
        }else {
            return 0;
        }
    }

    public static boolean esFactible(int[][]tablero, int etapax, int etapay, int intento){
        for(int fila=0; fila<=tablero.length-1;fila++) { //Miramos si hay otro número = que intento en la fila
            if (tablero[fila][etapay] == intento) {
                return false;
            }
        }
        for(int columna=0; columna<=tablero.length-1; columna++){ //Miramos si hay otro número = que intento en la columna
            if(tablero[etapax][columna]==intento){
                return false;
            }
        }
        //Miramos si hay otro nº = que intento en el cuadrante
        int iniciox= dameInicio(etapax);
        int inicioy= dameInicio(etapay);
        for(int i=iniciox; i<iniciox+3; i++){
            for(int j=inicioy; j<inicioy+3; j++){
                if(tablero[i][j]==intento){
                    return false;
                }
            }
        }
        return true;
    }

    public static int dameInicio(int inicio){
        switch(inicio){
            case 0:
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
            case 5:
                return 3;
            case 6:
            case 7:
            case 8:
                return 6;
        }
        return 0;
    }
    /*public static void backtrackingVarios(int [][]tablero, int etapax, int etapay ){
        if (tablero[etapax][etapay]!=0){
            if((etapax==tablero.length-1)&&(etapay==tablero.length-1)){
                imprimir(tablero);
            }else{
                int nuevax= dameNuevaX(tablero, etapax, etapay);
                int nuevay= dameNuevaY(tablero, etapax, etapay);
                backtrackingVarios(tablero, nuevax, nuevay);
            }
        }else{
            for(int intento=1; intento<=9 ; intento++){
                //tablero[etapax][etapay]=¿intento?
                if(esFactible(tablero, etapax, etapay, intento)){
                    tablero[etapax][etapay]= intento;
                    if()
                }
            }
        }
    }*/
}
