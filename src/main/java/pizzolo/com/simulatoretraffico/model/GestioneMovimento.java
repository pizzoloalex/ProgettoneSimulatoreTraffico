package pizzolo.com.simulatoretraffico.model;

import javafx.animation.AnimationTimer;
import java.util.ArrayList;

public class GestioneMovimento extends AnimationTimer{
    private Macchina macchina;
    //gestisce l'array di macchine per tutto il movimento di ogni macchina
    private ArrayList<Macchina> macchineCanvas;
    //gestisce il movimento della macchine
    private AnimationTimer animationTimer;
    public GestioneMovimento(){
        macchineCanvas = new ArrayList<>();
    }

    @Override
    public void handle(long l) {

    }

    public ArrayList<Macchina> getMacchineCanvas() {
        return macchineCanvas;
    }
}
