package serveur.phases;

import utils.RawConsoleInput;

/**
 *
 */
public class PhaseGagner extends Phase {

    /**
     * [CLIENT] Afficher l'écran de fin de niveau
     */
    public void afficher() {
        RawConsoleInput.clear();
        RawConsoleInput.println("Bravo, vous avez terminé ce niveau !");
    }

    @Override
    public String getNom() {
        return "PhaseGagner";
    }

}
