package com.nReinasBT;

public class Main {

    public static void main(String[] args) {
	    int []solucion=new int [8];
	    boolean exito= nReinasBT(solucion, 0);
	    if(exito){
	        imprimir(solucion);
        }else{
	        System.out.print("No existe solucion");
        }
    }
    public static boolean nReinasBT(int [] solucion, int etapa){
        boolean exito=false;
        for(int intento=0; intento<solucion.length && !exito; intento++){
            //solucion[etapa]=¿intento?
            if(esFactible(solucion,etapa,intento)){
                solucion[etapa]=intento;
                if(etapa==(solucion.length-1)) {
                    exito = true;
                }else{
                    exito=nReinasBT(solucion, etapa+1);
                }
                if(!exito){
                    solucion[etapa]=0;
                }
            }
        }
        return exito;
    }
    public static boolean esFactible(int [] solucion, int etapa, int intento){
        //solucion[etapa]=¿intento?
        //No puede haber 2 reinas en la misma columna:
        for(int i=0; i<etapa; i++){
            if(solucion[i]==intento){
                return false;
            }
        }
        //No puede haber 2 reinas en la misma diagonal:
        //Diagonal resta =:
        for(int i=0; i<etapa; i++){
            if(Math.abs(solucion[i]-intento)==Math.abs(i-etapa)){
                return false;
            }
        }
        return true;
    }
    public static void imprimir (int [] solucion){
        for(int i=0; i<solucion.length;i++){
            System.out.println("Ponemos la reina en la fila "+i+" columna "+solucion[i]);
        }
    }
}
