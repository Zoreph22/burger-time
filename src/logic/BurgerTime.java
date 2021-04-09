package logic;

import client.ClientEnvoyer;
import client.ClientRecevoir;
import client.ClientSocket;
import menus.MenuDemarrage;
import menus.MenuLobby;

public class BurgerTime {

    public static void main(String[] args) {
        //Game game = new Game();

        int choixDemarrage = new MenuDemarrage().getChoix();

        switch (choixDemarrage) {
            case 1:
                break;
            case 2:
                ClientSocket socket = new MenuLobby().startClient();
                ClientEnvoyer clientE = new ClientEnvoyer(socket);
                ClientRecevoir clientR = new ClientRecevoir(socket);
                break;
        }
    }

}
