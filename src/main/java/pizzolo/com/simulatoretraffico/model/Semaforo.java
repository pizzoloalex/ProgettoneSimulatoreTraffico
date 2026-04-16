package pizzolo.com.simulatoretraffico.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * classe che gestisce i colori di un semaforo
 */
public class Semaforo {

    private Color colore;
    //gestisce lo scambio di colori nel tempo
    private Timeline timeline;
    //durata di ogni semaforo
    private Duration durataVerde;
    private Duration durataGiallo;
    private Duration durataRossa;

    public Semaforo(Duration durataVerde, Duration durataGiallo, Duration durataRossa) {
        //inizialmente verde
        this.colore = Color.GREEN;
        this.durataVerde = durataVerde;
        this.durataGiallo = durataGiallo;
        this.durataRossa = durataRossa;
        this.timeline = new Timeline();
    }

    /**
     * metodo che inizializza il tempo di ogni semaforo
     */
    public void inizializzaSemaforo() {
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, actionEvent -> {
            this.colore = Color.GREEN;
        }));
        timeline.getKeyFrames().add(new KeyFrame(durataGiallo, actionEvent -> {
            this.colore = Color.YELLOW;
        }));
        timeline.getKeyFrames().add(new KeyFrame(durataRossa.add(durataGiallo), actionEvent -> {
            this.colore = Color.RED;
        }));
        timeline.getKeyFrames().add(new KeyFrame(durataVerde.add(durataGiallo).add(durataRossa), actionEvent -> {
            this.colore = Color.GREEN;
        }));
        timeline.play();
    }

    public boolean isRosso() {
        return this.colore == Color.RED;
    }
    public boolean isGiallo() {
        return this.colore == Color.YELLOW;
    }
    public boolean isVerde() {
        return this.colore == Color.GREEN;
    }

    public Color getColore() {
        return colore;
    }
}