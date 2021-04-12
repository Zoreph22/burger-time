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
        ia = new Ia(this,level);
        // ia.start();
    }

    // Méthode run de Enemy
    public void run() {

    }

    // Get
    public Ia getIa(){
        return this.ia;
    }

    // Set
    public void setIa(Ia ia){
        this.ia = ia;
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
