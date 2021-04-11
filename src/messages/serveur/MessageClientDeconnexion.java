package messages.serveur;

import client.ClientSocket;
import messages.Message;
import serveur.phases.Phase;
import serveur.phases.PhaseLobby;

/**
 * Message indiquant que le joueur s'est déconnecté du serveur
 */
public class MessageClientDeconnexion extends Message {

    // Identifiant du client
    private int idClient;

    /**
     * @param idClient Identifiant du client
     */
    public MessageClientDeconnexion(String idClient) {
        this.idClient = Integer.valueOf(idClient);
    }

    /**
     * Déconnecter le client du serveur
     */
    @Override
    public void action() {
        ClientSocket socket = ClientSocket.getInstance();
        Phase phase = socket.getGame().getPhaseCourante();

        // Si on est en phase lobby, enlever le être prêt
        if (phase.getNom().equals("PhaseLobby")) {
            PhaseLobby lobby = (PhaseLobby) phase;
            lobby.setEstPret(false);
            lobby.afficher();
        }
    }

}
