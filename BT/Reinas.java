/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reinas;

import java.util.Scanner;

/**
 *
 * @author jvergara
 */
public class Reinas {

    public static boolean esFactible (int solucion[], int etapa, int intento) {
       
        //solucion[etapa]=intento;??
        for (int i=0; i<etapa;i++){
            if (solucion[i]==intento) {
                return false;
            }
        }
        
        for (int i=0; i<etapa;i++){          
            if (Math.abs(solucion[i]-intento)==Math.abs(i-etapa)) {
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
