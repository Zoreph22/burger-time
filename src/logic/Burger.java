package logic;

public class Burger {

    // Attributs
    private Ingredient[] ingredients;

    // Constructeurs
    public Burger(){
        ingredients = new Ingredient[4];
    }
    public Burger(Ingredient[] ingredients){
        this.ingredients = ingredients;
    }
    public Burger(int y1, int y2, int y3, int y4, int x2) {
        initBurger(y1, y2, y3, y4, x2);
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

    private void initBurger(int y1, int y2, int y3, int y4, int x2) {
        ingredients = new Ingredient[4];
        ingredients[0] = new Ingredient("P1", y1, x2);
        ingredients[1] = new Ingredient("S", y2, x2);
        ingredients[2] = new Ingredient("V", y3, x2);
        ingredients[3] = new Ingredient("P2", y4, x2);
    }
}
