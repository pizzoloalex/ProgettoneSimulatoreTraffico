package pizzolo.com.simulatoretraffico.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import pizzolo.com.simulatoretraffico.model.GestioneMovimento;
import pizzolo.com.simulatoretraffico.model.Macchina;
import pizzolo.com.simulatoretraffico.model.Semaforo;


public class Controller {
    @FXML
    private Canvas canvas;
    @FXML
    private VBox root;

    private GraphicsContext gc;
    private GestioneMovimento gestioneMovimento;
    private Semaforo semaforoVerticale;
    private Semaforo semaforoOrizzontale;

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        gestioneMovimento = new GestioneMovimento(gc);
        //esegue questo codice dopo che il canvas e pronto o in un futuro qualunque
        Platform.runLater(() -> {
            Duration verde = Duration.seconds(8);
            Duration giallo = Duration.seconds(4);
            Duration rosso = verde.add(giallo);

            // semaforo per la macchina verticale: parte da verde
            semaforoVerticale = new Semaforo(verde, giallo, rosso, canvas.getWidth() / 2, canvas.getHeight() / 2);
            semaforoVerticale.inizializzaSemaforo(Duration.ZERO);

            // semaforo per la macchina orizzontale: sfasato → parte da rosso
            semaforoOrizzontale = new Semaforo(verde, giallo, rosso, canvas.getWidth() / 2 + 180, canvas.getHeight() / 2);
            semaforoOrizzontale.inizializzaSemaforo(verde.add(giallo));
            // registra i semafori in GestioneMovimento così vengono disegnati ogni frame
            gestioneMovimento.getSemafori().add(semaforoOrizzontale);
            gestioneMovimento.getSemafori().add(semaforoVerticale);
        });

    }

    /**
     * metodo che disegna ogni volta una macchina con  il suo relativo semaforo
     */
    @FXML
    public void aggiungiMacchina() {
        Macchina m1 = new Macchina(canvas.getWidth() / 2, canvas.getHeight() / 2 +  50, 0, -8, semaforoVerticale, 0, -5);
        m1.disegna(gc);
        Macchina m2 = new Macchina(canvas.getWidth() / 2 + 250, canvas.getHeight() / 2, -8, 0, semaforoOrizzontale, -5,0);
        m2.disegna(gc);
        gestioneMovimento.getMacchineCanvas().add(m1);
        gestioneMovimento.getMacchineCanvas().add(m2);
    }

    /**
     * Controlla se l'array e vuoto
     * metodo che elimina l'ultima macchina aggiunta ridisegnando il canvas
     */
    @FXML
    public void eliminaMacchina() {
        if (gestioneMovimento.getMacchineCanvas().isEmpty()) {
            return;
        }
        gestioneMovimento.getMacchineCanvas().removeLast();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Macchina m : gestioneMovimento.getMacchineCanvas()) {
            m.disegna(gc);
        }
    }


    /**
     * metodo che avvia la simulazione
     */
    @FXML
    public void avviaSimulazione() {
        gestioneMovimento.start();
    }
}
