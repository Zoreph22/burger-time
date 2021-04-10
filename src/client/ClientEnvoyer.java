package client;

import java.io.IOException;

/**
 * Classe qui gère la capture des touches du clavier, et envoie les actions
 * correspondantes au serveur
 */
public class ClientEnvoyer extends Thread {

    // Socket du serveur
    private ClientSocket socket;
    // Message a envoyer au serveur
    private String msgAEnvoyer;

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
                int x = RawConsoleInput.read(true);

                // Touche Échap pour quitter le jeu
                if (x == 27) {
                    System.exit(0);
                }

                this.socket.envoyer("Touche appuyée : " + x);
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
    }
}
