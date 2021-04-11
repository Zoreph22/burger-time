package logic;

public class Enemy extends Entity {

    // Attributs

    // Constructeurs
    public Enemy(int posi, int posj, String symbol, Cellule[][] cellules) {
        super(posi, posj, cellules);
        setSymbol(symbol);
    }

    // Méthode run de Enemy
    public void run() {

    }
}
