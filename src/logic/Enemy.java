package logic;

import utils.RawConsoleInput;

public class Enemy extends Entity {

    // Attributs
    // Constructeurs
    public Enemy(int posi, int posj, String symbol, Cellule[][] cellules, Level level) {
        super(posi, posj, cellules, level);
        setSymbol(symbol);
    }

    // Méthode run de Enemy
    public void run() {

    }

    @Override
    public void collisionEffect(Entity entity) {
        if (entity.getType().equals("Player")) {
            RawConsoleInput.println("PLAYER TOUCHÉ");
        }
    }

    @Override
    public String getType() {
        return "Enemy";
    }

}
