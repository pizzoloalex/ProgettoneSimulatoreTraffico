package pizzolo.com.simulatoretraffico.model;

public enum Semaforo {
    VERDE("vai"), GIALLO("rallenta"), ROSSO("stop");

    private String codice;

    Semaforo(String codice) {
        this.codice = codice;
    }
}
