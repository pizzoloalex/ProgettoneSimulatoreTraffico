package pizzolo.com.simulatoretraffico.model;

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
    private Duration durataVerde;
    private Duration durataGiallo;
    private Duration durataRossa;

    public Semaforo(Duration durataVerde, Duration durataGiallo, Duration durataRossa) {
        this.colore = Color.GREEN;
        this.durataVerde = durataVerde;
        this.durataGiallo = durataGiallo;
        this.durataRossa = durataRossa;
    }
}
