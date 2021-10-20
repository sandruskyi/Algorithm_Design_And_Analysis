package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

       public static long coste;
       public static long carreteras;
       public static int islas;

       public static class Arista implements Comparable<Arista>{
           public int origen;
           public int destino;
           public long peso;

           public Arista(int origen, int destino, long peso) {
               this.origen = origen;
               this.destino = destino;
               this.peso = peso;
           }

           public int compareTo (Arista otraArista){
               if(otraArista.peso == this.peso){
                   //soloparaSet por si no admite repetidos
                   /*
                   if(otraArista.origen == this.origen){

                       if(this.destino>otraArista.destino){ //menorDestino
                           return 1;
                       }else{
                           return -1;
                       }

                   }else{
                       return 1;
                   }
                   */

               }if(this.peso>otraArista.peso){ //mayor coste
                   return -1;
               }else{
                   return 1;
               }
           }
       }

           public static List<Arista> primIslas(List[] plano){

               boolean[] visitados = new boolean[plano.length];//almacena islas

               List<Arista> isla = new ArrayList<>();

               //Para llevar un seguimiento de en las que hemos estado

               //Bucle voraz
               for(int i=0; i<plano.length; i++){
                   if(!visitados[i]){
                       //Estamos en una nueva isla
                       islas++;
                       //Creamos una nueva cola de prioridad para guardar las aristas
                       PriorityQueue<Arista> cola = new PriorityQueue<>();
                       //Añadimos las aristas que tiene el nodo
                       cola.addAll(plano[i]);

                       //Lo dejamos como visitado
                       visitados[i] = true;

                       //Empieza el bucle voraz
                       while(!cola.isEmpty()){
                           //System.out.println("Info: " +islas +" "+carreteras+" "+ coste);

                           //Cogemos la mejor
                           Arista carretera = getBestCarretera(cola);
                    /*System.out.println("Arista: "+carretera.aString());
                    System.out.println();*/

                           //Si se puede meter
                           if(esFactible(carretera, visitados)){

                               //Actualizamos costes y número de carreteras
                               coste += carretera.peso;
                               carreteras+= 1;

                               //Guardo en la isla
                               isla.add(carretera);

                               //Añadimos aristas del nodo
                               cola.addAll(plano[carretera.destino]);

                               //Ponemos como visitado el nodo destino de la arista
                               visitados[carretera.destino] = true;
                           }
                           //cola.remove(carretera);
                       }
                   }
               }


               return isla;
           }

           public static Arista getBestCarretera(PriorityQueue<Arista> cola){
               return cola.poll();
           }


           public static boolean esFactible(Arista ar, boolean[] conjunto){
               //En este caso el conjunto es el de no visitados así que tengo que ver que haga ciclo con ese conjunto

               //Si solo tiene uno de los extremos en el conjunto
               return !conjunto[ar.destino];
           }





    public static void main(String[] args) throws IOException{

        Scanner lector = new Scanner(System.in);
        String linea = lector.nextLine();
        while (!linea.equals("0")){
            coste = 0;
            islas = 0;
            carreteras = 0;

            int numeroCiudades = Integer.parseInt (linea);
            int numeroCarreteras  = Integer.parseInt(lector.nextLine());
            List[] plano = new List[numeroCiudades];

            for (int i = 0; i <numeroCiudades ; i++) {
                plano[i]= new ArrayList();
            }



            for (int i = 0; i < numeroCarreteras; i++) {
                String[]partes = lector.nextLine().split(" ");
                int origen = Integer.parseInt(partes[0]);
                int destino = Integer.parseInt (partes[1]);
                int coste = Integer.parseInt (partes[2]);

                plano[origen].add(new Arista(origen,destino,coste));
                plano[destino].add(new Arista(destino,origen,coste));
            }
             primIslas(plano);
            System.out.println(islas +" "+carreteras+" "+ coste);
            System.out.println("---" );
            linea = lector.nextLine();
            }

        }



}


