package logic;

import serveur.ServeurSocket;
import serveur.phases.PhaseGagner;
import serveur.phases.PhasePartie;
import utils.RawConsoleInput;

public class Assiettes {

    // Attributs
    private Burger[] burgers;
    private int size;
    private boolean gagner;

    // Constructeurs
    public Assiettes(int size) {
        setSize(size);
        burgers = new Burger[this.size];
        //initBurger();
    }

    public Assiettes(int size, Burger[] burgers) {
        setSize(size);
        this.burgers = burgers;
    }

    // Get
    public int getSize() {
        return this.size;
    }

    public Burger getAssiette(int id) {
        return this.burgers[id];
    }

    public Boolean getGagner() {
        return this.gagner;
    }

    // Set 
    public void setSize(int size) {
        this.size = size;
    }

    public void setAssiette(int id, Burger burger) {
        this.burgers[id] = burger;
    }

    public void setGagner(boolean gagner) {
        this.gagner = gagner;
    }

    // Méthode init 
    public void initBurger() {
        for (int i = 0; i < this.size; i++) {
            // burgers[i] = new Burger();
        }
    }

    // Print
    public void print() {
        RawConsoleInput.println("");

        // Affichage du pain haut de chaque burger
        for (int i = 0; i < this.size; i++) {
            Ingredient painHaut = this.burgers[i].getIngredient(0);
            RawConsoleInput.print(" ");

            if (painHaut.getDansAssiette()) {
                RawConsoleInput.print(painHaut.toString());
                RawConsoleInput.print(painHaut.toString());
                RawConsoleInput.print(painHaut.toString());
            } else {
                RawConsoleInput.print("   ");
            }

            RawConsoleInput.print(" ");
        }

        RawConsoleInput.println();

        // Affichage de la salade de chaque burger
        for (int i = 0; i < this.size; i++) {
            Ingredient salade = this.burgers[i].getIngredient(1);
            RawConsoleInput.print(" ");

            if (salade.getDansAssiette()) {
                RawConsoleInput.print(salade.toString());
                RawConsoleInput.print(salade.toString());
                RawConsoleInput.print(salade.toString());
            } else {
                RawConsoleInput.print("   ");
            }

            RawConsoleInput.print(" ");
        }

        RawConsoleInput.println();

        // Affichage de la viande de chaque burger
        for (int i = 0; i < this.size; i++) {
            Ingredient viande = this.burgers[i].getIngredient(2);
            RawConsoleInput.print(" ");

            if (viande.getDansAssiette()) {
                RawConsoleInput.print(viande.toString());
                RawConsoleInput.print(viande.toString());
                RawConsoleInput.print(viande.toString());
            } else {
                RawConsoleInput.print("   ");
            }

            RawConsoleInput.print(" ");
        }

        RawConsoleInput.println();

        // Affichage du pain bas de chaque burger
        for (int i = 0; i < this.size; i++) {
            Ingredient painBas = this.burgers[i].getIngredient(3);
            RawConsoleInput.print(" ");

            if (painBas.getDansAssiette()) {
                RawConsoleInput.print(painBas.toString());
                RawConsoleInput.print(painBas.toString());
                RawConsoleInput.print(painBas.toString());
            } else {
                RawConsoleInput.print("   ");
            }

            RawConsoleInput.print(" ");
        }

        RawConsoleInput.println();

        // Affichage des assiettes
        for (int i = 0; i < this.size; i++) {
            RawConsoleInput.print("└───┘");
        }

        RawConsoleInput.println();
    }

    public void gagner() {
        int compte = 0;

        for (int i = 0; i < burgers.length; i++) {
            Burger temp = burgers[i];
            if (temp.estComplete()) {
                compte++;
            }
        }

        if (compte == this.size) {
            setGagner(true);

            if (ServeurSocket.isServeur()) {
                ((PhasePartie) ServeurSocket.getInstance().getGame().getPhaseCourante()).getLevel().getEnemys().stopIAs();
                ServeurSocket.getInstance().getGame().setPhaseCourante(new PhaseGagner());
                ServeurSocket.getInstance().broadcast("SERVER_LEVEL_WIN");
            }
        }
    }
}
