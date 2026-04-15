package pizzolo.com.simulatoretraffico.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.sql.Time;

/**
 * classe che gestisce la macchina e le sue posizioni
 */
public class Macchina {
    //statistiche della macchina
    private final double MAX_VELOCITA = 100;
    private double velocitaStandard;
    //velocita utilizzate per aggiornare la posizione
    private double velocitaX; //velocita orizzontale
    //velocita verticale
    private double velocitaY;
    //dimensioni fisse
    private final double HEIGHT = 30;
    private final double WIDTH = 30;
    //posizione della macchina
    private double posX;
    private double posY;
    //controlla se e in movimento o no (gestione futura di semafori)
    private boolean isMove;
    private Semaforo semaforo;

    public Macchina(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        this.velocitaStandard = 10;
        this.velocitaX = 0;
        this.velocitaY = -8;
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

    /**
     * aggiorna la posizione della macchina ogni volta
     * controlla i bordi del canvas
     *
     * @param maxWidth  larghezza massima per la gestione dei limiti
     * @param maxHeight altezza massima per la gestione dei limiti
     */
    public void aggiorna(double maxWidth, double maxHeight) {
        posX += velocitaX;
        posY += velocitaY;

        /*

        RIMBALZO PALLINA

        if (posX <= 0 || posX + WIDTH >= maxWidth) velocitaX = -velocitaX;
        if (posY <= 0 || posY + HEIGHT >= maxHeight) velocitaY = -velocitaY;

        */

        //controllo bordo superiore prendendo in causa la  grandezza
        if (posY + HEIGHT < 0) {
            posY = maxHeight;
        }
        //controllo bordo inferiore prendendo in causa la grandezza
        if (posY - HEIGHT > maxHeight) {
            posY = 0;
        }

        // bordo sinistro: esce a sinistra → riappare a destra
        if (posX + WIDTH < 0) {
            posX = maxWidth;
        } else if (posX - WIDTH > maxWidth) { // bordo destro: esce a destra → riappare a sinistra
            posX = 0;
        }
    }


    /**
     * metodo che disegna la macchina
     *
     * @param gc graphicontext del canvas
     */
    public void disegna(GraphicsContext gc) {
        disegnaSemaforo(gc);
        //disegna la macchina
        gc.setFill(Color.RED);
        gc.fillOval(posX, posY, WIDTH, HEIGHT);
    }

    /**
     * metodo che disegna il semaforo e gestisce il cambio colore
     * @param gc
     */
    private void disegnaSemaforo(GraphicsContext gc) {
        //disegna il semaforo
        gc.setFill(Color.GREEN);
        gc.fillOval(gc.getCanvas().getWidth() / 2, (gc.getCanvas().getHeight() / 2) - 200, WIDTH, HEIGHT);
    }
}
