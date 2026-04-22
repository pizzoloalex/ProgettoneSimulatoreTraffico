package pizzolo.com.simulatoretraffico.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//TODO aggiungere gestione di piu semafori e gestirli contemporaneamente
//TODO aggiungere lo sfondo , inizialmente una sola strada
//TODO gestire il rallentamento in modo tale che la macchina rallenti con velocita graduale e giusta coi da fermarsi prima della linea del semaforo

/**
 * classe che gestisce la macchina e le sue posizioni
 */
public class Macchina {
    //statistiche della macchina
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
    //velocita di rallentamento
    private double rallentamentoX;
    private double rallentamentoY;
    //    private boolean oltrepassatoMappa = false;
    //coordinate del angolo della curva
    private double angoloBersaglioX;
    private double angoloBersaglioY;


    /**
     * @param posX           posizione iniziale della macchina X
     * @param posY           posizione iniziale della macchina Y
     * @param velocitaX      velocita orizzontale
     * @param velocitaY      velocita verticale
     * @param semaforo       semaforo condiviso
     * @param rallentamentoX velocita di rallentamento orizzontale
     * @param rallentamentoY velocita di rallentamento verticale
     */
    public Macchina(double posX, double posY, double velocitaX, double velocitaY, Semaforo semaforo, double rallentamentoX, double rallentamentoY, double angoloBersaglioX, double angoloBersaglioY) {
        this.posX = posX;
        this.posY = posY;
        this.velocitaStandard = 10;
        this.velocitaY = velocitaY;
        this.velocitaX = velocitaX;
        this.velocitaXOriginale = velocitaX;
        this.velocitaYOriginale = velocitaY;
        this.rallentamentoX = rallentamentoX;
        this.rallentamentoY = rallentamentoY;
        //appena creata e in movimento
        this.isMove = true;
        this.semaforo = semaforo;
        this.angoloBersaglioX = angoloBersaglioX;
        this.angoloBersaglioY = angoloBersaglioY;

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

    public double getVelocitaYOriginale() {
        return velocitaYOriginale;
    }

    public void setRallentamentoY(double rallentamentoY) {
        this.rallentamentoY = rallentamentoY;
    }

    public void setRallentamentoX(double rallentamentoX) {
        this.rallentamentoX = rallentamentoX;
    }

    public void setVelocitaYOriginale(double velocitaYOriginale) {
        this.velocitaYOriginale = velocitaYOriginale;
    }

    public void setVelocitaXOriginale(double velocitaXOriginale) {
        this.velocitaXOriginale = velocitaXOriginale;
    }

    public double getVelocitaXOriginale() {
        return velocitaXOriginale;
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

        gestioneCurve();
        gestioneSemaforo();
    }

    /**
     * metodo che gestisce lo stop and go della macchina con riferimento del semaforo
     * ROSSO -> macchina prima del semaforo si ferma, gia superato il semaforo prima di rosso va avanti
     * GIALLO -> prima del semaforo rallenta dopo riaccelera
     * VERDE -> la macchina va avanti
     */
    private void gestioneSemaforo() {
        if (semaforo.isGiallo()) {
            if (hasSuperatoSemaforo()) {
                isMove = true;
                velocitaX = velocitaXOriginale;
                velocitaY = velocitaYOriginale;
            } else {
                isMove = true;
                velocitaX = rallentamentoX;
                velocitaY = rallentamentoY;
            }
        } else if (semaforo.isRosso()) {
            if (hasSuperatoSemaforo()) {
                isMove = true;
                velocitaX = velocitaXOriginale;
                velocitaY = velocitaYOriginale;
            } else {
                isMove = false;
                velocitaX = 0;
                velocitaY = 0;
            }
        } else {
            isMove = true;
            velocitaY = velocitaYOriginale;
            velocitaX = velocitaXOriginale;
        }
    }

    /**
     * controlla la velocita della macchina quindi la direzione
     *
     * @return se la macchina ha gia superato il semaforo
     */
    private boolean hasSuperatoSemaforo() {
//        if (oltrepassatoMappa) return false;
        //si muove verso destra
        if (velocitaX < 0) {
            //controlla la posizione della macchine
            return posX < semaforo.getPosX();
        }
        if (velocitaY < 0) {
            return posY < semaforo.getPosY();
        }
        return false;
    }

    private void gestioneCurve() {
        //todo
        /*
        calcolare la distanza tra la macchina e la zona di curva (distanza tra due punti)
        appena trovata la distanza controllare appena la macchina entra nella zona di curva
        appena entra gestire angolazione di rotazione della macchina
         */
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

}
