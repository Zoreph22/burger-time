package logic;

public class Ingredient extends Thread {

    // Attributs
    private String type;
    private Morceau morceau1;
    private Morceau morceau2;
    private Morceau morceau3;
    private Burger burger;
    private Cellule[][] cellules;

    // Constructeurs
    public Ingredient(String type, int i, int j, Cellule[][] cellules, Burger burger) {
        this.setType(type);
        this.burger = burger;
        this.cellules = cellules;
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

    public void echangerPos(Ingredient ingredient) {
        Position tmp1 = this.morceau1.getPos();
        Position tmp2 = this.morceau2.getPos();
        Position tmp3 = this.morceau3.getPos();
        
        this.morceau1.setPos(ingredient.morceau1.getPos());
        this.morceau2.setPos(ingredient.morceau2.getPos());
        this.morceau3.setPos(ingredient.morceau3.getPos());
        
        ingredient.morceau1.setPos(tmp1);
        ingredient.morceau2.setPos(tmp2);
        ingredient.morceau3.setPos(tmp3);
        
        this.placerDansCellule(cellules);
        ingredient.placerDansCellule(cellules);
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
    
    public Cellule[][] getCellules() {
        return this.cellules;
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
    
    /**
     * Vérifier l'état des morceaux, s'ils ont été marchés dessus
     */
    public void checkEtatMorceaux() {
        if (this.morceau1.getMarcher() && this.morceau2.getMarcher() && this.morceau3.getMarcher()) {
            this.burger.faireTomber(this);
        }
    }

}
