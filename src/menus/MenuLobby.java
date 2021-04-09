package menus;

import client.ClientSocket;
import client.RawConsoleInput;
import java.io.IOException;
import java.util.Scanner;

/**
 * Menu du lobby pour rejoindre un serveur
 */
public class MenuLobby {

    /**
     * Afficher le menu de lobby
     */
    public void showMenu() {
        RawConsoleInput.clear();
        System.out.println("Lobby, rejoindre un serveur");
        System.out.println();
        System.out.println("Saisir l'IP du serveur :");
    }

    /**
     * Retourner l'IP saisie
     *
     * @return IP serveur
     */
    public String getIp() {
        Scanner scanner = new Scanner(System.in);

        this.showMenu();

        String ip = scanner.nextLine();
        while (ip.isEmpty()) {
            showMenu();
            ip = scanner.nextLine();
        }

        return ip;
    }

    /**
     * Démarrer la connexion avec le serveur
     *
     * @return Connexion au serveur
     */
    public ClientSocket startClient() {
        boolean ok = false;
        ClientSocket socket = new ClientSocket();

        while (!ok) {
            RawConsoleInput.clear();
            String ip = this.getIp();

            try {
                RawConsoleInput.clear();
                System.out.println("Connexion au serveur " + ip + "...");
                socket.connecter(ip);
                ok = true;
            } catch (IOException ex) {
                System.out.println("Erreur de connexion, réessayez.");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex1) {
                }
            }
        }

        return socket;
    }
}
