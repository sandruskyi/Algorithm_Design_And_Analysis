/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blaster;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jvergara
 */
public class Blaster {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int T, O, P;
        int N, M;

        Scanner in = new Scanner(System.in);
        String linea = in.nextLine();
        String trozos[] = linea.split(" ");
        T = Integer.parseInt(trozos[0]);
        O = Integer.parseInt(trozos[1]);
        P = Integer.parseInt(trozos[2]);

        linea = in.nextLine();
        trozos = linea.split(" ");
        N = Integer.parseInt(trozos[0]);
        M = Integer.parseInt(trozos[1]);

        int atenuacion[] = new int[N];
        linea = in.nextLine();
        trozos = linea.split(" ");
        for (int i = 0; i < N; i++) {
            atenuacion[i] = Integer.parseInt(trozos[i]);
        }

        List<Integer>[] grafo = new List[N];
        for (int i = 0; i < N; i++) {
            grafo[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            linea = in.nextLine();
            trozos = linea.split(" ");
            int u = Integer.parseInt(trozos[0]);
            int v = Integer.parseInt(trozos[1]);
            grafo[u-1].add(v-1);
        }

        int maximaPotencia[] = new int[1];
        ArrayList<Integer> recorrido = new ArrayList<Integer>();
        boolean[] visitados = new boolean[grafo.length];

        visitados[T-1] = true;
        recorrido.add(T-1);
        maximaPotencia[0]=Integer.MIN_VALUE;
        recorridoProfundidadPonderado(grafo, T-1, O-1, P, N, M, atenuacion, recorrido, visitados, maximaPotencia);
        System.out.println (maximaPotencia[0]);
    }

    public static void recorridoProfundidadPonderado(List<Integer>[] grafo, int origen, int destino, int potencia, int N,
            int M, int atenuacion[], ArrayList<Integer> recorrido,
            boolean[] visitados, int maximaPotencia[]) {
        
        for (int adj : grafo[origen]) {
            if ((!visitados[adj]) && (potencia > 0)) { //Factible
                visitados[adj] = true;//Marcar
                recorrido.add(adj);//Marcar
               
                if (destino==adj) { //Es la última etapa
                    if (maximaPotencia[0]<potencia) {
                        maximaPotencia[0]=potencia;
                    }
                } else {
                    //Se resta potencia en el rebote pero no final
                    potencia=potencia-atenuacion[adj];
                    recorridoProfundidadPonderado(grafo, adj, destino, potencia, N, M, atenuacion, recorrido, visitados, maximaPotencia);
                }

                visitados[adj] = false;
                recorrido.remove((Integer) adj);
                potencia=potencia+atenuacion[adj]; //Esto da igual porque es por valor
            }
        }
    }
}

/*
1 6 10
6 8
8 3 2 1 4 9
1 2
1 3
2 4
3 4
3 5
4 5
4 6
5 6
*/