package logic;

public class Player extends Entity {

    // Attributs

    // Constructeurs
    public Player(int posi, int posj, String symbol, Cellule[][] cellules) {
        super(posi, posj, cellules);
        setSymbol(symbol);
    }

    // Méthode run de Player
    public void run() {

    }

    //Collision Ingredient
    public Ingredient ingredientCollision(Ingredient ingredient){
        if(isColliding(ingredient.getMorceau1().getPos().getPosi(), ingredient.getMorceau1().getPos().getPosj())){
            return ingredient;
        }
        if(isColliding(ingredient.getMorceau2().getPos().getPosi(), ingredient.getMorceau1().getPos().getPosj())){
            return ingredient;
        }
        if(isColliding(ingredient.getMorceau3().getPos().getPosi(), ingredient.getMorceau1().getPos().getPosj())){
            return ingredient;
        }
        return null;
    }
}
