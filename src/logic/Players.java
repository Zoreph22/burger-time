package logic;

public class Players {

    // Attributs
    private Player[] players;

    // Get
    public Player[] getPlayers() {
        return this.players;
    }

    // Set
    public void setPlayers(int id, Player player) {
        this.players[id] = player;
    }

    public Players(int size) {
        players = new Player[size];
    }
}
