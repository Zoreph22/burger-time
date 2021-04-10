package logic;

public abstract class Entity extends Thread {

    // Attributs
    private Position pos;
    private String symbol;

    // Constructeurs
    public Entity(int posx, int posy) {
        pos.setPosX(posx);
        pos.setPosY(posy);
    }

    // Méthode run de Entity extends Thread
    public void run() {

    }

    // Get Attributs
    public String getSymbol() {
        return this.symbol;
    }

    // Set Attributs
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    // Méthode de déplacement
    public void up() {
        pos.setPosY(pos.getPosY() + 1);
    }

    public void down() {
        pos.setPosY(pos.getPosY() - 1);
    }

    public void right() {
        pos.setPosY(pos.getPosX() + 1);
    }

    public void left() {
        pos.setPosY(pos.getPosX() - 1);
    }

    // Collision Ennemy
    public boolean isColliding(int posx, int posy) {
        if (pos.getPosX() == posx && pos.getPosY() == posy) {
            return true;
        }
        return false;
    }

    public Entity entityCollision(Entity entity) {
        if (isColliding(entity.pos.getPosX(), entity.pos.getPosY())) {
            return entity;
        }
        return null;
    }
}
