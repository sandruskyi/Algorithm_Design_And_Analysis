/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socios;

import java.util.Scanner;

/**
 *
 * @author jvergara
 */
public class Socios {

    public static boolean esFactible (int []cosas, int []solucion, int etapa, int intento) {
        //¿solucion[etapa]=intento?
        //Aquí calculamos el total de los valores de los bienes
        int total=0;
        for (int i=0; i<cosas.length; i++) {
            total = total + cosas[i];
        }
        
        //Calculamos lo que lleva repartido A y B
        int contA=0, contB=0;
        for (int i=0; i<etapa;i++) {
            if (solucion[i]==1) {
                contA=contA + cosas[i];
            }else {
                contB=contB + cosas[i];
            }
        }
        
        if (intento==1) {
            if (contA+cosas[etapa]>(total/2)) {
                return false;
            }
            if (etapa==cosas.length-1) {
                if (contA+cosas[etapa]!=(total/2)) {
                    return false;
                }
            }
        }
        
        if (intento==2) {
            if (contB+cosas[etapa]>(total/2)) {
                return false;
            }
            if (etapa==cosas.length-1) {
                if (contB+cosas[etapa]!=(total/2)) {
                    return false;
                }
            }
        }
        
        return true;
        
    }
    public static boolean reparto (int []cosas, int []solucion, int etapa) {
        boolean exito = false;
        
        for (int intento=1; intento<=2 && !exito; intento++) {
            //Hacemos nuestro trabajo
            //¿solucion[etapa]=intento?
            if (esFactible (cosas, solucion, etapa, intento)) {
                solucion[etapa]=intento;
                imprimir (solucion);
                if (etapa==cosas.length-1) {
                    exito=true;
                }else {
                    exito=reparto (cosas, solucion, etapa+1);
                }
                
                if (!exito) {
                    //Deshacer el trabajo hecho
                    solucion[etapa]=0;
                    imprimir (solucion);
                }
            }
        }
        return exito;
    }
    public static boolean reparto (int []cosas, int []solucion) {
        return reparto (cosas, solucion, 0);
    }
    
    public static void imprimir (int solucion[]) {
        System.out.print ("[");
        for (int i=0; i< solucion.length; i++) {
            System.out.print (solucion[i]+ " ");
        }
        System.out.println ("]");
        Scanner entrada = new Scanner (System.in);
        entrada.nextLine();
    }
    
    public static void main(String[] args) {
        int cosas[] = {300, 20, 50, 150, 400, 320,20,10,5,5};
        int solucion [] = new int [cosas.length];
        
        boolean exito = reparto (cosas, solucion);
        if (exito) {
            imprimir (solucion);
        }else {
            System.out.println ("No hay solucion");
        }
    }
    
}
