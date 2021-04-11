package logic;

public class Enemy extends Entity {

    // Attributs

    // Constructeurs
    public Enemy(int posi, int posj, String symbol, Cellule[][] cellules,Level level) {
        super(posi, posj, cellules, level);
        setSymbol(symbol);
    }

    // Méthode run de Enemy
    public void run() {

    }
}
