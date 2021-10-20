
package maxvector;

public class MaxVector {

    public static void main(String[] args) {
        int v[]={100,200,1,17,48,300,101,2};
        int m= maximo(v,0,v.length-1);
        System.out.print("El maximo es "+m);
        
    }

    /**
     *
     * @param v
     * @param ini
     * @param fin
     * @return
     */
    public static int maximo(int v[],int ini, int fin){
        if(ini==fin){
            return v[ini];
        }else{
            int medio= (ini+fin)/2; 
            int m1 = maximo(v, ini, medio);
            int m2 = maximo(v, medio+1, fin);
            if(m1>m2){
                return m1;
            }else{
                return m2; 
            }
        }
        
    }
}
