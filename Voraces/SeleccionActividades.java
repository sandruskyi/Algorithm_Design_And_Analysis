import java.util.Scanner;

public class SeleccionActividades {

	public static int funcionSeleccion (int []c, int [] f, boolean[] candidatos) {
		//estrategia de solución: devuelve las que se realizan antes
		int p=-1, valor=Integer.MAX_VALUE;
		
		for (int i=0; i<candidatos.length;i++) {
			if (candidatos[i]){
				if (f[i]<valor) {
					p=i;
					valor=f[p];
				}
			}
		}
		
		return p; //valor -1 indica que no hay candidato
	}
	
	public static boolean factible (int [] c, int [] f, int candidato, boolean []s) {
		boolean resul=true;
		for (int i=0; i< s.length; i++) {
			if (s[i]) {
				if (f[i]>c[candidato])
					return false;
			}
		}
		return resul;
	}
	public static void Solucion (int [] c, int [] f,  boolean []solucion) {
		boolean candidatos[] = new boolean[c.length];
		boolean hay_candidatos=true;
		
		for (int i=0; i<candidatos.length; i++) {
			candidatos[i]=true;
		}
		
		while (hay_candidatos) {
			int candidato = funcionSeleccion (c,f, candidatos);
			if (candidato==-1) {
				hay_candidatos=false;
			}else {
				candidatos[candidato]=false;
				if (factible (c,f,candidato, solucion)) {
					solucion[candidato]=true;
				}
			}
		}
	}
	public static void imprimir (boolean solucion[]) {
		int cont=0;
		for (int i=0; i<solucion.length; i++) {
			if (solucion[i]) {
				cont++;
			}
		}
		System.out.println(cont);
	}
	public static void main(String[] args) {
		//int c[] = {1, 2, 0, 5, 8, 5, 6, 8, 3, 2, 12};
		//int f[] = {4, 13, 6, 7, 12, 9, 10, 11, 8, 5, 14};
		//boolean solucion[] = new boolean [c.length];
		//for (int i=0; i<solucion.length; i++) {
		//	solucion[i]=false;
		//}
		int c[] = null;
		int f[] = null;
		boolean solucion[] = null;
		
		Scanner entrada = new Scanner (System.in);
		//Leemos la primera linea para ver el numero de tareas
		int n=Integer.parseInt(entrada.nextLine());
		//Ahora creamos los arrays de las dimensiones adecuadas
		c = new int[n];
		f = new int[n];
		solucion = new boolean [n];
		//Leemos a continuación la información de las tareas.
		String linea;
		linea = entrada.nextLine();
		String trozos_inicio[] = linea.split(" ");
		linea = entrada.nextLine();
		String trozos_fin[] = linea.split(" ");
		
		for (int i=0; i<trozos_inicio.length; i++) {
			c[i]=Integer.parseInt(trozos_inicio[i]);
			f[i]=Integer.parseInt(trozos_fin[i]);
		}
		
		
		
		for (int i=0; i<solucion.length; i++) {
			solucion[i]=false;
		}
		Solucion (c, f, solucion);
		
		imprimir (solucion);
		entrada.close();
	}

}
