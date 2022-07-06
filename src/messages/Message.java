package messages;

import serveur.ServeurSocket;

/**
 * Classe correspondante à un message de communication client-serveur
 */
public abstract class Message {

    /**
     * Action à exécuter pour ce message
     */
    public abstract void action();
}
