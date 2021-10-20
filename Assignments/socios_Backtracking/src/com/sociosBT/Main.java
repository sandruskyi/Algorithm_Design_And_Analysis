package com.sociosBT;

public class Main {

    public static void main(String[] args) {
        int cosas []={300,50,150,400,320,20};
        int solucion[]= new int [cosas.length];
        boolean exito= reparto(cosas,solucion);
        if(exito) {
            imprimir(cosas, solucion);
        }else{
            System.out.println("No hay solucion");
        }
    }

    public static boolean reparto(int [] cosas, int [] solucion){
        return(repartoBT(cosas, solucion, 0));
    }

    public static boolean repartoBT(int [] cosas, int[] solucion, int etapa){
        boolean exito=false;
        for(int intento=1; intento<=2 && !exito ; intento++){
            //solucion[etapa]=¿intento?
            if(esFactible(cosas, solucion, etapa, intento)){
                solucion[etapa]=intento;
                if(etapa==(cosas.length-1)){
                    exito=true;
                }else{
                    exito=repartoBT(cosas, solucion, etapa+1);
                }
                if(!exito){
                    solucion[etapa]=0;
                }
            }
        }
        return exito;
    }
    public static boolean esFactible(int [] cosas, int [] solucion, int etapa, int intento){
        int total=0;
        for(int i=0; i<cosas.length; i++){
            total+=cosas[i];
        }
        int contA=0;
        int contB=0;
        for(int i=0; i<solucion.length; i++){
            if(solucion[i]==1){
                contA+=cosas[i];
            }
            if(solucion[i]==2){
                contB+=cosas[i];
            }
        }
        if(intento==1){
            if((contA+cosas[etapa])>(total/2)){
                return false;
            }
            if(etapa==cosas.length-1){
                if((contA+cosas[etapa])!=(total/2)){
                    return false;
                }
            }
        }
        if(intento==2){
            if((contB+cosas[etapa])>(total/2)){
                return false;
            }
            if(etapa==cosas.length-1){
                if((contB+cosas[etapa])!=(total/2)){
                    return false;
                }
            }
        }
        return true;
    }
    public static void imprimir(int [] cosas, int [] solucion){
        for(int i=0; i<solucion.length; i++){
            System.out.println("El objeto "+cosas[i]+" es para "+solucion[i]);
        }
    }
}
