package logic;

public class Players {

    // Attributs
    private Player[] players;    
    
    // Constructeurs
    public Players(int size) {
        players = new Player[size];
    }

    // Get
    public Player getPlayers(int id) {
        return this.players[id];
    }

    // Set
    public void setPlayers(int id, Player player) {
        this.players[id] = player;
    }


}
