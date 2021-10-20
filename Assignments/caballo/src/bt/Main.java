package bt;

public class Main {
    static int [] movRelativosX= {1, 2, 2, 1, -1, -2, -2, -1};
    static int [] movRelativosY= {2, 1, -1, -2, -2, -1,1, 2};
    public static void main(String[] args) {
        int [][] tablero = new int [5][5];
        tablero[1][1]=1;
        boolean exito= BT(tablero, 2, 1, 1);
        if(exito){
            imprimir(tablero);
        }else{
            System.out.print("NO");
        }
    }
    public static boolean BT(int [][] tablero, int etapa, int posactualx, int posactualy){
        boolean exito=false;
        for(int intento=0; intento<=7 && !exito ; intento++){
            int nuevax= posactualx+movRelativosX[intento];
            int nuevay= posactualy+movRelativosY[intento];
            //tablero[nuevax][nuevay]=¿etapa?
            if(esFactible(tablero, nuevax, nuevay, etapa)){
                tablero[nuevax][nuevay]=etapa;
                if(etapa==25){
                    exito=true;
                }else{
                    exito=BT(tablero,etapa+1, nuevax, nuevay);
                }
                if(!exito){
                    tablero[nuevax][nuevay]=0;
                }
            }
        }
        return exito;
    }
    public static boolean esFactible(int [][] tablero, int  nuevax, int nuevay, int etapa){
        if(nuevax<0||nuevay<0){
            return false;
        }
        if(nuevax>tablero.length-1 || nuevay> tablero.length-1){
            return false;
        }
        if(tablero[nuevax][nuevay]!=0){
            return false;
        }
        return true;
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
