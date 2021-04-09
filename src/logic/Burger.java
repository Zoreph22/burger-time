package logic;

public class Burger {

    private Ingredient[] ingredients;

    public Burger(int posY) {
        initBurger(posY);
    }

    public Ingredient getIngredient(int id) 
    {
        return this.ingredients[id];
    }

    private void initBurger(int posY) {
        ingredients = new Ingredient[4];
        ingredients[0] = new Ingredient("P1", posY);
        ingredients[1] = new Ingredient("S", posY);
        ingredients[2] = new Ingredient("V", posY);
        ingredients[3] = new Ingredient("P2", posY);
    }

    



}
