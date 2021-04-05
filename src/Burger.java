public class Burger {

    private Ingredient[] ingredients;

    public Burger() {
        initBurger();
    }

    public Ingredient getIngredient(int id) {
        return this.ingredients[id];
    }

    public void setIngredient(int id, Ingredient ingredient) {
        this.ingredients[id] = ingredient;
    }

    public void initBurger() {
        ingredients = new Ingredient[4];
        ingredients[0] = new Ingredient("P");
        ingredients[1] = new Ingredient("S");
        ingredients[2] = new Ingredient("V");
        ingredients[3] = new Ingredient("P");
    }
}
