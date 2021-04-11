package logic;

public abstract class Entity extends Thread {

    // Attributs
    private Position pos;
    private String symbol;
    private Cellule[][] cellules;

    // Constructeurs
    public Entity(int posi, int posj, Cellule[][] cellules) {
        pos = new Position(posi,posj);
        this.cellules = cellules;
    }

    // Méthode run de Entity extends Thread
    public void run() {

    }

    // Get Attributs
    public String getSymbol() {
        return this.symbol;
    }

    public Cellule[][] getCellules(){
        return this.cellules;
    }

    // Set Attributs
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    // Méthode de déplacement
    public void up(Cellule[][] cellules) {
        cellules[pos.getPosi()][pos.getPosj()].setEntity(null);
        pos.setPosi(pos.getPosi() - 1);
        cellules[pos.getPosi()][pos.getPosj()].setEntity(this);
    }

    public void down(Cellule[][] cellules) {
        cellules[pos.getPosi()][pos.getPosj()].setEntity(null);
        pos.setPosi(pos.getPosi() + 1);
        cellules[pos.getPosi()][pos.getPosj()].setEntity(this);
    }

    public void right(Cellule[][] cellules) {
        cellules[pos.getPosi()][pos.getPosj()].setEntity(null);
        pos.setPosj(pos.getPosj() + 1);
        cellules[pos.getPosi()][pos.getPosj()].setEntity(this);
    }

    public void left(Cellule[][] cellules) {
        cellules[pos.getPosi()][pos.getPosj()].setEntity(null);
        pos.setPosj(pos.getPosj() - 1);
        cellules[pos.getPosi()][pos.getPosj()].setEntity(this);
    }

    // Collision Ennemy
    public boolean isColliding(int posi, int posj) {
        if (pos.getPosi() == posi && pos.getPosj() == posj) {
            return true;
        }
        return false;
    }

    public Entity entityCollision(Entity entity) {
        if (isColliding(entity.pos.getPosi(), entity.pos.getPosj())) {
            return entity;
        }
        return null;
    }
}
