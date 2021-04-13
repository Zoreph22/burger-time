package logic;

import java.util.UUID;
import serveur.ServeurSocket;
import serveur.phases.Phase;
import serveur.phases.PhaseGagner;
import serveur.phases.PhasePartie;

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

    /**
     * [SERVEUR] Démarrer la phase partie
     */
    public void demarrerPhasePartie() {
        ServeurSocket socket = ServeurSocket.getInstance();

        // Changer la phase
        PhasePartie partie = new PhasePartie();
        this.setPhaseCourante(partie);

        // Charger le niveau
        partie.setLevel(0);
        socket.broadcast("SERVER_LOAD_LEVEL|" + 0);

        // Spawner les joueurs
        int nb = 0;
        for (UUID idJoueur : socket.getClientsId()) {
            char symbole = (char) ('F' + nb++);
            Player player = partie.getLevel().spawnJoueur(idJoueur, String.valueOf(symbole));

            socket.broadcast("SERVER_SPAWN_PLAYER"
                    + "|" + idJoueur
                    + "|" + player.getPosition().getPosi()
                    + "|" + player.getPosition().getPosj()
                    + "|" + player.getSymbol());
        }

        // Spawner les ennemis
        for (int i = 0; i < 2; i++) {
            Enemy enemy = partie.getLevel().spawnEnemy();

            socket.broadcast("SERVER_SPAWN_ENEMY"
                    + "|" + enemy.getUuid()
                    + "|" + enemy.getPosition().getPosi()
                    + "|" + enemy.getPosition().getPosj());
        }

        // Activer l'IA des ennemis
        partie.getLevel().getEnemys().startIAs();
    }

    /**
     * [CLIENT] Gagner le niveau
     */
    public void gagnerNiveau() {
        PhaseGagner gagner = new PhaseGagner();
        this.phaseCourante = gagner;
        gagner.afficher();
    }
}
