package pizzolo.com.simulatoretraffico.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import pizzolo.com.simulatoretraffico.model.Macchina;

import java.util.ArrayList;

public class Controller {
    @FXML
    private Canvas canvas;
    @FXML
    private VBox root;

    private GraphicsContext gc;
    private Macchina macchina;
    //tiene il conto di quante macchine ci sono
    private int numMacchina;
    private ArrayList<Macchina> macchineCanvas;

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        macchineCanvas = new ArrayList<>();
    }


    /**
     * metodo che disegna una macchina e salva in un array
     */
    @FXML
    public void aggiungiMacchina() {
        macchina = new Macchina(canvas.getWidth() / 2, canvas.getHeight() / 2);
        macchineCanvas.add(macchina);
        macchina.disegna(gc);
        //salva la macchina appena disegnata
        numMacchina++;
    }

    /**
     * metodo che elimina l'ultima macchina aggiunta ridisegnando il canvas
     */
    @FXML
    public void eliminaMacchina() {
        if (macchineCanvas.isEmpty()) {
            return;
        }
        macchineCanvas.removeLast();
        numMacchina--;
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Macchina m : macchineCanvas) {
            m.disegna(gc);
        }
    }
}
