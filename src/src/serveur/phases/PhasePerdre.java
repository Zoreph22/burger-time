package serveur.phases;

import utils.RawConsoleInput;

/**
 *
 */
public class PhasePerdre extends Phase {

    /**
     * [CLIENT] Afficher l'écran de fin de niveau
     */
    public void afficher() {
        RawConsoleInput.clear();
        RawConsoleInput.println("Échec, vous avez perdu ce niveau !");
    }

    @Override
    public String getNom() {
        return "PhasePerdre";
    }

}
