package ejerc3BT;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner scan= new Scanner(System.in);
	    String entrada= scan.nextLine();
	    String[] en= entrada.split(" ");
	    int [] numeros= new int[en.length];
	    for(int i=0; i<en.length;i++){
	        numeros[i]=Integer.parseInt(en[i]);
        }
	    int [] solucion = new int [numeros.length]; //1 si se le reparte al vector 1 y 2 si se le reparte al vector 2
        vectorBT(numeros, solucion,0);
    }
    public static void vectorBT(int [] numeros, int[] solucion, int etapa){
        for(int intento=1; intento<=2; intento++){
            //solucion[etapa]=¿intento?
            if(esFactible(numeros,solucion, etapa, intento)){
                solucion[etapa]=intento;
                if(etapa==(solucion.length-1)){
                    imprimir(numeros,solucion);
                }else{
                    vectorBT(numeros,solucion, etapa+1);
                }
                solucion[etapa]=0;
            }
        }

    }
    public static boolean esFactible(int [] numeros, int [] solucion,int etapa, int intento) {
        //solucion[etapa]=¿intento?
        int suma1 = 0;
        int suma2 = 0;
        int sumaT = 0;
        for (int i = 0; i < numeros.length; i++) {
            sumaT += numeros[i];

        }
        for (int i = 0; i < etapa; i++) {
            if (solucion[i] == 1) {
                suma1 += numeros[i];
            } else if (solucion[i] == 2) {
                suma2 += numeros[i];
            }
        }

        if(intento==1){
            if ((suma1 + numeros[etapa]) > (sumaT / 2)) {
                return false;
            }
            if ((etapa == numeros.length - 1) && (suma1 + numeros[etapa] != (sumaT / 2))) {
                return false;
            }
        }
        if(intento==2) {

            if ((suma2 + numeros[etapa]) > (sumaT / 2)) {
                return false;
            }
            if ((etapa == numeros.length - 1) && (suma2 + numeros[etapa] != (sumaT / 2))) {
                return false;
            }
        }

        return true;
    }

    public static void imprimir(int [] numeros, int solucion[]){
        System.out.println("Una salida");
        for(int i=0; i<numeros.length; i++){
            if(solucion[i]==1){
                System.out.print(numeros[i]+" ");
            }
        }
        System.out.println();
        for(int i=0; i<numeros.length; i++){
            if(solucion[i]==2){
                System.out.print(numeros[i]+" ");
            }
        }
        System.out.println();
    }

}
