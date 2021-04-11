package logic;

import serveur.phases.Phase;

/**
 * Classe représentant le jeu
 */
public class Game {

    // Phase courante du jeu
    private Phase phaseCourante;

    /**
     * Modifier la phase courante
     *
     * @param phase Phase
     */
    public void setPhaseCourante(Phase phase) {
        this.phaseCourante = phase;
    }

    /**
     * Retourner la phase courante
     *
     * @return Phase courante
     */
    public Phase getPhaseCourante() {
        return this.phaseCourante;
    }
}
