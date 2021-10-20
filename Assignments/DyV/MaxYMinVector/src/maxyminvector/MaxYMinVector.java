package maxyminvector;
/**
 *
 * @author sandr
 */
public class MaxYMinVector {
    public static void main(String[] args) {
        int v[]={0, 20, 100, 5, 200, 4, 300, 50, 3001};
        int[] MxMn = MaxMin(v, 0, v.length-1);      
        System.out.println("El mínimo es "+MxMn[0]+" y el maximo es "+MxMn[1]);
    }
    public static int[] MaxMin(int v[], int ini, int fin){
        int[] aux = new int[2]; //{minimo, maximo}
        if( ini==fin){
            aux[0]=v[ini]; 
            aux[1]=v[fin];
            return aux;
        }else{
            int medio= (ini+fin)/2; 
            int [] m1= MaxMin(v, ini, medio);
            int [] m2= MaxMin (v, medio+1, fin);
            if(m1[0]>m2[0]){ //MINIMO
                aux[0]=m2[0];
            }else{
                aux[0]=m1[0];
            }
            if(m1[1]>m2[1]){ //MAXIMO
                aux[1]=m1[1];
            }else{
                aux[1]=m2[1];
            }
            return aux; 
        }
        
    }
}
