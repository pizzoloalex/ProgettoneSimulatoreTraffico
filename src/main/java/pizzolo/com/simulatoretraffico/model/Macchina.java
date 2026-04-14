package pizzolo.com.simulatoretraffico.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * classe che gestisce la macchina
 */
public class Macchina {
    //statistiche della macchina
    private final double MAX_VELOCITA = 100;
    private double velocitaStandard;
    //velocita utilizzate per aggiornare la posizione
    private double velocitaX;
    private double velocitaY;
    //dimensioni fisse
    private final double HEIGHT = 30;
    private final double WIDTH = 30;
    //posizione della macchina
    private double posX;
    private double posY;
    //controlla se e in movimento o no (gestione futura di semafori)
    private boolean isMove;

    public Macchina(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        this.velocitaStandard = 10;
        this.velocitaX = 3;
        this.velocitaY = 3;
        //appena creata e in movimento
        this.isMove = true;
    }

    public double getVelocitaStandard() {
        return velocitaStandard;
    }

    public void setVelocitaStandard(double velocitaStandard) {
        this.velocitaStandard = velocitaStandard;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public boolean isMove() {
        return isMove;
    }

    public void setMove(boolean move) {
        isMove = move;
    }

    public void aggiorna(double maxWidth, double maxHeight) {
        posX += velocitaX;
        posY += velocitaY;

        if (posX <= 0 || posY <= 0) {
            posX = maxWidth / 2;
            posY = maxHeight / 2;
        }
        if (posX >= maxWidth || posY >= maxHeight) {
            posX = maxWidth / 2;
            posY = maxHeight / 2;
        }
    }

    /**
     * metodo che disegna la macchina
     *
     * @param gc graphicontext del canvas
     */
    public void disegna(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(posX, posY, WIDTH, HEIGHT);
    }
}
