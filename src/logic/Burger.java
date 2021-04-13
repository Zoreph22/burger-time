package logic;

import java.util.ArrayList;
import utils.RawConsoleInput;

public class Burger {

    // Attributs
    private Ingredient[] ingredients;
    private Assiettes assiettes;
    
    // Constructeurs
    public Burger(Assiettes assiettes) {
        ingredients = new Ingredient[4];
        this.assiettes = assiettes;
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

    public int getIngredientLength(){
        return this.ingredients.length;
    }
    
    public int getIndex(Ingredient ingredient) {
        for (int i = 0; i < 4; i++) {
            if (this.ingredients[i] == ingredient) {
                return i;
            }
        }
        
        return 0;
    }

    /**
     * Retourner si le burger est complété
     *
     * @return Burger complété ?
     */
    public boolean estComplete() {
        boolean estComplete = true;

        // Vérifier que tous les ingrédients sont dans l'assiette
        for (int i = 0; i < 4; i++) {
            // S'il y a un ingrédient pas dans l'assiette
            if (!this.ingredients[i].getDansAssiette()) {
                estComplete = false;
                break;
            }
        }
        
        return estComplete;
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

    /**
     * Retourner s'il y a un ingrédient à un palier spécifique
     *
     * @param palier Numéro palier
     * @return true si ingrédient à ce palier
     */
    private boolean hasIngredientAuPalier(int palier) {
        boolean has = false;
        
        for (int i = 0; i < 4; i++) {
            if (this.ingredients[i].getPalierActuel() == palier) {
                has = true;
                break;
            }
        }
        
        return has;
    }

    /**
     * Faire tomber un ingrédient d'un palier
     *
     * @param source Ingrédient que le joueur fait tomber
     */
    public void faireTomber(Ingredient source) {
        int indexSource = this.getIndex(source);

        // On retire l'ingrédient source de sa position actuelle dans le niveau
        source.getCellules()[source.getMorceau1().getPos().getPosi()][source.getMorceau1().getPos().getPosj()].setMorceau(null);
        source.getCellules()[source.getMorceau2().getPos().getPosi()][source.getMorceau2().getPos().getPosj()].setMorceau(null);
        source.getCellules()[source.getMorceau3().getPos().getPosi()][source.getMorceau3().getPos().getPosj()].setMorceau(null);
        source.getMorceau1().setMarcher(false);
        source.getMorceau2().setMarcher(false);
        source.getMorceau3().setMarcher(false);

        /*
         * Si l'ingrédient source est au dernier palier,
         * le mettre directement dans l'assiette
         */
        if (source.getPalierActuel() == 1) {
            source.mettreDansAssiette();
            return;
        }

        /*
         * Si le palier en dessous ne contient pas d'ingrédient,
         * on descend d'un palier l'ingrédient source
         */
        if (!this.hasIngredientAuPalier(source.getPalierActuel() - 1)) {
            source.descendrePalier();
            return;
        }

        /*
         * Sinon, on fait descendre d'un palier chaque ingrédient en dessous
         * de l'ingrédient source
         */
        { 
            source.setPalierActuel(source.getPalierActuel() - 1);
            Ingredient enCours = source;
            
            
            for (int i = indexSource + 1; i < 4; i++) {
                Ingredient tmp = this.ingredients[i];
                
                if (tmp.getDansAssiette()) {
                    break;
                }
                
                tmp.setPalierActuel(tmp.getPalierActuel() - 1);
                tmp.echangerPos(enCours);
                enCours = tmp;
            }

            // Mettre dans l'assiette le dernier ingrédient sorti du niveau
            enCours.mettreDansAssiette();
        }
    }
}
