package com.nReinasBT;

public class Main {

    public static boolean esFactible (int solucion[], int etapa, int intento) {

        //solucion[etapa]=intento;??
        for (int i=0; i<etapa;i++){
            if (solucion[i]==intento) {
                return false;
            }
        }

        int resta=Math.abs (etapa-intento);
        for (int i=0; i<etapa;i++){
            if ((solucion[i]-i) == resta) {
                return false;
            }
        }


        int suma=etapa+intento;
        for (int i=0; i<etapa;i++){
            if ((solucion[i]+i) == suma) {
                return false;
            }
        }

        return true;
    }
    public static void imprimir (int solucion []) {
        for (int i = 0; i< solucion.length; i++) {
            System.out.print (solucion[i]+" ");
        }
        System.out.println ("");
    }
    public static boolean BT (int solucion[], int etapa) {
        boolean exito = false;
        for (int intento = 0; intento<8 && !exito; intento++) {
            //¿solucion[etapa]=intento?
            if (esFactible (solucion,  etapa, intento )) {
                solucion[etapa]=intento;
                if (etapa==solucion.length-1) {
                    exito=true;
                }else {
                    exito = BT (solucion, etapa+1);
                }

                //Desmarcar
                if (!exito) {
                    solucion[etapa]=0;
                }
            }
        }

        return exito;
    }
    public static void main(String[] args) {
        int solucion [] = new int [8];
        boolean exito;

        exito = BT (solucion, 0);
        if (exito) {
            imprimir (solucion);
        }else {
            System.out.println ("No hay solucion");
        }
    }
}
