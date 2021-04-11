package logic;

import java.util.ArrayList;

public class Burger {

    // Attributs
    private Ingredient[] ingredients;

    // Constructeurs
    public Burger() {
        ingredients = new Ingredient[4];
    }

    public Burger(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public Burger(int i, int i1, int i2, int i3, int j) {
        initBurger(i, i1, i2, i3, j);
    }

    // Get
    public Ingredient getIngredient(int id) {
        return this.ingredients[id];
    }

    public int getIndex(Ingredient ingredient) {
        for (int i = 0; i < 4; i++) {
            if (this.ingredients[i] == ingredient) {
                return i;
            }
        }

        return 0;
    }

    // Set
    public void setIngredient(int id, Ingredient ingredient) {
        this.ingredients[id] = ingredient;
    }

    private void initBurger(int i, int i1, int i2, int i3, int j) {
        ingredients = new Ingredient[4];
        ingredients[0] = new Ingredient("P1", i, j);
        ingredients[1] = new Ingredient("S", i1, j);
        ingredients[2] = new Ingredient("V", i2, j);
        ingredients[3] = new Ingredient("P2", i3, j);
    }

    public void faireTomber(Ingredient source) {
        int indexSource = this.getIndex(source);
        
        Ingredient enCours = source;
        
        for (int i = indexSource + 1; i < 4; i++) {
            this.ingredients[i].echangerPos(enCours);
            enCours = this.ingredients[i];
        }
        
        source.getCellules()[source.getMorceau1().getPos().getPosi()][source.getMorceau1().getPos().getPosj()].setMorceau(null);
        source.getCellules()[source.getMorceau2().getPos().getPosi()][source.getMorceau2().getPos().getPosj()].setMorceau(null);
        source.getCellules()[source.getMorceau3().getPos().getPosi()][source.getMorceau3().getPos().getPosj()].setMorceau(null);
        // A FIX
    }
}
