package pizzolo.com.simulatoretraffico.model;

public class Macchina {
    private final double maxVelocita = 100;
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
}
