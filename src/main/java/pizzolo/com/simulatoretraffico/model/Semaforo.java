package pizzolo.com.simulatoretraffico.model;

import javafx.scene.paint.Color;

/**
 * classe che gestisce i colori di un semaforo
 */
public enum Semaforo {
    VERDE(Color.GREEN), GIALLO(Color.YELLOW), ROSSO(Color.RED);


    private Color colore;

    Semaforo(Color colore) {
        this.colore = colore;
    }

    public Color getColore() {
        return colore;
    }
}
