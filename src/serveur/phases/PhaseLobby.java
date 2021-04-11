package serveur.phases;

import client.ClientSocket;
import utils.RawConsoleInput;

/**
 * Phase correspondante à la salle d'attente avant la partie. Les joueurs
 * doivent tous être prêts pour lancer la partie
 */
public class PhaseLobby extends Phase {

    // Nombre de joueurs dans le lobby
    private int nbJoueurs;
    // Nombre de joueurs prêts à lancer la partie
    private int nbPrets;
    // [CLIENT] Le joueur est prêt ?
    private boolean estPret;

    public PhaseLobby() {
        this.nbJoueurs = 0;
        this.nbPrets = 0;
        this.estPret = false;
    }

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    public void setNbPrets(int nbPrets) {
        this.nbPrets = nbPrets;
    }

    public void incrementerNbJoueurs() {
        this.nbJoueurs++;
    }

    public void incrementerNbPrets() {
        this.nbPrets++;
    }

    public void decrementerNbJoueurs() {
        this.nbJoueurs--;
    }

    public void decrementerNbPrets() {
        this.nbPrets--;
    }

    public int getNbJoueurs() {
        return this.nbJoueurs;
    }

    public int getNbPrets() {
        return this.nbPrets;
    }

    /**
     * [CLIENT] Modifier si le joueur est prêt
     *
     * @param estPret Le joueur est prêt ?
     */
    public void setEstPret(boolean estPret) {
        this.estPret = estPret;
    }

    /**
     * [CLIENT] Retourner si le joueur est prêt
     *
     * @return estPret Le joueur est prêt ?
     */
    public boolean getEstPret() {
        return this.estPret;
    }

    /**
     * [CLIENT] Afficher le lobby
     */
    public void afficher() {
        RawConsoleInput.clear();
        System.out.println("ID : " + ClientSocket.getInstance().getIdClient());
        System.out.println("Lobby, en attente d'autres joueurs.");
        System.out.println();
        System.out.println("Joueurs connectés : " + this.nbJoueurs + ".");
        System.out.println("Joueurs prêts : " + this.nbPrets + ".");

        if (!this.estPret) {
            System.out.println();
            System.out.println();
            System.out.println("Appuyez sur Entrée quand vous êtes prêt à démarrer la partie.");
        }
    }

    @Override
    public String getNom() {
        return "PhaseLobby";
    }
}
