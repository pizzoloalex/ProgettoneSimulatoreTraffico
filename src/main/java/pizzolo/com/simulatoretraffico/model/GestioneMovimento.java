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

    /**
     * calcola la distanza migliore tra la macchina e il semaforo piu vicino
     *
     * @param m macchina da gestire per la distanza
     * @return il semaforo piu vicino
     */
    private Semaforo distanzaSemaforo(Macchina m) {
        Semaforo vicino = null;
        double minDistanza = Double.MAX_VALUE; //inizialmente distanza infinita
        for (Semaforo s : semafori) {
            double d = calcolaDistanza(m, s);
            //se minore della distanza massima
            if (d < minDistanza) {
                minDistanza = d;
                vicino = s;
            }
        }
        return vicino;
    }

    /**
     * calcola la distanza tra la macchina e il semaforo
     *
     * @param m macchina per la distanza
     * @param s semaforo in condivisione con l machcina
     * @return
     */
    private double calcolaDistanza(Macchina m, Semaforo s) {
        double distanzaX = m.getPosX() - s.getPosX();
        double distanzaY = m.getPosY() - s.getPosY();
        return Math.sqrt(distanzaX * distanzaX + distanzaY * distanzaY);
    }

    public ArrayList<Semaforo> getSemafori() {
        return semafori;
    }

    public ArrayList<Macchina> getMacchineCanvas() {
        return macchineCanvas;
    }
}
