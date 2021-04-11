package messages.serveur;

import client.ClientSocket;
import messages.Message;
import serveur.phases.PhasePartie;

/**
 * Message envoyant le niveau aux joueurs
 */
public class MessageEnvoyerLevel extends Message {

    // Numéro du niveau
    private int numLevel;
    
    public MessageEnvoyerLevel(String numLevel) {
        this.numLevel = Integer.valueOf(numLevel);
    }
    
    @Override
    public void action() {
        PhasePartie partie = new PhasePartie();
        ClientSocket.getInstance().getGame().setPhaseCourante(partie);
        partie.setLevel(this.numLevel);
        partie.getLevel().print();
        partie.getLevel().getAssiettes().print();
    }
    
}
