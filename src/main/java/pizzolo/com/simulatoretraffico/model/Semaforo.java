package pizzolo.com.simulatoretraffico.model;

/**
 * classe che gestisce i colori di un semaforo
 */
public enum Semaforo {
    VERDE("vai"), GIALLO("rallenta"), ROSSO("stop");

    private String codice;

    Semaforo(String codice) {
        this.codice = codice;
    }
}
