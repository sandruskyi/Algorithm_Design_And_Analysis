package cuadrado;

public class Main {

    public static void main(String[] args) {
	    int [][] tablero= new int [3][3];
	    boolean exito= cuadradoMagicoBT(tablero,0,0);
	    if(exito){
	        imprimir(tablero);
        }else{
	        System.out.println("No existe solución");
        }
    }
    public static boolean cuadradoMagicoBT(int [][] tablero, int etapax, int etapay){
        boolean exito=false;
        for(int intento=1; intento<=(tablero.length*tablero.length)&&!exito;intento++){
            //tablero[etapax][etapay]=¿intento?
            if(esFactible(tablero,etapax,etapay,intento)){
                tablero[etapax][etapay]=intento;
                if((etapax==tablero.length-1)&&(etapay==tablero.length-1)){
                    exito=true;
                }else{
                    int nuevax= dameNuevaX(tablero, etapax, etapay);
                    int nuevay= dameNuevaY(tablero, etapax, etapay);
                    exito= cuadradoMagicoBT(tablero, nuevax, nuevay);
                }
                if(!exito){
                    tablero[etapax][etapay]=0;
                }
            }
        }
        return exito;
    }
    public static int dameNuevaX(int [][]tablero, int etapax, int etapay){
        if(etapay<(tablero.length-1)){
            return etapax;
        }else{
            return etapax+1;
        }
    }
    public static int dameNuevaY(int [][]tablero, int etapax, int etapay){
        if(etapay<(tablero.length-1)){
            return etapay+1;
        }else{
            return 0;
        }
    }
    public static boolean esFactible(int [][]tablero,int etapax,int etapay,int intento){
        if((sumaFilas(tablero,etapax)+intento<15)&&(etapay==tablero.length-1)){
            return false;
        }
        if((sumaColumnas(tablero,etapay)+intento<15)&&(etapax==tablero.length-1)){
            return false;
        }
        if((sumaFilas(tablero,etapax)+intento>15)){
            return false;
        }
        if((sumaColumnas(tablero,etapay)+intento>15)){
            return false;
        }
        return true;
    }
    public static int sumaFilas(int [][]tablero, int etapax){
        int contador=0;
        for(int y=0;y<tablero.length; y++){
            contador+=tablero[etapax][y];
        }
        return contador;
    }
    public static int sumaColumnas(int [][]tablero, int etapay){
        int contador=0;
        for(int x=0;x<tablero.length; x++){
            contador+=tablero[x][etapay];
        }
        return contador;
    }
    public static void imprimir(int [][] tablero){
        for(int i=0; i<tablero.length; i++){
            for(int j=0; j<tablero.length;j++){
                System.out.print(tablero[i][j]+" ");
            }
            System.out.println();
        }
    }

}
