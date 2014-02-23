package com.robertobayon.aplastaelmal;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

class HiloCronoJuego extends Thread {
    
    private boolean funcionar = true;
    private int contadorSegs;
    
    public HiloCronoJuego(){
        int contadorSegs = 0;
    }

    public void run() {
        
        while (funcionar) {
            contadorSegs++;
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                
            }
        }
    }
    
    public void parar(){
        funcionar = false;
    }
    
    public int getSegundos(){
        return contadorSegs;
    }
}
