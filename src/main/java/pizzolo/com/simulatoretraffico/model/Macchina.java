package pizzolo.com.simulatoretraffico.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

//TODO aggiungere gestione di piu semafori e gestirli contemporaneamente
//TODO controllare che la macchina si  fermi prima del semaforo e non dopo
//TODO aggiungere lo sfondo , inizialmente una sola strada
//TODO gestire il rallenatamento della macchina quando il semaforo e rosso
//TODO gestire piu macchine su semafori distinti

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
    //gestione di due incroci
    private Semaforo semaforo;
    private double velocitaXOriginale;
    private double velocitaYOriginale;


    /**
     * @param posX      posizione iniziale della macchina X
     * @param posY      posizione iniziale della macchina Y
     * @param velocitaX velocita orizzontale
     * @param velocitaY velocita verticale
     * @param semaforo  semaforo condiviso
     */
    public Macchina(double posX, double posY, double velocitaX, double velocitaY, Semaforo semaforo) {
        this.posX = posX;
        this.posY = posY;
        this.velocitaStandard = 10;
        this.velocitaY = velocitaY;
        this.velocitaX = velocitaX;
        this.velocitaXOriginale = velocitaX;
        this.velocitaYOriginale = velocitaY;
        //appena creata e in movimento
        this.isMove = true;
        this.semaforo = semaforo;
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
     * gestisce quando la macchina e in movimento o farma
     *
     * @param maxWidth  larghezza massima per la gestione dei limiti
     * @param maxHeight altezza massima per la gestione dei limiti
     */
    public void aggiorna(double maxWidth, double maxHeight) {
        if (semaforo.isGiallo()) {
            isMove = true;
            velocitaX = 0;
            velocitaY = -5;
        } else if (semaforo.isRosso()) {
            isMove = false;
            velocitaX = 0;
            velocitaY = 0;
        } else {
            isMove = true;
            velocitaY = velocitaYOriginale;
            velocitaX = velocitaXOriginale;
        }

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
        //disegna la macchina
        gc.setFill(Color.BLACK);
        gc.fillOval(posX, posY, WIDTH, HEIGHT);
    }

//    /**
//     * metodo che disegna il semaforo e gestisce il cambio colore prendendo il colore dalla classe semaforo
//     *
//     * @param gc graphicontext del canvas
//     */
//    public void gestioneSemaforo(GraphicsContext gc) {
//        gc.setFill(semaforoIncrocioA1.getColore());
//        gc.fillOval(gc.getCanvas().getWidth() / 2, (gc.getCanvas().getHeight() / 2) - 100, WIDTH, HEIGHT);
//        gc.setFill(semaforoIncrocioA2.getColore());
//        gc.fillOval((gc.getCanvas().getWidth() / 2) + 200, (gc.getCanvas().getHeight() / 2) - 200, WIDTH, HEIGHT);
//    }

}
