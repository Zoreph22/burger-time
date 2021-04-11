package logic;

import client.ClientEnvoyer;
import client.ClientRecevoir;
import client.ClientSocket;
import java.io.IOException;
import menus.MenuDemarrage;
import menus.MenuLobby;
import serveur.ServeurSocket;

public class BurgerTime {

    public static void main(String[] args) throws IOException {

        Game g = new Game();
        // int choixDemarrage = new MenuDemarrage().getChoix();

        // switch (choixDemarrage) {
        //     case 1:
        //         ServeurSocket.getInstance().start();
        //         break;
        //     case 2:
        //         ClientSocket socketC = new MenuLobby().startClient();
        //         ClientEnvoyer clientE = new ClientEnvoyer(socketC);
        //         ClientRecevoir clientR = new ClientRecevoir(socketC);
        //         clientE.start();
        //         clientR.start();
        //         break;
        // }
    }
}
