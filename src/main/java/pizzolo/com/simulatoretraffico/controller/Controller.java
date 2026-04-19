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

//TODO fixare  bug perche non mi disegna  semafori

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
        Platform.runLater(() -> {
            Duration verde = Duration.seconds(5);
            Duration giallo = Duration.seconds(2);
            Duration rosso = Duration.seconds(5);

            // semaforo per la macchina verticale: parte da verde
            semaforoVerticale = new Semaforo(verde, giallo, rosso, canvas.getWidth() / 2, canvas.getHeight() / 2 - 100);
            semaforoVerticale.inizializzaSemaforo(Duration.ZERO);

            // semaforo per la macchina orizzontale: sfasato → parte da rosso
            semaforoOrizzontale = new Semaforo(verde, giallo, rosso, canvas.getWidth() / 2 + 200, canvas.getHeight() / 2 - 200);
            semaforoOrizzontale.inizializzaSemaforo(verde.add(giallo));
            // registra i semafori in GestioneMovimento così vengono disegnati ogni frame
            gestioneMovimento.getSemafori().add(semaforoVerticale);
            gestioneMovimento.getSemafori().add(semaforoOrizzontale);
        });

    }

    @FXML
    public void aggiungiMacchina() {
        Macchina m1 = new Macchina(canvas.getWidth() / 2, canvas.getHeight() / 2, 0, -8, semaforoVerticale);
        m1.disegna(gc);
        Macchina m2 = new Macchina(canvas.getWidth() / 2 + 250, canvas.getHeight() / 2, -8, 0, semaforoOrizzontale);
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
