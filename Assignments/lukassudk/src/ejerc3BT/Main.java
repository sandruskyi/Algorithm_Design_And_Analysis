package ejerc3BT;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        HashSet<Integer>[] conjuntos_filas = new HashSet[9];
        HashSet<Integer>[] conjuntos_columnas = new HashSet[9];
        HashSet<Integer>[] conjuntos_cuadrantes = new HashSet[9];

        for(int i = 0; i < 9; ++i) {
            conjuntos_filas[i] = new HashSet(9);
            conjuntos_columnas[i] = new HashSet(9);
            conjuntos_cuadrantes[i] = new HashSet(9);
        }

        int[][] tablero = new int[][]{{5, 3, 0, 0, 7, 0, 0, 0, 0}, {6, 0, 0, 1, 9, 5, 0, 0, 0}, {0, 9, 8, 0, 0, 0, 0, 6, 0}, {8, 0, 0, 0, 6, 0, 0, 0, 3}, {4, 0, 0, 8, 0, 3, 0, 0, 1}, {7, 0, 0, 0, 2, 0, 0, 0, 6}, {0, 6, 0, 0, 0, 0, 2, 8, 0}, {0, 0, 0, 4, 1, 9, 0, 0, 5}, {0, 0, 0, 0, 8, 0, 0, 7, 9}};

        for(int i = 0; i < tablero.length; ++i) {
            for(int j = 0; j < tablero.length; ++j) {
                if (tablero[i][j] != 0) {
                    conjuntos_filas[i].add(tablero[i][j]);
                    conjuntos_columnas[j].add(tablero[i][j]);
                    conjuntos_cuadrantes[dameCuadrante(i, j)].add(tablero[i][j]);
                }
            }
        }

        boolean exito = false;
        exito = BTUna(conjuntos_filas, conjuntos_columnas, conjuntos_cuadrantes, 0, 0, tablero);
        if (exito) {
            imprimir(tablero);
        } else {
            System.out.println("no hay solucion");
        }

    }

    public static boolean BTUna(HashSet<Integer>[] conjuntos_filas, HashSet<Integer>[] conjuntos_columnas, HashSet<Integer>[] conjuntos_cuadrantes, int etapax, int etapay, int[][] tablero) {
        boolean exito = false;
        int nuevax;
        int nuevay;
        if (tablero[etapax][etapay] != 0) {
            if ((etapax == tablero.length - 1) && (etapay == tablero.length - 1)) {
                exito = true;
            } else {
                nuevax = dameNuevaX(etapax, etapay);
                nuevay = dameNuevaY(etapax, etapay);
                exito = BTUna(conjuntos_filas, conjuntos_columnas, conjuntos_cuadrantes, nuevax, nuevay, tablero);
            }
        } else {
            for(int intento = 1; intento <= tablero.length && !exito; intento++) {
                if (!conjuntos_filas[etapax].contains(intento) && !conjuntos_columnas[etapay].contains(intento) && !conjuntos_cuadrantes[dameCuadrante(etapax, etapay)].contains(intento)) {
                    tablero[etapax][etapay] = intento;
                    conjuntos_filas[etapax].add(intento);
                    conjuntos_columnas[etapay].add(intento);
                    conjuntos_cuadrantes[dameCuadrante(etapax, etapay)].add(intento);
                    if (etapax == tablero.length - 1 && etapay == tablero.length - 1) {
                        exito = true;
                    } else {
                        nuevax = dameNuevaX(etapax, etapay);
                        nuevay = dameNuevaY(etapax, etapay);
                        exito = BTUna(conjuntos_filas, conjuntos_columnas, conjuntos_cuadrantes, nuevax, nuevay, tablero);
                    }
                }

                if (!exito) {
                    tablero[etapax][etapay] = 0;
                    conjuntos_filas[etapax].remove(intento);
                    conjuntos_columnas[etapay].remove(intento);
                    conjuntos_cuadrantes[dameCuadrante(etapax, etapay)].remove(intento);
                }
            }
        }

        return exito;
    }

    public static int dameNuevaX(int x, int y) {
        return y < 8 ? x : x + 1;
    }

    public static int dameNuevaY(int x, int y) {
        return y < 8 ? y + 1 : 0;
    }

    public static int dameCuadrante(int x, int y) {
        int iniciox = dameInicio(x);
        int inicioy = dameInicio(y);
        switch(iniciox) {
            case 0:
                switch(inicioy) {
                    case 0:
                        return 0;
                    case 3:
                        return 1;
                    case 6:
                        return 2;
                }
            case 3:
                switch(inicioy) {
                    case 0:
                        return 3;
                    case 3:
                        return 4;
                    case 6:
                        return 5;
                }
            case 6:
                switch(inicioy) {
                    case 0:
                        return 6;
                    case 3:
                        return 7;
                    case 6:
                        return 8;
                }
            default:
                return -1;
        }
    }

    public static int dameInicio(int etapa) {
        switch(etapa) {
            case 0:
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
            case 5:
                return 3;
            case 6:
            case 7:
            case 8:
                return 6;
            default:
                return -1;
        }
    }

    public static void imprimir(int[][] tablero) {
        for(int i = 0; i < tablero.length; ++i) {
            for(int j = 0; j < tablero.length; ++j) {
                System.out.print(tablero[i][j]);
            }

            System.out.println("");
        }

    }
}
