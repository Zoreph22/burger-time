package logic;

import java.util.UUID;
import serveur.ServeurSocket;
import serveur.phases.Phase;
import serveur.phases.PhasePartie;

/**
 * Classe représentant le jeu
 */
public class Game {

    // Phase courante du jeu
    private Phase phaseCourante;
    
    public Game() {
        /*level = new Level(0);
        int i = 0;
        while (i != 200) {
            level.print();
            level.getAssiettes().print();
            Scanner s = new Scanner(System.in);
            String x = s.nextLine();
            switch (x) {
                case "z":
                    level.getPlayers().getPlayers(0).up(level.getCellules());
                    break;
                case "q":
                    level.getPlayers().getPlayers(0).left(level.getCellules());
                    break;
                case "s":
                    level.getPlayers().getPlayers(0).down(level.getCellules());
                    break;
                case "d":
                    level.getPlayers().getPlayers(0).right(level.getCellules());
                    break;
            }
            i++;
            RawConsoleInput.clear();
        }*/
    }

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
    }
}
