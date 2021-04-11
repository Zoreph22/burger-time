package logic;

public abstract class Entity extends Thread {

    // Attributs
    private Position pos;
    private String symbol;
    private Cellule[][] cellules;
    private Level level;

    // Constructeurs
    public Entity(int posi, int posj, Cellule[][] cellules, Level level) {
        pos = new Position(posi, posj);
        this.cellules = cellules;
        this.level = level;
    }

    // Méthode run de Entity extends Thread
    public void run() {

    }

    // Get Attributs
    public String getSymbol() {
        return this.symbol;
    }

    public Cellule[][] getCellules() {
        return this.cellules;
    }

    public Level getLevel() {
        return this.level;
    }

    public Position getPosition() {
        return this.pos;
    }

    // Set Attributs
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    // Méthode de déplacement
    public void up(Cellule[][] cellules) {
        if (pos.getPosi() > 0) {
            if (cellules[pos.getPosi() - 1][pos.getPosj()].getDecor() == getLevel().getLadder()) {
                cellules[pos.getPosi()][pos.getPosj()].setEntity(null);
                pos.setPosi(pos.getPosi() - 1);
                cellules[pos.getPosi()][pos.getPosj()].setEntity(this);
            } else if (cellules[pos.getPosi() - 1][pos.getPosj()].getDecor() == getLevel().getFloor() && cellules[pos.getPosi()][pos.getPosj()].getDecor() == getLevel().getLadder()) {
                cellules[pos.getPosi()][pos.getPosj()].setEntity(null);
                pos.setPosi(pos.getPosi() - 2);
                cellules[pos.getPosi()][pos.getPosj()].setEntity(this);
            }
        }
    }

    public void down(Cellule[][] cellules) {
        if (pos.getPosi() < cellules.length - 2) {
            if (cellules[pos.getPosi() + 1][pos.getPosj()].getDecor() == getLevel().getLadder()) {
                cellules[pos.getPosi()][pos.getPosj()].setEntity(null);
                pos.setPosi(pos.getPosi() + 1);
                cellules[pos.getPosi()][pos.getPosj()].setEntity(this);
            } else if (cellules[pos.getPosi() + 1][pos.getPosj()].getDecor() == getLevel().getFloor() && cellules[pos.getPosi()][pos.getPosj()].getDecor() == getLevel().getLadder() && pos.getPosi() < cellules.length - 3) {
                cellules[pos.getPosi()][pos.getPosj()].setEntity(null);
                pos.setPosi(pos.getPosi() + 2);
                cellules[pos.getPosi()][pos.getPosj()].setEntity(this);
            }
        }
    }

    public void right(Cellule[][] cellules) {
        // La case droite n'est pas un sol
        if (cellules[pos.getPosi()][pos.getPosj() + 1].getDecor() == getLevel().getFloor()) {
            return;
        }
        // Pas dépasser les bords
        if (pos.getPosj() >= cellules[0].length - 2) {
            return;
        }
        // La case droite ne possède pas un sol sous elle
        if (cellules[pos.getPosi() + 1][pos.getPosj() + 1].getDecor() != getLevel().getFloor()
            && cellules[pos.getPosi() + 1][pos.getPosj() + 1].getDecor() != getLevel().getLadder()) {
            return;
        }

        cellules[pos.getPosi()][pos.getPosj()].setEntity(null);
        pos.setPosj(pos.getPosj() + 1);
        cellules[pos.getPosi()][pos.getPosj()].setEntity(this);
    }

    public void left(Cellule[][] cellules) {
        // La case gauche n'est pas un sol
        if (cellules[pos.getPosi()][pos.getPosj() - 1].getDecor() == getLevel().getFloor()) {
            return;
        }
        // Pas dépasser les bords
        if (pos.getPosj() <= 1) {
            return;
        }
        // La case gauche ne possède pas un sol sous elle
        if (cellules[pos.getPosi() + 1][pos.getPosj() - 1].getDecor() != getLevel().getFloor()
            && cellules[pos.getPosi() + 1][pos.getPosj() - 1].getDecor() != getLevel().getLadder()) {
            return;
        }

        cellules[pos.getPosi()][pos.getPosj()].setEntity(null);
        pos.setPosj(pos.getPosj() - 1);
        cellules[pos.getPosi()][pos.getPosj()].setEntity(this);
    }

    // Test collision
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
