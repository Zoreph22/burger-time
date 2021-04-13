package logic;

import utils.RawConsoleInput;

public class Enemy extends Entity {

    // Attributs
    private Ia ia;
    private Level level;

    // Constructeurs
    public Enemy(int posi, int posj, String symbol, Cellule[][] cellules, Level level) {
        super(posi, posj, cellules, level);
        setSymbol(symbol);
        this.level = level;
        this.ia = new Ia(this, level);
    }

    // Get
    public Ia getIa() {
        return this.ia;
    }

    // Set
    public void setIa(Ia ia) {
        this.ia = ia;
    }

    @Override
    public void collisionEffect(Entity entity) {
        if (entity.getType().equals("Player")) {
            RawConsoleInput.println("PLAYER TOUCHÉ");
                entity.setVie(entity.getVie()-1);
                if(entity.getVie() <= 0){
                    entity.setMort(true);
                    Position pos = entity.getPosition();
                    entity.getCellules()[pos.getPosi()][pos.getPosj()].setEntity(null);
                }
        }
    }

    @Override
    public String getType() {
        return "Enemy";
    }

}
