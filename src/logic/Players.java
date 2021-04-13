package logic;

import client.ClientSocket;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class Players {

    // Attributs
    private HashMap<UUID, Player> players = new HashMap<>();

    // Get
    public Player getPlayer(UUID id) {
        return this.players.get(id);
    }

    public Collection<Player> getPlayers() {
        return this.players.values();
    }

    // Set
    public void setPlayer(UUID id, Player player) {
        this.players.put(id, player);
    }

    public int playersSize() {
        return this.players.size();
    }

    public Player getMonJoueur() {
        return this.players.get(ClientSocket.getInstance().getIdClient());
    }
}
