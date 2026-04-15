package pizzolo.com.simulatoretraffico.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pizzolo.com.simulatoretraffico.model.GestioneMovimento;
import pizzolo.com.simulatoretraffico.model.Macchina;


public class Controller {
    @FXML
    private Canvas canvas;
    @FXML
    private VBox root;

    private GraphicsContext gc;
    private Macchina macchina;
    //tiene il conto di quante macchine ci sono
    private int numMacchina;
    private GestioneMovimento gestioneMovimento;
    private double height;
    private int dist;

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        gestioneMovimento = new GestioneMovimento(gc);
//        disegnaSemaforo();
    }

    /**
     * metodo che disegna una macchina e salva in un array
     * ogni macchina disegnata ha una distanza uguale, ogni volta calcolata
     */
    @FXML
    public void aggiungiMacchina() {
        dist += 60;
        System.out.println("distanza:" + dist);
        height = (canvas.getHeight() / 2) + dist;
        System.out.println(height);
        macchina = new Macchina(canvas.getWidth() / 2, height);
        gestioneMovimento.getMacchineCanvas().add(macchina);
        macchina.disegna(gc);
        //salva la macchina appena disegnata
        numMacchina++;
        System.out.println(numMacchina);
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
        numMacchina--;
        dist -= 60;
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
