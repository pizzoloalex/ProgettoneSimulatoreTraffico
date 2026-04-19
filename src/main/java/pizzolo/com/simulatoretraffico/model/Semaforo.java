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
     * metodo che gestisce il ciclo di piu semafori a colori alterni
     *
     * @param offset durata di cui sfasare il semaforo
     */
    public void inizializzaSemaforo(Duration offset) {
        Duration fineVerde = durataVerde;
        Duration fineGiallo = fineVerde.add(durataGiallo);
        Duration cicloTotale = fineGiallo.add(durataRossa);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, actionEvent -> {
            this.colore = Color.GREEN;
        }));
        timeline.getKeyFrames().add(new KeyFrame(fineVerde, actionEvent -> {
            this.colore = Color.YELLOW;
        }));
        timeline.getKeyFrames().add(new KeyFrame(fineGiallo, actionEvent -> {
            this.colore = Color.RED;
        }));
        timeline.getKeyFrames().add(new KeyFrame(cicloTotale));
        timeline.play();

        /*
        Salta a una determinata posizione in questo Animation.
        Se il tempo specificato è inferiore a Duration.ZERO, questo metodo salterà all'inizio dell'animazione.
        Se il tempo specificato è maggiore della durata di questo Animation, questo metodo salterà alla fine.
        fa partire i colori sfasati
         */
        if (!offset.equals(Duration.ZERO) && offset != null) {
            timeline.jumpTo(offset);
        }
    }

    public void inizializzaSemaforo() {
        inizializzaSemaforo(Duration.ZERO);
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