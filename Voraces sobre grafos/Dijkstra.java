/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

/**
 *
 * @author jvergara
 */
public class Dijkstra {
    
    public static int[] Dijkstra1 (int[][] grafo, int origen) {
        
      // calcula las longitudes de los caminos mínimos
      // conjunto de candidatos, con todos los nodos salvo el origen
      boolean[] candidatos = new boolean[grafo.length];
      for (int i=0; i<candidatos.length; i++)
         candidatos[i] = true;
      
      candidatos[origen] = false;
      
      // distancias inicializadas como vienen dadas en el grafo
      int[] distancias = new int[grafo.length];
      for (int i=0; i<distancias.length; i++)
         distancias[i] = grafo[origen][i];
      
      
      distancias[origen] = 0;
      // iteración para todos los candidatos
      for (int i=0; i<grafo.length-1; i++) {
         // se busca un primer candidato que todavia no tengamos su distancia definitiva
         int j;
         for (j=0; j<candidatos.length && !candidatos[j]; j++) {}
         
         int menor = j; 
         // se busca desde el, el que tenga la distancia mas cercana al nodo y todavia no haya sido tratado.
         for (; j<candidatos.length; j++) {
            if (candidatos[j] && distancias[j]<distancias[menor])
               menor = j;
         }
         //Ya no le vamos a tratar mas asi es que a false
         candidatos[menor] = false;
         
         // se actualizan las distancias mínimas que pasan por el nuevo candidato
         for (j=0; j<candidatos.length; j++) {
            if (candidatos[j])
               distancias[j] = Math.min (distancias[j], distancias[menor]+grafo[menor][j]);
         }
      }
      
      return distancias;
   }
    
     public static int[] Dijkstra2 (int[][] grafo, int origen) {
      // calcula las longitudes de los caminos mínimos y los propios caminos mínimos
      // conjunto de candidatos, con todos los nodos salvo el origen
      boolean[] candidatos = new boolean[grafo.length];
      for (int i=0; i<candidatos.length; i++)
         candidatos[i] = true;
      candidatos[origen] = false;
      // distancias inicializadas como vienen dadas en el grafo, y predecesores
      int[] distancias = new int[grafo.length];
      int[] predecesores = new int[grafo.length];
      for (int i=0; i<distancias.length; i++) {
         distancias[i] = grafo[origen][i];
         predecesores[i] = grafo[origen][i]<1000?origen:0; /*valor tomado como infinito*/
      }
      distancias[origen] = 0;
      predecesores[origen] = 0;
      // iteración para cada candidato
      for (int i=0; i<grafo.length-1; i++) {
         // se busca un primer candidato
         int j;
         for (j=0; (j<candidatos.length) && !(candidatos[j]); j++) {}
         int menor = j;
         // se busca el candidato más cercano al origen
         for (; j<candidatos.length; j++) {
            if ((candidatos[j]) && (distancias[j]<distancias[menor]))
               menor = j;
         }
         candidatos[menor] = false;
         // se actualizan las distancias mínimas que pasan por el nuevo candidato
         for (j=0; j<candidatos.length; j++) {
            if (candidatos[j])
               if (distancias[menor]+grafo[menor][j]<distancias[j]) {
                   distancias[j] = distancias[menor]+grafo[menor][j];
                   predecesores[j] = menor;
               }
         }
      }
      imprimirCaminos (predecesores, origen, distancias);
      return distancias;
   }
     
   private static void imprimirCaminos (int[] preds, int origen, int[] distancias) {
      for (int i=0; i<preds.length; i++) {
         System.out.print ("Nodo "+i+" : camino = ");
         if (distancias[i]==1000) /*valor tomado como infinito*/
            System.out.print ("-");
         else
            imprimirCamino(preds,origen,i);
         System.out.println ();
      }
   }
   
   private static void imprimirCamino (int[] preds, int origen, int j) {
      if (j!=origen)
         imprimirCamino (preds,origen,preds[j]);
      System.out.print (j+" ");
   }
  
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int [][]grafo =  {{0,20,10,1000,1000},{1000,0,1000,8,15},{1000,5,0,10,1000},{1000,1000,1000,0,20},{1000,1000,30,1000,0}};
        
        int[] distancias=Dijkstra1 (grafo, 0);
        
        for (int i=0; i< distancias.length; i++) {
            System.out.println ("Distancia a nodo "+i+" "+distancias[i]);
        }
        
        distancias=Dijkstra2 (grafo, 0);
        
        for (int i=0; i< distancias.length; i++) {
            System.out.println ("Distancia a nodo "+i+" "+distancias[i]);
        }
    }
}
