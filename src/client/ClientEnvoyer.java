package client;

import java.awt.event.KeyEvent;
import utils.RawConsoleInput;
import java.io.IOException;
import logic.Game;
import logic.Level;
import serveur.phases.Phase;
import serveur.phases.PhaseLobby;
import serveur.phases.PhasePartie;

/**
 * Classe qui gère la capture des touches du clavier, et envoie les actions
 * correspondantes au serveur
 */
public class ClientEnvoyer extends Thread {

    // Socket du serveur
    private ClientSocket socket;
    // Message a envoyer au serveur
    private String msgAEnvoyer;
    // Code de la touche appuyée
    private int codeToucheAppuyee;

    /**
     * @param socket Socket du serveur
     */
    public ClientEnvoyer(ClientSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        this.boucleClavier();
    }

    /**
     * Capturer les inputs clavier
     */
    public void boucleClavier() {
        while (!this.socket.isClosed()) {
            try {
                this.codeToucheAppuyee = RawConsoleInput.read(true);
                this.reagirAuToucheClavier();
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
    }

    public void reagirAuToucheClavier() {
        Game game = ClientSocket.getInstance().getGame();
        Phase phase = game.getPhaseCourante();

        // Ne rien si on est dans aucune phase
        if (phase == null) {
            return;
        }

        // Quitter le jeu à l'appuie de la touche Échap
        if (this.codeToucheAppuyee == KeyEvent.VK_ESCAPE) {
            this.socket.deconnecter();
            System.exit(0);
            return;
        }

        // Phase lobby
        if (phase.getNom().equals("PhaseLobby")) {
            PhaseLobby lobby = (PhaseLobby) phase;

            if (this.codeToucheAppuyee == 13 || this.codeToucheAppuyee == 10) {
                // Ne rien faire si le joueur est déjà prêt
                if (lobby.getEstPret()) {
                    return;
                }

                ClientSocket.getInstance().envoyer("CLIENT_LOBBY_PLAYER_READY");
                lobby.setEstPret(true);
            }
        }

        // Phase partie
        if (phase.getNom().equals("PhasePartie")) {
            PhasePartie partie = (PhasePartie) phase;
            Level level = partie.getLevel();

            switch (this.codeToucheAppuyee) {
                case 122: // Z minuscule
                    //level.getPlayers().getMonJoueur().up(level.getCellules());
                    socket.envoyer("CLIENT_PLAYER_MOVED|" + socket.getIdClient() + "|UP");
                    //level.print();
                    break;
                case 115: // S minuscule
                    //level.getPlayers().getMonJoueur().down(level.getCellules());
                    socket.envoyer("CLIENT_PLAYER_MOVED|" + socket.getIdClient() + "|DOWN");
                    //level.print();
                    break;
                case 113: // Q minuscule
                    //level.getPlayers().getMonJoueur().left(level.getCellules());
                    socket.envoyer("CLIENT_PLAYER_MOVED|" + socket.getIdClient() + "|LEFT");
                    //level.print();
                    break;
                case 100: // D minuscule
                    //level.getPlayers().getMonJoueur().right(level.getCellules());
                    socket.envoyer("CLIENT_PLAYER_MOVED|" + socket.getIdClient() + "|RIGHT");
                    //level.print();
                    break;
                case 32: // Espace
                    break;
            }

        }

    }
}
