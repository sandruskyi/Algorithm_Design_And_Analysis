/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalstory;

import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author jvergara
 */
public class FinalStory {

    public static boolean hayVidaEnemiga (int vida_enemigos[]) {
        for (int i=0; i< vida_enemigos.length; i++){
            if (vida_enemigos[i]>0) {
                return true;
            }
        }
        return false;
    }
    public static int partidaVoraz (int ataqueJugador,int numero_enemigos, int ataques_enemigos[], int vida_enemigos[]) {
        
        int contador_daño = 0;
        while (hayVidaEnemiga (vida_enemigos)) {
            //Calculamos un ataque de todos los vivos
            for (int i=0; i< vida_enemigos.length; i++){
                if (vida_enemigos[i]>0) {
                    contador_daño=contador_daño+ataques_enemigos[i];
                }
            }
            //Ahora buscamos al que tiene menos vida y si dos tienen menos vida, entonces el que mas ataque tiene de los dos
            int candidato_menor_vida = -1;
            int valor_menor_vida = Integer.MAX_VALUE;
            for (int i=0; i< vida_enemigos.length; i++){
                if (vida_enemigos[i]>0) {
                    if ((vida_enemigos[i]<valor_menor_vida) || ((vida_enemigos[i]==valor_menor_vida) && (ataques_enemigos[i]>ataques_enemigos[candidato_menor_vida]))) {
                        candidato_menor_vida=i;
                        valor_menor_vida=vida_enemigos[i];
                    }
                }
            }
            
            //Ahora le atacamos
            vida_enemigos[candidato_menor_vida]=vida_enemigos[candidato_menor_vida]-ataqueJugador;
            if (vida_enemigos[candidato_menor_vida]<0)
                vida_enemigos[candidato_menor_vida]=0;
            
            
        }
        
        return contador_daño;
    }
   
    public static void main(String[] args) {
        Scanner entrada = new Scanner (System.in);
        int npartidas = Integer.parseInt(entrada.nextLine());
        for (int i=0; i<npartidas; i++) {
            int ataqueJugador = Integer.parseInt(entrada.nextLine());
            int numero_enemigos = Integer.parseInt(entrada.nextLine());
            int ataques_enemigos[] = new int [numero_enemigos];
            int vida_enemigos[] = new int [numero_enemigos];
            
            //Leemos los ataques de los enemigos
            String trozos[] = entrada.nextLine().split(" ");
            for (int j=0; j<trozos.length; j++) {
                ataques_enemigos[j]=Integer.parseInt(trozos[j]);
            }
            
            //Leemos las vidas de los enemigos
            trozos = entrada.nextLine().split(" ");
            for (int j=0; j<trozos.length; j++) {
                vida_enemigos[j]=Integer.parseInt(trozos[j]);
            }
            
            int resultado = partidaVoraz (ataqueJugador, numero_enemigos, ataques_enemigos, vida_enemigos);
            
            System.out.println (resultado);
        }
    }


}
