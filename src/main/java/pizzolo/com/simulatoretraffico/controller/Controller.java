package pizzolo.com.simulatoretraffico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pizzolo.com.simulatoretraffico.model.Macchina;

public class Controller {
    @FXML
    private Canvas canvas;
    @FXML
    private VBox root;

    private GraphicsContext gc  = canvas.getGraphicsContext2D();
    private Macchina macchina;

    public void aggiungiMacchina() {
    }
}
