package pizzolo.com.simulatoretraffico.model;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class GestioneMovimento extends AnimationTimer{
    //gestisce l'array di macchine per tutto il movimento di ogni macchina
    private ArrayList<Macchina> macchineCanvas;
    //gestisce il movimento della macchine
    private GraphicsContext gc;
    public GestioneMovimento(GraphicsContext gc){
        macchineCanvas = new ArrayList<>();
        this.gc = gc;
    }

    @Override
    public void handle(long l) {
        gc.clearRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for (Macchina m: macchineCanvas){
            m.aggiorna(gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            m.disegna(gc);
        }
    }

    public ArrayList<Macchina> getMacchineCanvas() {
        return macchineCanvas;
    }
}
