package logic;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getType() {
        return "Player";
    }
}
