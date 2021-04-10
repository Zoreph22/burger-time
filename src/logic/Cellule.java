package logic;

public class Cellule {
    // Atributs
    private char decor;
    private Entity entity;
    private Ingredient ingredient;

    // Constructeurs 
    public Cellule(char decor) {
        this.decor = decor;
    }

    public Cellule(Entity entity){
        this.entity = entity;
    } 

    public Cellule(Ingredient ingredient){
        this.ingredient = ingredient;
    } 

    // Get
    public char getDecor() {
        return this.decor;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    // Set
    public void setDecor(char decor) {
        this.decor = decor;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    // toString
    public String toString(){
        if(this.entity != null){
            return this.entity.getSymbol();
        }
        if(this.ingredient != null){
            return this.ingredient.getType();
        }
        return String.valueOf(this.decor);
    }
}
