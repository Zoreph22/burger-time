package serveur.phases;

import logic.Level;

/**
 * Phase correspondante à la partie en cours
 */
public class PhasePartie extends Phase {

    private Level level;

    @Override
    public String getNom() {
        return "PhasePartie";
    }

    public Level getLevel() {
        return this.level;
    }
    
    /**
     * Modifier le niveau en cours
     *
     * @param numLevel Numéro niveau
     */
    public void setLevel(int numLevel) {
        this.level = new Level(numLevel);
    }
}
