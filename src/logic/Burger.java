package logic;

public class Burger {

    // Attributs
    private Ingredient[] ingredients;

    // Constructeurs
    public Burger(int posY) {
        initBurger(posY);
    }

    // Get
    public Ingredient getIngredient(int id) 
    {
        return this.ingredients[id];
    }

    // Set
    public void setIngredient(int id, Ingredient ingredient){
        this.ingredients[id] = ingredient;
    }

    private void initBurger(int posY) {
        ingredients = new Ingredient[4];
        ingredients[0] = new Ingredient("P1", posY);
        ingredients[1] = new Ingredient("S", posY);
        ingredients[2] = new Ingredient("V", posY);
        ingredients[3] = new Ingredient("P2", posY);
    }

    



}
