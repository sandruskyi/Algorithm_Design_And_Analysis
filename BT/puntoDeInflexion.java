
public class PuntoInflexion {
	
	public static int DyV (int v[], int ini, int fin) {
		if (fin<ini) {
			return -1;
		}else if (ini==fin) {
			if (v[ini]%2 == 0)
				return ini;
			else
				return -1;
		}else {
			int mitad = (ini+fin)/2;
			if (v[mitad]%2==0) {
				int r=DyV (v, mitad+1, fin);
				if (r==-1)
					return mitad;
				else
					return r;
			}else {
				return DyV (v, ini, mitad-1);
			}
		}
	}

	public static void main(String[] args) {
		int v[] = {2 ,-4, 10, 8, 0, 12, 9, 3, -15, 3, 1};
		int r=DyV (v, 0, v.length-1);
		System.out.println(r);
	}

}
