package logic;

public class Ingredient extends Thread {

    // Attributs
    private String type;
    private Morceau morceau1;
    private Morceau morceau2;
    private Morceau morceau3;

    // Constructeurs
    public Ingredient(String type, int i, int j, Cellule[][] cellules) {
        this.setType(type);
        initMorceau(i, j);
        placerDansCellule(cellules);
    }

    public Ingredient(String type, int i, int j) {
        this.setType(type);
        initMorceau(i, j);
    }

    // Méthode Run
    public void run() {

    }

    // Init
    public void initMorceau(int i, int j) {
        morceau1 = new Morceau(i, j - 1, this);
        morceau2 = new Morceau(i, j, this);
        morceau3 = new Morceau(i, j + 1, this);
    }

    public void placerDansCellule(Cellule[][] cellules) {
        cellules[this.morceau1.getPos().getPosi()][this.morceau1.getPos().getPosj()].setMorceau(this.morceau1);
        cellules[this.morceau2.getPos().getPosi()][this.morceau2.getPos().getPosj()].setMorceau(this.morceau2);
        cellules[this.morceau3.getPos().getPosi()][this.morceau3.getPos().getPosj()].setMorceau(this.morceau3);
    }

    // Get
    public String getType() {
        return this.type;
    }

    public Morceau getMorceau1() {
        return this.morceau1;
    }

    public Morceau getMorceau2() {
        return this.morceau2;
    }

    public Morceau getMorceau3() {
        return this.morceau3;
    }

    // Set
    public void setType(String type) {
        this.type = type;
    }

    // toString
    public String toString() {
        if (type.equals("P1") || type.equals("P2")) {
            return "P";
        } else if (type.equals("S")) {
            return "S";
        } else if (type.equals("V")) {
            return "V";
        } else {
            return null;
        }
    }

}
