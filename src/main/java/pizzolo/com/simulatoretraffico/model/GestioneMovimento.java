package pizzolo.com.simulatoretraffico.model;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * classe che gestisce il movimento
 * estende AnimationTimer
 */
public class GestioneMovimento extends AnimationTimer {
    //gestisce l'array di macchine per tutto il movimento di ogni macchina
    private ArrayList<Macchina> macchineCanvas;
    private ArrayList<Semaforo> semafori;
    //gestisce il movimento della macchine
    private GraphicsContext gc;

    public GestioneMovimento(GraphicsContext gc) {
        macchineCanvas = new ArrayList<>();
        semafori = new ArrayList<>();
        this.gc = gc;
    }

    /**
     * stampa 60 frame al secondo, ogni volta cancella e ridisegna il canvas con la posizione aggiornata
     *
     * @param l
     */
    @Override
    public void handle(long l) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for (Semaforo s : semafori) {
            s.disegna(gc);
        }

        for (Macchina m : macchineCanvas) {
            m.aggiorna(gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            m.disegna(gc);
        }
    }
/*
    METODO PER CALCOLARE IL RALLENTAMENTO DELLA PALLINA CON LA FISICA

    //distanza tra due punti = sqrt((xs- xm)^2  + (ys-ym)^2)

    /**
     * metodo che ritorna la distanza tra due punti
     *
     * @param m macchina per il calcolo della distanza
     * @param s semaforo per il calcolo della distanza
     * @return la distanza tra semaforo e macchina

    private double distanzaPunti(Macchina m, Semaforo s) {
        return Math.sqrt(Math.pow(s.getPosX() - m.getPosX(), 2) + Math.pow(s.getPosY() - m.getPosY(), 2));
    }
    //formula rallentamento = velocita (finale - iniziale)/ il tempo che ci mette a fermarsi dall'inizio del rallentamento
  */


    public ArrayList<Semaforo> getSemafori() {
        return semafori;
    }

    public ArrayList<Macchina> getMacchineCanvas() {
        return macchineCanvas;
    }
}
