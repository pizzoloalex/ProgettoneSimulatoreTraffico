package pizzolo.com.simulatoretraffico.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Macchina {
    private final double MMAX_VELOCITA = 100;
    //dimensioni fisse
    private final double HEIGHT = 30;
    private final double WIDTH = 30;
    private double velocitaStandard;
    private double posX;
    private double posY;

    public Macchina(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        this.velocitaStandard = 50;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void disegna(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(posX,posY, WIDTH, HEIGHT);
    }
}
