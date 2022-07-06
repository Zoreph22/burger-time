package messages.serveur;

import client.ClientSocket;
import messages.Message;
import serveur.phases.PhaseLobby;

/**
 * Message donnant les informations du lobby
 */
public class MessageLobbyActualiser extends Message {

    private int nbJoueurs;
    private int nbPrets;

    public MessageLobbyActualiser(String nbJoueurs, String nbPrets) {
        this.nbJoueurs = Integer.valueOf(nbJoueurs);
        this.nbPrets = Integer.valueOf(nbPrets);
    }

    /**
     * Actualiser les informations du lobby
     */
    @Override
    public void action() {
        PhaseLobby lobby = (PhaseLobby) ClientSocket.getInstance().getGame().getPhaseCourante();
        lobby.setNbJoueurs(this.nbJoueurs);
        lobby.setNbPrets(this.nbPrets);
        lobby.afficher();
    }

}
