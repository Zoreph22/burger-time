package client;

import java.awt.event.KeyEvent;
import utils.RawConsoleInput;
import java.io.IOException;
import logic.Game;
import serveur.phases.Phase;
import serveur.phases.PhaseLobby;

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

    }
}
