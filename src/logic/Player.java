package logic;

public class Player extends Entity {

    // Attributs

    // Constructeurs
    public Player(int posx, int posy, String symbol) {
        super(posx, posx);
        setSymbol(symbol);
    }

    // Méthode run de Player
    public void run() {

    }

    //Collision Ingredient
    public Ingredient ingredientCollision(Ingredient ingredient){
        if(isColliding(ingredient.getPos1().getPosX(), ingredient.getPos1().getPosY())){
            return ingredient;
        }
        if(isColliding(ingredient.getPos2().getPosX(), ingredient.getPos2().getPosY())){
            return ingredient;
        }
        if(isColliding(ingredient.getPos3().getPosX(), ingredient.getPos3().getPosY())){
            return ingredient;
        }
        return null;
    }
}
