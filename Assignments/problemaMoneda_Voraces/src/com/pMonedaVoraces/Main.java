package com.pMonedaVoraces;

public class Main {

    public static void main(String[] args) {
        int [] monedas= {200,100,50,20,10,5,2,1};
        int cantidad=563;
        int [] cantidades= new int [monedas.length];
        int numMonedas= cambioMonedas(monedas,cantidad,cantidades);
        for(int i=0; i<monedas.length;i++){
            System.out.println("Damos "+cantidades[i]+" monedas de "+monedas[i]);
        }
        System.out.println("En total pagamos con "+ numMonedas+ " monedas");
    }
    public static int cambioMonedas(int [] monedas, int cantidad, int [] cantidades){
        int numMonedas=0;
        for(int etapa=0; etapa<monedas.length;etapa++){
            int cociente=cantidad/monedas[etapa];
            int resto= cantidad % monedas[etapa];
            cantidades[etapa]=cociente;
            cantidad=resto;
            numMonedas+=cociente;
        }
        return numMonedas;
    }
}
