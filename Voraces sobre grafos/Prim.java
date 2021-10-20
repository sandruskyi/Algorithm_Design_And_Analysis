/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */
package prim;

/**
 *
 * @author jvergara
 */
public class Prim {
    public static int Prim (int[][] grafo) {
      // vectores con los arcos del Arbol de recubrimiento minimo
      // Sabemos que vamos a tener tantos arcos en el ARM como nodos - 1
      int[] orig = new int[grafo.length-1];
      int[] dest = new int[grafo.length-1];
      
      // inicialización de vectores auxiliares que marcan la distancia a todos los nodos 
      // partiendo desde el nodo 0.
    
      int[] masProx = new int[grafo.length];
      int[] distMin = new int[grafo.length];
      for (int i=1; i<grafo.length; i++) {
         masProx[i] = 0;
         distMin[i] = grafo[i][0];
      }
      // bucle voraz
      for (int i=0; i<grafo.length-1; i++) {
         // se selecciona nodo más cercano al ARM ya formado. Con menos distancia.
         int min = 1000;
         int k = 0;
         for (int j=1; j<grafo.length; j++) 
            if ((0<=distMin[j]) && (distMin[j]<min)) {
               min = distMin[j];
               k = j;
            }
         // se añade el arco al nodo k al ARM
         orig[i] = masProx[k];
         dest[i] = k;
         distMin[k] = -1; // Se marca a -1 para que nunca mas sea seleccionado.
         
         // se actualizan distancias mínimas desde ese nuevo K alcanzado a los demas nodos.
         for (int j=1; j<grafo.length; j++)
            if (grafo[j][k]<distMin[j]) {
               distMin[j] = grafo[j][k];
               masProx[j] = k;
            }
      }
      
      int coste = 0;
      for (int i=0; i<grafo.length-1; i++)
         coste += grafo[orig[i]][dest[i]];
      imprimirArcos (orig, dest);
      return coste;
   }
   
   private static void imprimirArcos (int[] orig, int[] dest) {
      for (int i=0; i<orig.length; i++)
         System.out.println ("Arco "+i+": ("+orig[i]+","+dest[i]+")");
   }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int grafo[][] =  {{1000,16,12,1000,21},{16,1000,1000,6,11},{12,1000,1000,18,33},{1000,6,18,1000,14},{21,11,33,14,1000}};
        int coste = Prim (grafo);
        System.out.println ("Coste total: "+ coste);
    }
}
