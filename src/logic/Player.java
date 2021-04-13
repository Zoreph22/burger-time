package logic;

import utils.RawConsoleInput;

public class Player extends Entity {

    // Attributs
    // Constructeurs
    public Player(int posi, int posj, String symbol, Cellule[][] cellules, Level level) {
        super(posi, posj, cellules, level);
        setSymbol(symbol);
    }

    // Méthode run de Player
    public void run() {

    }

    @Override
    public void collisionEffect(Entity entity) {
        RawConsoleInput.println("TOUCHE!!!");
    }

    @Override
    public String getType() {
        return "Player";
    }
}
