package logic;

import utils.RawConsoleInput;

public class Morceau {

    // Attributs
    private Position pos;
    private boolean marcher;
    private Ingredient ingredient;

    // Constructeurs
    public Morceau(int i, int j, Ingredient ingredient) {
        this.pos = new Position(i, j);
        this.ingredient = ingredient;
    }

    // Get
    public Position getPos() {
        return this.pos;
    }

    public boolean getMarcher() {
        return this.marcher;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    // Set 
    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void setMarcher(boolean marcher) {
        this.marcher = marcher;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String toString() {
        String c = this.getIngredient().toString();

        if (this.marcher) {
            c = c.toLowerCase();
        }

        return c;
    }

    public void collisionEffect(Entity entity) {
        if (entity.getType().equals("Player")) {
            this.marcher = true;
            this.ingredient.checkEtatMorceaux();
        }
    }
}
