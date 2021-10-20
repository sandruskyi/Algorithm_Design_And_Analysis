/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Kruskal {
    
    public static int Kruskal (int[][] grafo) {
      // vectores con los arcos del Arbol de recubrimiento minimo
      // Sabemos que vamos a tener tantos arcos en el ARM como nodos - 1
      int[] orig = new int[grafo.length-1];
      int[] dest = new int[grafo.length-1];
      
     
      int[][] distMin = new int[grafo.length][grafo.length];
      for (int i=0; i<grafo.length; i++) {
         for (int j=0; j<grafo.length; j++)
            distMin[i][j] = grafo[i][j];
      }
      
      //Vamos apuntando las componentes conexas. Por ahora todas son independientes
      int comp_conexas[] = new int [grafo.length];
      for (int i=0; i<grafo.length; i++) {
		  comp_conexas[i]=i;
	  }
	  
      // bucle voraz
      int i = 0;
      while (i<grafo.length-1) {

			 // se selecciona arco con menos coste.
			 int min = 1000;
			 int o = 0;
			 int d = 0;
			 for (int j=0; j<grafo.length; j++) {
				for (int k=0; k<grafo.length; k++) {
					if ((0<=distMin[j][k]) && (distMin[j][k]<min)) {
						min = grafo[j][k];
						o = j;
						d = k;
					}
				}
			}
			
			//Ahora que tenemos el arco minimo lo marcamos para que nunca más se vaya a seleccionar
			 distMin[o][d] = -1;
			 distMin[d][o] = -1;// Se marca a -1 para que nunca mas sea seleccionado.
			 
			 //Ahora vemos si se añade o no al ARM. Puede que sea rechazada   
			 if (comp_conexas[o]!=comp_conexas[d]) { //Es un buen arco, une cosas que antes no estaban unidas
				 // se añade el arco seleccionado al ARM
				 orig[i] = o;
				 dest[i] = d;
				 i++;
				 int color = comp_conexas[o];
				 //Marcamos los nodos de componente igual a origen con el mismo color que la componente destino
				 for (int j=0; j<comp_conexas.length; j++) {
						if (comp_conexas[j]==color)
							comp_conexas[j]=comp_conexas[d];
				  }
				 
			 }else {
				 //Rechazada
				 System.out.println ("Rechazada: "+"("+o+","+d+")");
				 
			 }
      }
      
      int coste = 0;
      for (i=0; i<grafo.length-1; i++)
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
        int coste = Kruskal (grafo);
        System.out.println ("Coste total: "+ coste);
    }
    
}







