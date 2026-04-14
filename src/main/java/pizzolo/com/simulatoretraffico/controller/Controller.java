package pizzolo.com.simulatoretraffico.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
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

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
    }

    public void aggiungiMacchina() {
        macchina = new Macchina(canvas.getWidth() / 2, canvas.getHeight() / 2);
        macchina.disegna(gc);
        numMacchina++;
    }
}
