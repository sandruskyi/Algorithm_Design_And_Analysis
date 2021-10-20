package homo;

public class Main {

    public static void main(String[] args) {
        //Calcular el num de regiones homogeneas de una matriz
        int [][] matriz = new int [4][4]; //Filasxcolumnas
        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz.length/2; j++)
                matriz[i][j]=1;
        }
        for(int i= 0; i<matriz.length/2; i++){
            for(int j= (matriz.length/2) ; j<matriz.length; j++){
                matriz[i][j]=3;
            }
        }
        matriz[2][2]=4;
        matriz[2][3]=7;
        matriz[3][2]=8;
        matriz[3][3]=5;
        for(int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println();
        }
        int numRegionesHomogeneas= DYV(matriz, 0, matriz.length-1, 0, matriz[0].length-1);
        System.out.println(numRegionesHomogeneas);
    }
    public static int DYV( int [][] m, int iniF, int finF, int iniC, int finC){
        if(iniF==finF){ //Si solo hay una fila, solo hay una columna
            return 1;
        }else if(todosNumerosIguales(m, iniF,finF,iniC,finC)){
            return 1;
        }else{
            int medioF=(iniF+finF)/2;
            int medioC=(iniC+finC)/2;
            return ( DYV(m,iniF,medioF,iniC,medioC)+DYV(m, medioF+1, finF, medioC+1, finC)+ DYV(m, medioF+1, finF, iniC, medioC)+ DYV(m, iniF, medioF, medioC+1, finC));
        }
    }
    public static boolean todosNumerosIguales(int [][]m, int iniF,int finF,int iniC,int finC){
        int num= m[iniF][iniC];
        for(int i=iniF; i<=finF; i++){
            for(int j=iniC;j<=finC; j++){
                if(num!=m[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}