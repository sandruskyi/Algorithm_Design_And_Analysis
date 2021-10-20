
public class Mochila {
	
	public static void Ordenar (int beneficios[], int peso[],int  ids[], double bp[]) {
		for (int veces=0; veces<bp.length; veces++) {
			for (int i=0; i<bp.length-1; i++) {
				if (bp[i]<bp[i+1]) {
					double aux =bp [i];
					bp[i]=bp[i+1];
					bp[i+1]= aux;
					
					int aux2 =ids [i];
					ids[i]=ids[i+1];
					ids[i+1]= aux2;
					
					aux2 =peso [i];
					peso[i]=peso[i+1];
					peso[i+1]= aux2;
					
					aux2 =beneficios [i];
					beneficios[i]=beneficios[i+1];
					beneficios[i+1]= aux2;
					
				}
	 		}
	 	}
	}
	public static void Voraz (int beneficios[], int peso[], int ids[], double bp[], double solucion[],
							int peso_mochila) {
		for (int i=0; i<beneficios.length && peso_mochila!=0; i++) {
			if (peso[i]<=peso_mochila) {
				solucion[i]=1.0;
				peso_mochila=peso_mochila-peso[i];
			}else {
				solucion[i]=(double)peso_mochila/peso[i];
				peso_mochila=0;
			}
		}
	}
	public static void imprimir (int ids[], double solucion[], int peso[]) {
		for (int i=0; i<ids.length; i++) {
			if (solucion[i]>0)
				System.out.println("Objeto con id = "+ids[i]+" porcentaje del objeto: "+solucion[i]+ " con peso: "+peso [i]);
		}
	}
	public static void main(String[] args) {
		int beneficios [] = {8, 4, 2, 4, 5, 1, 4, 2, 34, 5 };
		int peso [] = {4, 3, 3, 4, 1, 2, 4, 5, 6, 10};
		int ids[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		double bp[] = new double [beneficios.length];
		
		//Calculamos array bp (beneficio/peso)
		for (int i=0; i< beneficios.length; i++) {
			bp [i] = beneficios[i]/peso [i];
		}
		//Ordenar arrays en base al criterio de mayor beneficio/peso
		Ordenar (beneficios, peso, ids, bp);
		
		double solucion [] = new double [beneficios.length];
		Voraz (beneficios, peso, ids, bp, solucion, 10);
		imprimir (ids, solucion, peso);

	}

}
