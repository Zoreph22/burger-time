package client;

import java.io.IOException;

/**
 *
 */
public class ClientEnvoyer extends Thread {
    
    private String msgAEnvoyer;

    /**
     * Socket du serveur
     */
    private ClientSocket socket;

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
        // 27 = Echap Code qui permet de ne plus se soucier de la touche entrer.
        while (true) {
            try {
                int x = RawConsoleInput.read(true);
                
                System.out.println(x);
                
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
