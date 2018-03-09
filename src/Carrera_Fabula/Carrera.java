/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carrera_Fabula;

/**
 * @author Raquel
 */

/** 
 * Clase que contiene las variables comunes para Liebre y Tortuga y sus métodos.
 */

public class Carrera {

    /**
     * corre - Booleano que permite la sincronización de los hilos.
     */
    private boolean corre;
    
    /**
     * casillal - Int que recoge la distancia recorrida por la Liebre.
     */
    public int casillal=0;
    
    /**
     * casillat - Int que recoge la distancia recorrida por la Tortuga.
     */
    public int casillat=0;
    

    /**
     * Constructor de la clase Carrera. 
     * Inicializa la variable "corre" con valor "false".
     *@clase Carrera
     */

    public Carrera() {
        corre=false;
    }

    /**
     *Método que utilizará la Liebre para moverse durante la carrera.
     *Establece las condiciones para asignarle un valor u otro a la casilla
     *a partir del valor "a" que le introducimos. Contiene un método "wait()" y 
     *su correspondiente "catch" para atrapar la interrupción. 
     *Se usa para regular el uso alternativo de los hilos.
     * @param a Int. Dependiendo de su valor, suma o resta a "casillal" un
     * determinado número de posiciones.
     */

    public synchronized void Liebre(int a) {
        if(corre == false){
            try{
                wait();
            } catch(InterruptedException e) {}
        }
        if (a<=10){
            casillal-=12;
        }
        else if (a >=20 && a<=40){
            casillal+=9;
        }
        else if(a>=70){
            casillal+=1;
        }
        else if(a>=41 && a<=61){}
        else{
            casillal-=1;
        }
        if(casillal < 1)
            casillal = 1;
        corre = false;
        notify();
    }
    
    /**
     *Método que utilizará la Tortuga para moverse durante la carrera.
     *Establece las condiciones para asignarle un valor u otro a la casilla
     *a partir del valor "a" que le introducimos. Contiene un método "wait()" y 
     *su correspondiente "catch" para atrapar la interrupción. 
     *Se usa para regular el uso alternativo de los hilos.
     * @param a Int. Dependiendo de su valor, suma o resta a "casillat" un
     * determinado número de posiciones.
     */
  
    public synchronized void Tortuga(int a) {
        if(corre == true){
            try{
                wait();
            } catch(InterruptedException e) {}
        }
        if (a >=50){
            casillat+=3;
        }
        else if (a<=20){
            casillat-=6;
        }
        else{
            casillat+=1;
        }
        if(casillat < 1)
            casillat = 1;
        corre = true;
        this.Pista();
        notify();
    }

    /**
     * Método que generará la representación gráfica del movimiento
     *de la Liebre y la Tortuga a través de la pista. Pone tantos
     *espacios como distancia hayan recorrido junto a la letra que corresponda
     *a cada animal y dibuja una pista para ver como avanzan.
     *@see Carrera
     */

    public synchronized void Pista(){
        int i=0;
        for(i=1;i<=casillal;i++){
            System.out.print(" ");
            if (i == casillal)
                System.out.println("L");
        }
        i=0;
        for(i=0;i<=casillat;i++){
            System.out.print(" ");
            if (i == casillat)
                System.out.println("T");
        }
        System.out.println("                                                                    - M - ");
        System.out.println("                                                                    - E - ");
        System.out.println("                                                                    - T - ");
        System.out.println("                                                                    - A - ");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
    }

    /**
     *Método que permite la detención de los hilos usando un 
     *booleano para comprobar si alguno ha llegado a la meta.
     * @return True o False dependiendo de la condición.
     */

    public synchronized boolean ganador(){
        if (casillal >= 70 || casillat >=70)
            return false;
        else
            return true;
    }
    
}

