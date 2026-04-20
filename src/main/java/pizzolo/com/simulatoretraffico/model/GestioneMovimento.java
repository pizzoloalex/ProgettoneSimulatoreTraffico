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
    private long lastTime;

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

    public ArrayList<Semaforo> getSemafori() {
        return semafori;
    }

    public ArrayList<Macchina> getMacchineCanvas() {
        return macchineCanvas;
    }
}
